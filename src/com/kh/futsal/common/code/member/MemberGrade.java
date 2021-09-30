package com.kh.futsal.common.code.member;

public enum MemberGrade {
   
	ME00("nomal", "user"),
	ME01("member", "team"),//팀원
	ME02("manager", "team"),//매니저
	ME03("leader", "team"),//팀장
	   
	AD00("super", "admin");

	public final String DESC;
	public final String ROLE;

	private MemberGrade(String desc,String role) {
		      this.DESC = desc;
		      this.ROLE = role;
	}
}