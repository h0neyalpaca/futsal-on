package com.kh.futsal.common.code;

public enum Config {
	
	//DOMAIN("http://www.pclass.com"),
	DOMAIN("http://localhost:7070"),
	SMTP_AUTHENTICATION_ID("fly12035@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("mj12031205"),
	COMPANY_EMAIL("fly12035@gmail.com"),
	//UPLOAD_PATH("C:\\CODE\\upload") 운영서버
	UPLOAD_PATH("C:\\CODE\\upload\\");//개발서버
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}
}
