package com.kh.futsal.matching.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.matching.model.service.MatchingService;
import com.kh.futsal.team.model.dto.Team;

/**
 * Servlet implementation class MatchingController
 */
@WebServlet("/matching/*")
public class MatchingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MatchingService matchingService = new MatchingService();
       

    public MatchingController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");
		System.out.println(uriArr[uriArr.length-1]);
		switch (uriArr[uriArr.length-1]) {
		
		case "team-list":
			teamList(request,response);
			break;
		case "team-match-form":
			teamMathForm(request,response);
			break;
		case "team-match-register":
			teamMatchRegister(request,response);
			break;
		case "team-match-search":
			teamMatchSearch(request,response);
			break;
		case "team-modify":
			teamModify(request,response);
			break;
		case "mercenary-list":
			mercenaryList(request,response);
			break;
		case "mercenary-match-form":
			mercenaryMathForm(request,response);
			break;
		case "mercenary-modify":
			mercenaryModify(request,response);
			break;


		default:
		}
		
	}


	private void teamMatchSearch(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String localCode = request.getParameter("localCode");
		String date = request.getParameter("date");
		String level = request.getParameter("level");
		
		
		List<MatchMaster> matchList = matchingService.matchListSearch(localCode,date,level); 	
		
		
		request.setAttribute("matchList", matchList);
		
		request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
	}


	private void teamMatchRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String localCode = request.getParameter("localCode");
		String detailAddress = request.getParameter("detailAddress");
		int size = Integer.parseInt(request.getParameter("size"));
		
		
		String expense = request.getParameter("cost");
		String grade = request.getParameter("level");
		String content = request.getParameter("content");
		
		String matchDate = request.getParameter("date");
		String matchTime = request.getParameter("time");
		StringBuffer sb = new StringBuffer();
		
		System.out.println(size); 
		System.out.println(localCode);
		
		sb.append(matchDate+" ");
		sb.append(matchTime);
		
		MatchMaster matchMaster = new MatchMaster();
		matchMaster.setLocalCode(localCode);
		matchMaster.setAddress(detailAddress);
		matchMaster.setExpense(expense);
		matchMaster.setGrade(grade);
		matchMaster.setTmMatch(size);
		matchMaster.setTitle(detailAddress+" 매치 상대 구합니다!");
		matchMaster.setMatchTime(matchTime);
		matchMaster.setMatchDate(matchDate);
		matchMaster.setContent(content);
		
		matchingService.matchRegister(matchMaster);
		request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
	
	}


	private void mercenaryModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/mercenary/mercenary-modify").forward(request, response);
	}
	private void mercenaryMathForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/mercenary/mercenary-match-form").forward(request, response);
	}
	private void mercenaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
	}
	
	private void teamModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/team/team-modify").forward(request, response);
	}
	private void teamMathForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/team/team-match-form").forward(request, response);
	}
	private void teamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MatchMaster> matchList = matchingService.matchListView();
		
		request.setAttribute("matchList", matchList);
		
		
		request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
