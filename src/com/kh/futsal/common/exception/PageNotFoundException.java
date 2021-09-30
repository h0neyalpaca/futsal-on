package com.kh.futsal.common.exception;

public class PageNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -930316220873109881L;

	public PageNotFoundException() {
		this.setStackTrace(new StackTraceElement[0]);
	}
}
