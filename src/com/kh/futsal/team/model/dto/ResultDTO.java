package com.kh.futsal.team.model.dto;

public class ResultDTO {

	private String mgIdx; //매치게임번호
	private String matchDate; //매치일자
	private String matchTime; //매치시간
	private String applicantCode; //상대 코드
	private String applicantName; //상대팀명
	private String tmName; //주최팀명
	private String winner; //승자
	private int hostRating; //주최팀 만족도
	private int rivalRating; //상대팀 만족도
	
	public String getMgIdx() {
		return mgIdx;
	}
	public void setMgIdx(String mgIdx) {
		this.mgIdx = mgIdx;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public String getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}
	public String getApplicantCode() {
		return applicantCode;
	}
	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getTmName() {
		return tmName;
	}
	public void setTmName(String tmName) {
		this.tmName = tmName;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public int getHostRating() {
		return hostRating;
	}
	public void setHostRating(int hostRating) {
		this.hostRating = hostRating;
	}
	public int getRivalRating() {
		return rivalRating;
	}
	public void setRivalRating(int rivalRating) {
		this.rivalRating = rivalRating;
	}
	
	@Override
	public String toString() {
		return "ResultDTO [mgIdx=" + mgIdx + ", matchDate=" + matchDate + ", matchTime=" + matchTime
				+ ", applicantCode=" + applicantCode + ", applicantName=" + applicantName + ", tmName=" + tmName
				+ ", winner=" + winner + ", hostRating=" + hostRating + ", rivalRating=" + rivalRating + "]";
	}
}
