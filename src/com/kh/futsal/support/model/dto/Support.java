package com.kh.futsal.support.model.dto;

import java.sql.Date;

public class Support {
	
	private String bdIdx;
	private String userId;
	private Date regDate;
	private String title;
	private String content;
	private int type;
	private int isAnswer;
	private String answer;
	public String getBdIdx() {
		return bdIdx;
	}
	public void setBdIdx(String bdIdx) {
		this.bdIdx = bdIdx;
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
	public int getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(int isAnswer) {
		this.isAnswer = isAnswer;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Support [bdIdx=" + bdIdx + ", userId=" + userId + ", regDate=" + regDate + ", title=" + title
				+ ", content=" + content + ", type=" + type + ", isAnswer=" + isAnswer + ", answer=" + answer + "]";
	}
	
	
	
}
