package com.kh.futsal.matching.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.matching.model.dto.MatchGame;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.matching.model.service.MatchingService;

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
		case "subscription":
			MatchRequest(request,response);
			break;
		case "team-modify":
			teamModify(request,response);
			break;
		case "team-modify-register":
			teamModifyRegister(request,response);
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

	private void teamModifyRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String delAndModify = request.getParameter("modify");
		String matchIdx = request.getParameter("matchIdx");
		int res = 0;
		System.out.println("동작");
		if (delAndModify.equals("수정")) {
			
			String localCode = request.getParameter("localCode");
			String detailAddress = request.getParameter("detailAddress");
			int size = Integer.parseInt(request.getParameter("size"));
			
			String expense = request.getParameter("cost");
			String grade = request.getParameter("level");
			String content = request.getParameter("content");
			
			String matchDate = request.getParameter("date");
			String matchTime = request.getParameter("time");
			
			
			MatchMaster matchMaster = new MatchMaster();
			
			matchMaster.setLocalCode(localCode);
			matchMaster.setAddress(detailAddress);
			matchMaster.setMmIdx(matchIdx);
			matchMaster.setExpense(expense);
			matchMaster.setGrade(grade);
			matchMaster.setTmMatch(size);
			matchMaster.setTitle(detailAddress+" 매치 상대 구합니다!");
			matchMaster.setMatchTime(matchTime);
			matchMaster.setMatchDate(matchDate);
			matchMaster.setContent(content);
			if (res == matchingService.matchModify(matchMaster)) {
				request.setAttribute("msg", "오류가 발생하였습니다.");
				request.setAttribute("url", "/team/managing/team-board");
				request.getRequestDispatcher("/common/result").forward(request, response);
			}
			
			request.setAttribute("msg", "매치글 수정을 완료하였습니다.");
			request.setAttribute("url", "/team/managing/team-board");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}else if (delAndModify.equals("삭제")) {			
			if (res == matchingService.matchDel(matchIdx)) {
				request.setAttribute("msg", "오류가 발생하였습니다.");
				request.setAttribute("url", "/team/managing/team-board");
				request.getRequestDispatcher("/common/result").forward(request, response);
			}
			request.setAttribute("msg", "매치글 삭제를 완료하였습니다.");
			request.setAttribute("url", "/team/managing/team-board");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		

		
	}


	//매치신청
	private void MatchRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int res = 0;
		String matchIdx = request.getParameter("matchIdx");
		String userId = request.getParameter("userId");
		String tmCode = request.getParameter("tmCode");
		String matchDate = request.getParameter("matchDate");
		String matchTime = request.getParameter("matchTime");
		StringBuffer sb = new StringBuffer();
		sb.append(matchDate);
		sb.append(" "+matchTime);
		//신청자와 주최자가 같을경우
		System.out.println(matchDate);
		
		//팀코드로 비교 후 같은 팀이면 에러처리
		String requsetTeamCode = matchingService.checkRequset(userId);
		System.out.println(requsetTeamCode);
		if (requsetTeamCode.equals(tmCode)) {
			response.sendRedirect("matching/team/team-list?err=1");
			return;
		}
		MatchGame matchGame = new MatchGame();
		matchGame.setMmIdx(matchIdx);
		matchGame.setMatchDate(sb.toString());
		matchGame.setApplicantCode(requsetTeamCode);
		
		

		//매치게임에 등록
		if (res == matchingService.matchGameRegister(matchGame)) {
			request.setAttribute("msg", "에러가 발생했습니다.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
		//모집상태 변경
		matchingService.matchRequset(matchIdx);
		
		//team에 전적수 추가
		//신청팀 전적 추가
		matchingService.matchUpdate(requsetTeamCode);
		//주최팀 전적 추가
		matchingService.matchUpdate(tmCode);
		
		request.setAttribute("msg", "매치가 성사되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
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
		String userId = request.getParameter("userId");
		String teamCode = matchingService.checkRequset(userId);
		
		
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
		
		matchMaster.setUserId(userId);
		matchMaster.setTmCode(teamCode);
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
		request.setAttribute("msg", "매치글 작성이 완료되었습니다.");
		request.setAttribute("url", "/matching/team/team-list");
		request.getRequestDispatcher("/common/result").forward(request, response);
	
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
		String matchIdx = request.getParameter("matchIdx");
		System.out.println(matchIdx);
		//매치 번호를 이용해서 글의 정보를 가져온다.
		MatchMaster matchModify = matchingService.getMatchModify(matchIdx);
		
		if (matchModify == null) {
			request.setAttribute("msg", "에러가 발생했습니다.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		//가져온 정보를 뿌려준다
		request.setAttribute("matchIdx", matchIdx);
		request.setAttribute("matchModify", matchModify);
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
