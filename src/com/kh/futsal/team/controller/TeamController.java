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
		request.getRequestDispatcher("/team/managing/modify").forward(request, response);
		
	}
	
	//팀 생성 기능
	private void createFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		String tmCode = ts.makingTmCode();
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
	
	//팀참가기능
	private void joinFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Member member = (Member) request.getSession().getAttribute("authentication");
		Member member = new Member();
		
		// **** 테스트용 아이디 ****
		member.setUserId("nomal");
		String tmCode = request.getParameter("tmCode");
		ts.updateByTmCode(member, tmCode);
		
		request.getRequestDispatcher("/team/main").forward(request, response);
	}
	private void joinTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/join-team").forward(request, response);
	}
	private void teamMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/main").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
