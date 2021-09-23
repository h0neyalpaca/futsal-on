package com.kh.futsal.mypage.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ModifyForm {
	
	private String password;
	private String newPw;
	private String checkPw;
	private String tell;
	private String nickName;
	private HttpServletRequest request;
	
	private Map<String,String> faildValidation = new HashMap<String,String>();
	
	public ModifyForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.newPw = request.getParameter("new-password");
		this.checkPw = request.getParameter("check-new-password");
		this.tell = request.getParameter("tell");
		this.nickName = request.getParameter("nickName");
	}
	
	public boolean test(){
		
		boolean isFailed = false;
		
	
		//비밀번호가 영어,숫자,특수문자 조합의 8자리 이상의 문자열인지 확인
		if(!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", newPw)) {
			faildValidation.put("new-password",newPw);
			isFailed = true;
		}
		
		if(newPw != checkPw) {
			faildValidation.put("check-new-password",checkPw);
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
			request.getSession().setAttribute("modifyValid", faildValidation);
			request.getSession().setAttribute("modifyForm",this);
			return false;
		}else {
			request.getSession().removeAttribute("modifyForm");
			request.getSession().removeAttribute("modifyValid");
			return true;
		}
	}
	
	
	public String getNewPw() {
		return newPw;
	}

	

	public String getCheckPw() {
		return checkPw;
	}

	public String getTell() {
		return tell;
	}
	public String getNickName() {
		return nickName;
	}
	
}
