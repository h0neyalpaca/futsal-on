package com.kh.futsal.notice.model.dto;

import java.util.Date;

public class Notice {

	private String nwIdx;
	private String nwTitle;
	private String nwContent;
	private Date regDate;
	private int isDel;
	private int nwMain;
	private int views; 
	
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNwIdx() {
		return nwIdx;
	}
	public void setNwIdx(String nwIdx) {
		this.nwIdx = nwIdx;
	}
	public String getNwTitle() {
		return nwTitle;
	}
	public void setNwTitle(String nwTitle) {
		this.nwTitle = nwTitle;
	}
	public String getNwContent() {
		return nwContent;
	}
	public void setNwContent(String nwContent) {
		this.nwContent = nwContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public int getNwMain() {
		return nwMain;
	}
	public void setNwMain(int nwMain) {
		this.nwMain = nwMain;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	@Override
	public String toString() {
		return "Notice [nwIdx=" + nwIdx + ", nwTitle=" + nwTitle + ", nwContent=" + nwContent + ", regDate=" + regDate
				+ ", isDel=" + isDel + ", nwMain=" + nwMain + ", views=" + views + "]";
	}
	
	
	
}
