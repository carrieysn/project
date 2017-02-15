package com.cifpay.lc.constant;

public class ResultHandler<T> {
	boolean isSuccess;
	String message;
	T data;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> ResultHandler<T> success(T data) {
		ResultHandler<T> resultHandler = new ResultHandler<T>();
		resultHandler.setData(data);
		resultHandler.setSuccess(true);

		return resultHandler;
	}

	public static <T> ResultHandler<T> fail(String message) {
		ResultHandler<T> resultHandler = new ResultHandler<T>();
		resultHandler.setSuccess(false);
		resultHandler.setMessage(message);

		return resultHandler;
	}

}
