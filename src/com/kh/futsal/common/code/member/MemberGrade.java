package com.kh.futsal.common.code.member;

//enum(enumerated type) : 열거형
//서로 연관된 상수들의 집합
//서로 연관된 상수들을 하나의 묶음으로 다루기 위한 enum만의 문법적 형식과 메서드를 제공해준다.
public enum MemberGrade {
   
   //회원등급코드가 'ME00'이면 등급명은 '일반'이고 연장가능횟수 1회
   //내부적으로 enum은 class이다.
   //ME00("일반",1) -> public static final MemberGrade ME00 = new MemberGrade("일반",1);
   //public이기 때문에 어디에서나 접근이 가능하고, static이기 때문에 언제나 접근이 가능한 인스턴스에
   //등급명과 연장횟수를 저장해두고 getter를 통해서 반환받아 사용한다.
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