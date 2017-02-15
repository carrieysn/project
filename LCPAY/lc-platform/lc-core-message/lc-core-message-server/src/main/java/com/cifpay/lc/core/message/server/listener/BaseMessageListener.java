package com.cifpay.lc.core.message.server.listener;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.message.MessageService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.db.dao.MessageDao;
import com.cifpay.lc.core.db.dao.MessageHisDao;
import com.cifpay.lc.core.db.pojo.Message;
import com.cifpay.lc.core.db.pojo.MessageHis;
import com.cifpay.lc.core.interceptor.annotation.EnableGenerateRequestId;
import com.cifpay.lc.core.message.biz.comm.MessageContext;
import com.cifpay.lc.domain.message.MessageInputBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.domain.message.MessageParamBean;
import com.cifpay.lc.exception.MessageBusinessException;
import com.cifpay.lc.util.logging.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@EnableGenerateRequestId
public abstract class BaseMessageListener<TMsgParam extends MessageParamBean> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageHisDao messageHisDao;

    public abstract MessageService<TMsgParam> getMessageService();

    public void handleMessage(MessageInputBean messageInputBean) {
        if (messageInputBean != null) {
            // 设置slf4j
            LogUtil.initMDC(messageInputBean.getRequestId(), messageInputBean.getSence());

            logger.info("处理消息: msgId={}", messageInputBean.getMsgId());
        }

        MessageContext context = new MessageContext();
        BusinessOutput<MessageOutputBean> output = null;
        try {
            beforeHandle(messageInputBean, context);

            MessageService<TMsgParam> service = getMessageService();
            output = service.execute(new BusinessInput<MessageInputBean>(messageInputBean));

        } catch (MessageBusinessException e) {
            output = BusinessOutput.fail(ReturnCode.SERVER_UNAVALIABLE, "服务不可用，稍后再试");
            logger.error("消息处理不成功,returnCode:{},msg:{}", e.getReturnCode(), e.getMessage());
        } catch (Exception e) {
            output = BusinessOutput.fail(ReturnCode.UNKNOWN_ERROR, "服务器开小差，请稍后再试");
            logger.error("消息处理发生不可预期的异常,err:{}", e);
        } finally {
            afterHandle(output, context);
        }
    }

    public void beforeHandle(MessageInputBean input, MessageContext context) {
        long msgId = input.getMsgId();
        Message message = messageDao.selectByPrimaryKey(msgId);
        if (message == null) {
            throw new MessageBusinessException(ReturnCode.CORE_MSG_NOT_FOUND_EXCEPTION_N108001, "数据库不存在该消息");
        }
        context.setAttribute("message", message);
    }

    public Message getMessage(MessageInputBean input) {
        long msgId = input.getMsgId();
        Message message = messageDao.selectByPrimaryKey(msgId);
        if (message == null) {
            throw new MessageBusinessException(ReturnCode.CORE_MSG_NOT_FOUND_EXCEPTION_N108001, "数据库不存在该消息");
        }
        return message;
    }

    public void afterHandle(BusinessOutput<MessageOutputBean> output, MessageContext context) {
        try {
            Message message = (Message) context.getAttribute("message");
            if (message == null) {
                logger.error("[BaseMessageListener]_[afterHandle],message is null");
                return;
            }
            if (output.isFailed()) {
                MessageOutputBean res = output.getData();
                if ((res != null && res.isFinish()) || message.getCount() >= MessageEnum.FAIL_COUNT) {
                    //把消息记录移除到历史表，以备审计
                    MessageHis messageHis = new MessageHis();
                    messageHis.setStatus(MessageEnum.Status.FAIL.val);
                    messageHis.setLastTime(System.currentTimeMillis());
                    messageHis.setRemark("消息处理失败");
                    BeanUtils.copyProperties(message, messageHis);
                    messageHisDao.insert(messageHis);
                    //删除消息表记录
                    messageDao.deleteByPrimaryKey(message.getId());
                } else {
                    Message record = new Message();
                    record.setId(message.getId());
                    long planTime = getPlanTime(message);
                    record.setCount(message.getCount() + 1);
                    record.setPlanTime(planTime);
                    record.setStatus(MessageEnum.Status.WAIT.val);
                    record.setLastTime(System.currentTimeMillis());
                    messageDao.updateByPrimaryKeySelective(record);
                }
            } else {
                //把消息记录移除到历史表，以备审计
                MessageHis messageHis = new MessageHis();
                messageHis.setStatus(MessageEnum.Status.FIN.val);
                messageHis.setLastTime(System.currentTimeMillis());
                BeanUtils.copyProperties(message, messageHis);
                messageHis.setRemark("消息处理成功");
                messageHisDao.insert(messageHis);
                //删除消息表记录
                messageDao.deleteByPrimaryKey(message.getId());
            }
        } catch (Exception e) {
            logger.error("消息后置处理无法预期的错误", e);
        }
    }

    /**
     * 重试时间规则：2的n次方乘10
     *
     * @param message
     * @return
     */
    protected long getPlanTime(Message message) {
        if (message != null) {
            long planTime = System.currentTimeMillis();
            planTime += Math.pow(2, message.getCount()) * 1000 * 10;
            return planTime;
        }
        return 0;
    }

}
