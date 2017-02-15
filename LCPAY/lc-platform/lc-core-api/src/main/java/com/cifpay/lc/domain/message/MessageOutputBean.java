package com.cifpay.lc.domain.message;

import java.io.Serializable;

public class MessageOutputBean implements Serializable {

	private static final long serialVersionUID = -7035472766282905068L;
	
	private boolean finish;
	
	public MessageOutputBean(){}
	
	public MessageOutputBean(boolean finish){
		this.finish = finish;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	
}
