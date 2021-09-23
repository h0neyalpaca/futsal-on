package com.kh.futsal.member.model.dto;

import java.sql.Date;

//DTO(DATA TRANSFER OBJECT)
//데이터 전송 객체
//데이터베이스로부터 얻어 온 데이터를 service(비지니스로직)으로 보낼 때 사용한 객체
//비지니스 로직을 포함하고 있지 않은, 순수하게 데이터 전송만을 위한 객체
//getter/setter, equals, hashCode, toString 메서드만을 갖는다.

// *** 참고 
//   도메인 객체 : 데이터베이스 테이블에서 조회 해온 한 행(row)의 값을 저장하는 용도로 사용하는 객체
//   DOMAIN OBJECT, VALUE OBJECT(VO), DTO, ENTITY, BEAN

//DTO의 조건 (JAVA BEAN 규약)
//1. 모든 필드변수는 PRIVATE일 것
//2. 반드시 기본 생성자가 존재할 것. (매개변수가 있는 생성자가 있더라도, 기본 생성자가 있어야함)
//3. 모든 필드변수는 GETTER/SETTER 메서드를 가져야 한다.

//오라클 - 자바 타입 매핑
//CHAR, VARCHAR2 -> String
//DATE -> java.util.Date, java.sql.Date
//number -> int, double
public class Member {   
   
   private String userId;
   private String tmCode;
   private String password;
   private String userName;
   private String grade;
   private String userNick;
   private String email;
   private String tell;
   private String capacity;
   private Date regDate;
   private int isLeave;
   
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getIsLeave() {
		return isLeave;
	}
	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", tmCode=" + tmCode + ", password=" + password + ", userName=" + userName
				+ ", grade=" + grade + ", userNick=" + userNick + ", email=" + email + ", tell=" + tell + ", capacity="
				+ capacity + ", regDate=" + regDate + ", isLeave=" + isLeave + "]";
	}

}