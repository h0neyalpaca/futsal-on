package com.kh.futsal.team.model.dto;

public class ResultDTO {

	private String thIdx; //게임결과번호
	private String mgIdx; //매치게임번호
	private String matchDate; //매치일자
	private String matchTime; //매치시간
	private String matchSchedule; //매치일자+시간
	private String rivalCode; //상대팀 코드
	private String rivalName; //상대팀명
	private String hostCode; //주최팀 코드
	private String hostName; //주최팀명
	private String winner; //승자
	private int hostRating; //주최팀 만족도
	private int rivalRating; //상대팀 만족도
	public String getThIdx() {
		return thIdx;
	}
	public void setThIdx(String thIdx) {
		this.thIdx = thIdx;
	}
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
	public String getMatchSchedule() {
		return matchSchedule;
	}
	public void setMatchSchedule(String matchSchedule) {
		this.matchSchedule = matchSchedule;
	}
	public String getRivalCode() {
		return rivalCode;
	}
	public void setRivalCode(String rivalCode) {
		this.rivalCode = rivalCode;
	}
	public String getRivalName() {
		return rivalName;
	}
	public void setRivalName(String rivalName) {
		this.rivalName = rivalName;
	}
	public String getHostCode() {
		return hostCode;
	}
	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
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
	
	public long getDateParseInt() {
		return Long.parseLong(matchSchedule);
	}
	
	@Override
	public String toString() {
		return "ResultDTO [thIdx=" + thIdx + ", mgIdx=" + mgIdx + ", matchDate=" + matchDate + ", matchTime="
				+ matchTime + ", matchSchedule=" + matchSchedule + ", rivalCode=" + rivalCode + ", rivalName="
				+ rivalName + ", hostCode=" + hostCode + ", hostName=" + hostName + ", winner=" + winner
				+ ", hostRating=" + hostRating + ", rivalRating=" + rivalRating + "]";
	}
}
	
