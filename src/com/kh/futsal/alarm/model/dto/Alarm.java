package com.kh.futsal.alarm.model.dto;


public class Alarm {
	
	private String ntIdx;
	private String mmIdx;
	private int state;
	private String ntDate;
	private String content;
	private String userId;
	private String matchTime;
	private int isStart;
	public String getNtIdx() {
		return ntIdx;
	}
	public void setNtIdx(String ntIdx) {
		this.ntIdx = ntIdx;
	}
	public String getMmIdx() {
		return mmIdx;
	}
	public void setMmIdx(String mmIdx) {
		this.mmIdx = mmIdx;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getNtDate() {
		return ntDate;
	}
	public void setNtDate(String ntDate) {
		this.ntDate = ntDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}
	public int getIsStart() {
		return isStart;
	}
	public void setIsStart(int isStart) {
		this.isStart = isStart;
	}
	@Override
	public String toString() {
		return "Alarm [ntIdx=" + ntIdx + ", mmIdx=" + mmIdx + ", state=" + state + ", ntDate=" + ntDate + ", content="
				+ content + ", userId=" + userId + ", matchTime=" + matchTime + ", isStart=" + isStart + "]";
	}
	
}
