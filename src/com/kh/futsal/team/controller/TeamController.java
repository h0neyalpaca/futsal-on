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
import com.oreilly.servlet.MultipartRequest;

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
		//아무나
		case "main":
			teamMain(request,response);
			break;
		case "join-team":
			joinTeam(request,response);
			break;
		case "join":
			joinFunc(request,response);
			break;
		case "create-form":
			createForm(request,response);
			break;
		case "create":
			createFunc(request,response);
			break;
		//팀 소속된 사람만
		case "modify":
			teamModify(request,response);
			break;
		case "manage":
			teamManage(request,response);
			break;
		case "manage-delegation":
			manageDelegation(request,response);
			break;
		case "manage-expulsion":
			manageExpulsion(request,response);
			break;
		case "manage-grade":
			manageGrade(request,response);
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
	
	private void manageGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ts.updateGrade(request.getParameter("userId"),request.getParameter("grade"));
	}
	
	private void manageExpulsion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ts.updateExpulsion(request.getParameter("userId"));
	}
	
	private void manageDelegation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		Member member = (Member) request.getSession().getAttribute("authentication");

		int res = ts.updateGrades(request.getParameter("userId"),team.getManagerId());
		if (res > 0) {
			res = ts.updateTmManager(request.getParameter("userId"),team.getTmCode());
			if (res > 0) {
				member.setGrade("ME01");
				request.getSession().setAttribute("authentication", member);
			}
		}
	}
	
	private void teamManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		request.setAttribute("tmMembers", ts.selectTmMembers(team.getTmCode()));
		request.getRequestDispatcher("/team/managing/manage").forward(request, response);
	}
	
	private void teamModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/modify").forward(request, response);
	}
	
	private void createFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		//random code
		String tmCode = ts.createRandomCode(member.getUserId());
		
		Team team = new Team();
		team.setTmCode(tmCode);
		team.setLocalCode(request.getParameter("localCode"));
		team.setManagerId(member.getUserId());
		team.setTmName(request.getParameter("tmName"));
		team.setTmGrade(request.getParameter("tmGrade"));
		team.setTmInfo(request.getParameter("tmInfo"));

		ts.insertTeam(team);
		ts.updateMember(member, team);
		response.sendRedirect("/team/managing/modify?result=2");
	}
	
	private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/create-form").forward(request, response);
	}
	
	private void joinFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		Team team = ts.selectTeamByTmCode(request.getParameter("tmCode"));
		
		if(team.getTmCode()==null || team.getDelDate()!=null) {
			response.sendRedirect("/team/join-team?err=1");
			return;
		}
		ts.updateMember(member, team);
		response.sendRedirect("/team/managing/modify?result=1");
	}
	
	private void joinTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/join-team").forward(request, response);
	}
	
	private void teamMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		//팀이 있는 회원은 팀관리 화면으로, 없는 회원은 메인으로 보내기
		if(team != null) {
			request.getRequestDispatcher("/team/managing/modify").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/team/main").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
