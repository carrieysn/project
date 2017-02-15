package com.cifpay.lc.core.message.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.db.dao.MessageDao;
import com.cifpay.lc.core.db.pojo.Message;
import com.cifpay.lc.core.message.biz.comm.MsgIdWorker;
import com.cifpay.lc.domain.message.MessageInputBean;
import com.cifpay.lc.domain.message.MessageParamBean;
import com.cifpay.lc.domain.message.SendMessageOutput;
import com.cifpay.lc.exception.MessageBusinessException;

public abstract class MessageHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MsgIdWorker msgIdWorker;

    public BusinessOutput<SendMessageOutput> sendMessage(MessageEnum.MsgType msgType, MessageEnum.Scene scene, MessageParamBean messageParamBean) {
        logger.info("发送消息到MQ...msgType={}, scene={}", msgType.desc, scene.desc);

        long id = msgIdWorker.nextId();

        Message message = new Message();
        message.setId(id);
        message.setParams(JSON.toJSONString(messageParamBean));
        message.setStatus(MessageEnum.Status.HANLDING.val);
        message.setMsgType(msgType.val);
        message.setScene(scene.val);
        message.setCount(0);
        message.setLastTime(System.currentTimeMillis());
        message.setPlanTime(System.currentTimeMillis());
        message.setCreateTime(System.currentTimeMillis());
        int count = messageDao.insert(message);
        if (count == 0) {
            logger.error("保存异步处理消息到队列异常,body:", message.getParams());
            throw new MessageBusinessException(ReturnCode.CORE_MSG_SAVE_EXCEPTION_N108002, "保存消息到message表不成功");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("insert messgage:{}", message);
        }
        MessageInputBean messageInput = new MessageInputBean();
        messageInput.setRequestId(messageParamBean.getRequestId());
        messageInput.setMsgId(message.getId());
        messageInput.setMsgType(message.getMsgType());
        messageInput.setSence(message.getScene());

        MessagePublisher publisher = getMessagePublisher();
        boolean sendSuccess = publisher.send(messageInput);
        if (sendSuccess) {
            logger.info("消息发送成功");
            //发送消息成功
            return BusinessOutput.success(null);
        }

        logger.error("消息发送失败: msgId={}", messageInput.getMsgId());

        // 如果放到消息队列不成功，把状态改为待处理，由schedule继续处理
        message.setStatus(MessageEnum.Status.WAIT.val);
        boolean succ = messageDao.updateByPrimaryKeySelective(message);
        if (!succ) {
            logger.error("保存异步处理消息到队列异常,body:", message.getParams());
        }
        return BusinessOutput.fail(ReturnCode.CORE_STD_SMS_SEND_ERROR, "发送消息失败");
    }

    public abstract MessagePublisher getMessagePublisher();
}
