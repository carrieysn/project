package com.cifpay.lc.domain.message;

import com.cifpay.lc.util.logging.AbstractInputBean;

import static com.cifpay.lc.util.logging.LoggerEnum.Scene.SENDMESSAGE;

public class MessageInputBean extends AbstractInputBean {

    private static final long serialVersionUID = -4869490821560743194L;

    private Long msgId;

    private Integer msgType;

    private String sence;

    public MessageInputBean() {
        super(SENDMESSAGE);
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getSence() {
        return sence;
    }

    public void setSence(String sence) {
        this.sence = sence;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MessageInputBean [msgId=");
        builder.append(msgId);
        builder.append(", msgType=");
        builder.append(msgType);
        builder.append(", sence=");
        builder.append(sence);
        builder.append("]");
        return builder.toString();
    }


}
