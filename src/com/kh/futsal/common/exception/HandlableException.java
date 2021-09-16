package com.kh.futsal.common.exception;

import com.kh.futsal.common.code.ErrorCode;

public class HandlableException extends RuntimeException{

	private static final long serialVersionUID = -3930409458039432875L;
	
	public ErrorCode error;
	
	//log를 남기지 않고 사용자에게 알림메세지만 전달하는 용도의 생성자
	public HandlableException(ErrorCode error) {
		this.error = error;
		this.setStackTrace(new StackTraceElement[0]);//stacakTrace를 비워준다
	}
	
	public HandlableException(ErrorCode error,Exception e) {
		this.error = error;
		e.printStackTrace();
		this.setStackTrace(new StackTraceElement[0]);//stacakTrace를 비워준다
	}

}
