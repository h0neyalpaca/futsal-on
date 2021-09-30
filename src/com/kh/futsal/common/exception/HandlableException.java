package com.kh.futsal.common.exception;

import com.kh.futsal.common.code.ErrorCode;

public class HandlableException extends RuntimeException{

	private static final long serialVersionUID = -3930409458039432875L;
	
	public ErrorCode error;
	
	public HandlableException(ErrorCode error) {
		this.error = error;
		this.setStackTrace(new StackTraceElement[0]);
	}
	
	public HandlableException(ErrorCode error,Exception e) {
		this.error = error;
		e.printStackTrace();
		this.setStackTrace(new StackTraceElement[0]);
	}

}
