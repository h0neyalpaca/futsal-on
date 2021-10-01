package com.kh.futsal.mypage.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.member.model.service.MemberService;

public class ModifyForm {
	
	private String password;
	private String newPw;
	private String checkPw;
	private String tell;
	private String nickName;
	private HttpServletRequest request;
	
	private MemberService memberService = new MemberService();
	private Map<String,String> faildValidation = new HashMap<String,String>();
	
	public ModifyForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.password = request.getParameter("password");
		this.newPw = request.getParameter("new-password");
		this.checkPw = request.getParameter("check-new-password");
		this.tell = request.getParameter("tell");
		this.nickName = request.getParameter("nickName");
	}
	
	public boolean test(){
		
		boolean isFailed = false;
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		
		if(!memberService.selectMemberById(userId).getPassword().equals(password)) {
			faildValidation.put("password",password); 
			isFailed = true;
		}
		
		
		if(memberService.selectMemberByNick(nickName) != null || nickName.equals("")) {
			if(member.getUserNick().equals(nickName)) {
				 isFailed = false;
			}else {
			 faildValidation.put("nickName",nickName); 
			 isFailed = true; 
			}
		}
	
		if(!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", newPw)) {
			faildValidation.put("new-password",newPw);
			isFailed = true;
			if(newPw.equals("")) {
				isFailed = false;
			}
		}
		
		if(!newPw.equals(checkPw)) {
			faildValidation.put("check-new-password",checkPw);
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

	public String getPassword() {
		return password;
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
