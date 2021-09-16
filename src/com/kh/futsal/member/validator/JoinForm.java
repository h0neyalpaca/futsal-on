package com.kh.futsal.member.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class JoinForm {
	
	private String userId;
	private String password;
	private String email;
	private String tell;
	private String nickName;
	private HttpServletRequest request;
	
	//private MemberService memberService = new MemberService();
	private Map<String,String> faildValidation = new HashMap<String,String>();
	
	public JoinForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.userId = request.getParameter("userId");
		this.password = request.getParameter("password");
		this.email = request.getParameter("email");
		this.tell = request.getParameter("tell");
		this.nickName = request.getParameter("nickName");
	}
	
	public boolean test(){
		
		boolean isFailed = false;
		
		//사용자 아이디가 DB에 이미 존재하는 지 확인
		/*
		 * if(memberService.selectMemberById(userId) != null || userId.equals("")) {
		 * faildValidation.put("userId",userId); isFailed = true; }
		 */
		
		//비밀번호가 영어,숫자,특수문자 조합의 8자리 이상의 문자열인지 확인
		if(!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", password)) {
			faildValidation.put("password",password);
			isFailed = true;
		}
		//닉네임
		if(!Pattern.matches("^([a-zA-Zㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{2,8}$", nickName)) {
			faildValidation.put("nickName",nickName);
			isFailed = true;
		}
		//전화번호가 숫자로만 이루어져 있는 지 확인
		if(!Pattern.matches("\\d{9,11}", tell)) {
			faildValidation.put("tell",tell);
			isFailed = true;
		}
		
		if(isFailed) {
			request.getSession().setAttribute("joinValid", faildValidation);
			request.getSession().setAttribute("joinForm",this);
			return false;
		}else {
			request.getSession().removeAttribute("joinForm");
			request.getSession().removeAttribute("joinValid");
			return true;
		}
	}
	
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getTell() {
		return tell;
	}
	public String getNickName() {
		return nickName;
	}
	
}
