package com.kh.futsal.common.exception;

public class PageNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -930316220873109881L;

	public PageNotFoundException() {
		//StackTrace를 길이 0 짜리 새로운 StackTraceElement를 던져서 비워준다
		this.setStackTrace(new StackTraceElement[0]);
	}
}
