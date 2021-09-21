package com.kh.futsal.support.model.dto;

import java.sql.Date;

public class Support {
	
	private String dbIdx;
	private String userId;
	private Date regDate;
	private String title;
	private String content;
	private int type;
	
	public String getDbIdx() {
		return dbIdx;
	}
	public void setDbIdx(String dbIdx) {
		this.dbIdx = dbIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Support [dbIdx=" + dbIdx + ", userId=" + userId + ", regDate=" + regDate + ", title=" + title
				+ ", content=" + content + ", type=" + type + "]";
	} 
	
	
}
