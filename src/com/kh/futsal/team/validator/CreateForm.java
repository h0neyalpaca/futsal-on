package com.kh.futsal.team.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class CreateForm {
	
	private String teamName;
	private HttpServletRequest request;
	
	//private TeamService teamService = new TeamService();
	private Map<String,String> faildValidation = new HashMap<String,String>();
	
	public CreateForm(ServletRequest request) {
		this.request = (HttpServletRequest) request;
		this.teamName = request.getParameter("teamName");	
	}
	
	public boolean test(){
		
		boolean isFailed = false;
		
		//사용자가 입력한 팀이름이 이미 존재하는지 확인
		/*
		 * if(!Pattern.matches("^([a-zA-Zㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{2,8}$", teamName)) {
		 * if(teamService.selectTeamByteamName(teamName) != null || teamName.equals(""))
		 * { faildValidation.put("teamName",teamName); isFailed = true; } }
		 */
		
		if(isFailed) {
			request.getSession().setAttribute("createValid", faildValidation);
			request.getSession().setAttribute("createForm",this);
			return false;
		}else {
			request.getSession().removeAttribute("createForm");
			request.getSession().removeAttribute("createValid");
			return true;
		}
	}
	
	public String getTeamName() {
		return teamName;
	}

	
	
}
