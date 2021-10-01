package com.kh.futsal.team.model.dto;

import java.util.Date;

public class Team {
	private String tmCode; //팀코드
	private String localCode; //활동지역
	private String managerId; //팀장아이디
	private String tmName; //팀이름
	private String tmGrade; //팀실력
	private String tmInfo; //소개글
	private double tmRating; //팀 평균 평점
	private int gameCnt; //총게임횟수
	private int tmWin; //승리횟수
	private int tmLose; //패배횟수
	private Date regDate; //팀생성일
	private Date delDate; //팀삭제일
	
	public String getTmCode() {
		return tmCode;
	}
	public void setTmCode(String tmCode) {
		this.tmCode = tmCode;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getTmName() {
		return tmName;
	}
	public void setTmName(String tmName) {
		this.tmName = tmName;
	}
	public String getTmGrade() {
		return tmGrade;
	}
	public void setTmGrade(String tmGrade) {
		this.tmGrade = tmGrade;
	}
	public String getTmInfo() {
		return tmInfo;
	}
	public void setTmInfo(String tmInfo) {
		this.tmInfo = tmInfo;
	}
	public int getGameCnt() {
		return gameCnt;
	}
	public void setGameCnt(int gameCnt) {
		this.gameCnt = gameCnt;
	}
	public int getTmWin() {
		return tmWin;
	}
	public void setTmWin(int tmWin) {
		this.tmWin = tmWin;
	}
	public int getTmLose() {
		return tmLose;
	}
	public void setTmLose(int tmLose) {
		this.tmLose = tmLose;
	}
	public double getTmRating() {
		return tmRating;
	}
	public void setTmRating(double tmRating) {
		this.tmRating = tmRating;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	@Override
	public String toString() {
		return "Team [tmCode=" + tmCode + ", localCode=" + localCode + ", managerId=" + managerId + ", tmName=" + tmName
				+ ", tmGrade=" + tmGrade + ", tmInfo=" + tmInfo + ", tmRating=" + tmRating + ", gameCnt=" + gameCnt
				+ ", tmWin=" + tmWin + ", tmLose=" + tmLose + ", regDate=" + regDate + ", delDate=" + delDate + "]";
	}
}
