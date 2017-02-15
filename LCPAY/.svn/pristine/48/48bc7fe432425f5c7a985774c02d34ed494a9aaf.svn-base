package com.cifpay.lc.std.component.impl;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.biz.impl.LcMessageHandler;
import com.cifpay.lc.domain.message.LcNotifyParamBean;
import com.cifpay.lc.std.component.NotifyService;
import com.cifpay.lc.std.domain.component.notify.AbstractNotifyBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by sweet on 16-11-2.
 */
@Component
public class HttpNotifyServiceImpl implements NotifyService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LcMessageHandler lcMessageHandler;


    @Override
    public BusinessOutput notify(String merId, String url, AbstractNotifyBean notifyBean) {
        //DONE: 修改为异步
        logger.info("将异步通知商户消息插入队列");

        Map map = new ObjectMapper().convertValue(notifyBean, Map.class);
        LcNotifyParamBean notifyParamBean = new LcNotifyParamBean();
        notifyParamBean.setMerId(merId);
        notifyParamBean.setNotifyUrl(url);
        notifyParamBean.setMap(map);
        return lcMessageHandler.sendMessage(MessageEnum.MsgType.LC, MessageEnum.Scene.NOTIFY_MERCH, notifyParamBean);
    }
}
