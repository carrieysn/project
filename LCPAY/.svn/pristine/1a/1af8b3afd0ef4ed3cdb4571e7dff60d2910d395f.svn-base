package com.cifpay.lc.std.business.message;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.db.dao.MessageDao;
import com.cifpay.lc.core.db.pojo.Message;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.message.MessageInputBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.domain.message.MessageParamBean;
import com.cifpay.lc.exception.MessageBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;

public abstract class MessageServiceBase<TMsgParam extends MessageParamBean> extends CoreBusinessServiceImplBase<MessageInputBean, MessageOutputBean> {
    protected Class<TMsgParam> clazz;

    protected abstract BusinessOutput<MessageOutputBean> handleMessage(TMsgParam messageParams, CoreBusinessContext context) throws CoreBusinessException;

    @Autowired
    private MessageDao messageDao;

    @Override
    protected BusinessOutput<MessageOutputBean> processBusiness(MessageInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {
        Message message = messageDao.selectByPrimaryKey(inputBean.getMsgId());
        if (message == null) {
            throw new MessageBusinessException(ReturnCode.CORE_MSG_NOT_FOUND_EXCEPTION_N108001, "message不存在");
        }
        context.setAttribute("MESSAGE", message);

        if (StringUtils.isEmpty(message.getParams())) {
            throw new MessageBusinessException(ReturnCode.SERVER_PARAM_ERROR, "消息参数不正确，不能为空");
        }

        Class<TMsgParam> msgParamClass = (Class<TMsgParam>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        TMsgParam messageParams = JSON.parseObject(message.getParams(), msgParamClass);

        BusinessOutput<MessageOutputBean> output = handleMessage(messageParams, context);

        // 超过重试次数时，取消重试机制
        if (output.isFailed() && message.getCount() >= MessageEnum.MAX_COUNT) {
            output.setData(new MessageOutputBean(true));
        }

        return output;
    }
}

