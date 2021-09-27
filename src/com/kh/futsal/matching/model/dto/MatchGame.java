package com.kh.futsal.matching.model.dto;

public class MatchGame {
	
	private String mgIdx;
	private String mmIdx;
	private String applicantCode;
	
	public String getMgIdx() {
		return mgIdx;
	}
	public void setMgIdx(String mgIdx) {
		this.mgIdx = mgIdx;
	}
	public String getMmIdx() {
		return mmIdx;
	}
	public void setMmIdx(String mmIdx) {
		this.mmIdx = mmIdx;
	}
	public String getApplicantCode() {
		return applicantCode;
	}
	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	@Override
	public String toString() {
		return "MatchGame [mgIdx=" + mgIdx + ", mmIdx=" + mmIdx + ", applicantCode=" + applicantCode + "]";
	}
	
	
	
	
	
	
}
