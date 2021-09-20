package com.kh.futsal.team.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.common.file.FileUtil;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.team.model.dto.Team;
import com.kh.futsal.team.model.service.TeamService;

/**
 * Servlet implementation class TeamController
 */
@WebServlet("/team/*")
public class TeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TeamService ts = new TeamService();
	
    public TeamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length-1]) {
		
		case "main":
			teamMain(request,response);
			break;
		case "join-team":
			joinTeam(request,response);
			break;
		case "join-func":
			joinFunc(request,response);
			break;
		case "create-form":
			createForm(request,response);
			break;
		case "create-func":
			createFunc(request,response);
			break;
			
		case "modify":
			teamModify(request,response);
			break;
		case "manage":
			teamManage(request,response);
			break;
		case "total-score":
			totalScore(request,response);
			break;
		case "team-board":
			teamBoard(request,response);
			break;
		case "delete-team":
			deleteTeam(request,response);
			break;

		default:
		}
	}

	private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/delete-team").forward(request, response);
		
	}
	private void teamBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/team-board").forward(request, response);
		
	}
	private void totalScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/total-score").forward(request, response);
		
	}
	private void teamManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/manage").forward(request, response);
		
	}
	private void teamModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//*** 로그인 기능구현 안됨 ***
		//Member member = (Member) request.getSession().getAttribute("authentication");

		//*** 테스트용 아이디 강제로 입력함 ***
		Member member = new Member();
		member.setUserId("alpaca_m");
		
		Team team = ts.selectTeamByUserId(member.getUserId());
		request.setAttribute("teamInfo", team);
		
		request.getRequestDispatcher("/team/managing/modify").forward(request, response);
	}
	
	//팀 생성 기능
	private void createFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		String tmCode = ts.createTmCode();
		String localCode = request.getParameter("localCode");
		//String managerId = member.getUserId();
		String tmName = request.getParameter("tmName");
		String tmGrade = request.getParameter("tmGrade");
		String tmInfo = request.getParameter("tmInfo");
		//String tmImage = request.getAttribute("");
		
		Team team = new Team();
		team.setTmCode(tmCode);
		team.setLocalCode(localCode);
		//team.setManagerId(managerId);
		team.setManagerId("leader");
		team.setTmName(tmName);
		team.setTmGrade(tmGrade);
		team.setTmInfo(tmInfo);
		
		//기능구현중 .... 오류남 2021-09-19
		//ts.createTeam(team);
		request.getRequestDispatcher("/team/create-form").forward(request, response);
	}
	
	private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/create-form").forward(request, response);
	}
	
	//팀 참가 기능
	private void joinFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//*** 로그인 기능구현 안됨 ***
		//Member member = (Member) request.getSession().getAttribute("authentication");

		//*** 테스트용 아이디 강제로 입력함 ***
		Member member = new Member();
		member.setUserId("alpaca_n");
		
		String tmCode = request.getParameter("tmCode");
		boolean flag = ts.updateByTmCode(member, tmCode);
		//팀코드가 존재하지 않으면 에러메시지를 반환
		if(flag == false) {
			response.sendRedirect("/team/join-team?err=1");
			return;
		}
		response.sendRedirect("/team/managing/modify?result=1");
	}
	private void joinTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//*** 로그인 기능구현 안됨 ***
		//Member member = (Member) request.getSession().getAttribute("authentication");
		
		//*** 테스트용 아이디 강제로 입력함 ***
		Member member = new Member();
		member.setUserId("alpaca_n");
		//member.setUserId("alpaca_m");
		
		//팀이 있는 회원은 팀관리 화면으로, 없는 회원은 팀참가 폼으로 보내기
		Team team = ts.selectTeamByUserId(member.getUserId());
		if(team != null) {
			request.getRequestDispatcher("/team/managing/modify").forward(request, response);
		}
		request.getRequestDispatcher("/team/join-team").forward(request, response);
	}
	
	private void teamMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//*** 로그인 기능구현 안됨 ***
		//Member member = (Member) request.getSession().getAttribute("authentication");
		
		//*** 테스트용 아이디 강제로 입력함 ***
		Member member = new Member();
		member.setUserId("alpaca_n");
		//member.setUserId("alpaca_m");
		
		//팀이 있는 회원은 팀관리 화면으로, 없는 회원은 메인으로 보내기
		Team team = ts.selectTeamByUserId(member.getUserId());
		if(team != null) {
			request.getRequestDispatcher("/team/managing/modify").forward(request, response);
		}
		request.getRequestDispatcher("/team/main").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
