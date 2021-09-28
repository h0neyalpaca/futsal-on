package com.kh.futsal.matching.model.dto;

import java.util.Date;

public class MatchMaster {
	private String mmIdx; // 매치 번호
	private String userId; // 유저id
	private String tmCode; // 팀 코드
	private String localCode; // 지역코드
	private String address; // 상세주소
	private Date regDate; // 작성 일자
	private String title; // 제목
	private int matchNum; // 용병 인원
	private String expense; // 비용
	private String grade; // 실력
	private String content; // 내용
	private int tmMatch; // 매치 방식
	private String matchDate; // 매치 날짜
	private String matchTime; // 매치 시간

	private int state;
	
	// 팀 dto
	private String tmName; // 팀이름
	private String tmGrade; // 팀실력
	private String tmInfo; // 소개글
	private int gameCnt; // 총게임횟수
	private int tmScore; // 평점
	private int tmWin; // 승리횟수


	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public int getGameCnt() {
		return gameCnt;
	}

	public void setGameCnt(int gameCnt) {
		this.gameCnt = gameCnt;
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

	public int getTmScore() {
		return tmScore;
	}

	public void setTmScore(int tmScore) {
		this.tmScore = tmScore;
	}

	public int getTmWin() {
		return tmWin;
	}

	public void setTmWin(int tmWin) {
		this.tmWin = tmWin;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getMmIdx() {
		return mmIdx;
	}

	public void setMmIdx(String mmIdx) {
		this.mmIdx = mmIdx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getMatchNum() {
		return matchNum;
	}

	public void setMatchNum(int matchNum) {
		this.matchNum = matchNum;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTmMatch() {
		return tmMatch;
	}

	public void setTmMatch(int tmMatch) {
		this.tmMatch = tmMatch;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	@Override
	public String toString() {
		return "MatchMaster [mmIdx=" + mmIdx + ", userId=" + userId + ", tmCode=" + tmCode + ", localCode=" + localCode
				+ ", address=" + address + ", regDate=" + regDate + ", title=" + title + ", matchNum=" + matchNum
				+ ", expense=" + expense + ", grade=" + grade + ", content=" + content + ", tmMatch=" + tmMatch
				+ ", matchDate=" + matchDate + ", matchTime=" + matchTime + ", state=" + state + ", tmName=" + tmName
				+ ", tmGrade=" + tmGrade + ", tmInfo=" + tmInfo + ", gameCnt=" + gameCnt + ", tmScore=" + tmScore
				+ ", tmWin=" + tmWin + "]";
	}

}