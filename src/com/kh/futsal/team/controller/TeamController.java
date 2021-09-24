package com.kh.futsal.team.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.member.model.service.MemberService;
import com.kh.futsal.team.model.dto.Team;
import com.kh.futsal.team.model.service.TeamService;

@WebServlet("/team/*")
public class TeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TeamService ts = new TeamService();
	private MemberService ms = new MemberService();
	
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
		case "break-team":
			breakTeam(request,response);
			break;
		case "leave-team":
			leaveTeam(request,response);
			break;

		default:
		}
	}

	//팀 탈퇴 O
	private void leaveTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateMemberForLeaveTeam(request.getParameter("userId"));
		
		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = "팀 탈퇴 처리가 완료되었습니다.";
			Member member = ms.selectMemberById(request.getParameter("userId"));
			request.getSession().setAttribute("authentication", member);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache, no-store");
		PrintWriter pw= response.getWriter();
		pw.print(msg);
	}
	
	//팀 해체 O
	private void breakTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> tmMembers = ts.selectTmMembers(request.getParameter("tmCode"));
		int res = 0;
		for (Member member : tmMembers) {
			res = ts.updateMemberForLeaveTeam(member.getUserId());
			if(res < 1) {
				return;
			}
		}
		res = ts.updateDelDateForLeaveTeam(request.getParameter("tmCode"));
		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = "팀 해체가 완료되었습니다.";
			Member member = (Member) request.getSession().getAttribute("authentication");
			member.setTmCode(null);
			member.setGrade("ME00");
			request.getSession().setAttribute("authentication", member);
			request.getSession().removeAttribute("team");
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache, no-store");
		PrintWriter pw= response.getWriter();
		pw.print(msg);
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
	
	//팀원 등급 변경 O
	private void manageGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateGrade(request.getParameter("userId"),request.getParameter("grade"));
		
		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = request.getParameter("userId")+"님의 등급 변경이 완료되었습니다.";
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache, no-store");
		PrintWriter pw= response.getWriter();
		pw.print(msg);
	}
	
	//팀원 추방 O
	private void manageExpulsion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateMemberForLeaveTeam(request.getParameter("userId"));

		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = request.getParameter("userId")+"님을 추방하였습니다.";
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache, no-store");
		PrintWriter pw= response.getWriter();
		pw.print(msg);
	}
	
	//팀장 위임 O
	private void manageDelegation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		Member member = (Member) request.getSession().getAttribute("authentication");

		String userId = request.getParameter("userId");
		String msg = "처리 중 오류가 발생하였습니다.";
		int res = ts.updateGrades(userId,team.getManagerId());
		if (res > 0) {
			res = ts.updateTmManager(userId,team.getTmCode());
			if (res > 0) {
				msg = userId+"님에게 팀장을 위임하였습니다.";
				member.setGrade("ME01");
				request.getSession().setAttribute("authentication", member);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache, no-store");
		PrintWriter pw= response.getWriter();
		pw.print(msg);
	}
	
	//팀원 목록 O
	private void teamManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		request.setAttribute("tmMembers", ts.selectTmMembers(team.getTmCode()));
		request.getRequestDispatcher("/team/managing/manage").forward(request, response);
	}
	
	private void teamModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/modify").forward(request, response);
	}
	
	//팀 생성 X
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

		int res = ts.insertTeam(team);
		if(res > 0) {
			res = ts.updateMemberIntoTeam(member, team);
			if(res > 0) {
				member.setTmCode(tmCode);
				member.setGrade("ME03");
				request.getSession().setAttribute("authentication", member);
				response.sendRedirect("/team/managing/modify?result=2");
			}
		}
	}
	
	private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/create-form").forward(request, response);
	}
	
	//팀 가입 O
	private void joinFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		Team team = ts.selectTeamByTmCode(request.getParameter("tmCode"));
		
		if(team.getTmCode()==null || team.getDelDate()!=null) {
			response.sendRedirect("/team/join-team?err=1");
			return;
		}
		int res = ts.updateMemberIntoTeam(member, team);
		if(res > 0) {			
			member.setTmCode(team.getTmCode());
			member.setGrade("ME01");
			request.getSession().setAttribute("authentication", member);
			response.sendRedirect("/team/managing/modify?result=1");
		}
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
