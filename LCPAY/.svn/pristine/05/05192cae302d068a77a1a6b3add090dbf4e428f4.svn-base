package com.cifpay.lc.api.message;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.BusinessService;
import com.cifpay.lc.domain.message.MessageInputBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.domain.message.MessageParamBean;

public interface MessageService<TMsgParam extends MessageParamBean> extends BusinessService {

    /**
     * 每一个Core业务层的业务类必须实现该接口方法。
     *
     * @param input
     * @return
     */
    BusinessOutput<MessageOutputBean> execute(BusinessInput<MessageInputBean> input);
}

