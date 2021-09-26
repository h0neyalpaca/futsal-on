package com.kh.futsal.common.code;

public enum Config {
	
	//DOMAIN("http://www.pclass.com"),
	DOMAIN("http://localhost:7070"),
	SMTP_AUTHENTICATION_ID("mmyajit@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("kwhyweerrmlwrrez"),
	COMPANY_EMAIL("mmyajit@gmail.com"),
	//UPLOAD_PATH("C:\\CODE\\upload") 운영서버
	//UPLOAD_PATH("C:\\CODE\\upload\\");//개발서버
	UPLOAD_PATH("/resources/img/team/");
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}
}
