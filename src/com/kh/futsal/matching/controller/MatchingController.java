package com.kh.futsal.matching.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.alarm.model.service.AlarmService;
import com.kh.futsal.matching.model.dto.MatchGame;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.matching.model.service.MatchingService;
import com.kh.futsal.team.model.service.TeamService;

/**
 * Servlet implementation class MatchingController
 */
@WebServlet("/matching/*")
public class MatchingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MatchingService matchingService = new MatchingService();
	AlarmService alarmService = new AlarmService();   
	TeamService teamService = new TeamService();

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
			MatchSearch(request,response);
			break;
		case "recent":
			SearchRecent(request,response);
			break;
		case "rating":
			SearchRating(request,response);
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

	private void SearchRating(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<MatchMaster> matchList = null;
		String match = request.getParameter("match");
		
		if (match.equals("team")) {
			matchList = matchingService.matchListView(); 	
		}else if (match.equals("mercenary")) {
			matchList = matchingService.mercenaryListView();
		}
		for (MatchMaster matchMaster : matchList) {
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
		}
		matchList.sort(Comparator.comparing(MatchMaster::getTmRating,Comparator.reverseOrder()));
		
		request.setAttribute("matchList", matchList);
		
		if (match.equals("team")) {
			request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
		}else if(match.equals("mercenary")) {
			request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
		}
	}
	

	private void SearchRecent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String match = request.getParameter("match");
		System.out.println("recent : " + match);
		
		if (match.equals("team")) {
			match = "NULL";
		}else if(match.equals("mercenary")) {
			match = "NOT NULL";
		}
		
		
		List<MatchMaster> matchList = matchingService.RecentView(match);
		
		for (MatchMaster matchMaster : matchList) {
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
		}
		request.setAttribute("matchList", matchList);
		
		if (match.equals("NULL")) {
			request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
		}else if(match.equals("NOT NULL")) {
			request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
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
				alarmService.deleteAlarm(matchIdx);
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
		String title = request.getParameter("title");
		String match = request.getParameter("match");
		String hostId = request.getParameter("hostId");
		String requsetTeamCode = null;
		MatchGame matchGame = new MatchGame();
		MatchMaster matchMaster = new MatchMaster();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(matchDate);
		sb.append(" "+matchTime);
		
		
		//매치 유형 확인
		//신청자와 주최자가 같을경우
		//팀코드로 비교 후 같은 팀이면 에러처리		
		if (match.equals("team")) {
			requsetTeamCode = matchingService.checkRequset(userId);
			
			if (requsetTeamCode.equals(tmCode)) {
				response.sendRedirect("matching/team/team-list?err=1");
				return;
			}
			matchGame.setApplicantCode(requsetTeamCode);
		
		}else if (match.equals("mercenary")) {
			//용병신청자가 이미 신청한 매치에 다시 신청하지 못하도록 확인
			//신청자 유저아이디를 이용해서 매치번호 가져와서 주최자 매치번호를 비교해서 같으면 에러처리
			//유저아이디와 매치시간을 이용해서 매치번호 특정시켜 가져온다.
			String checkMatchIdx = matchingService.checkMatchIdx(sb.toString(),userId);
			
			if (checkMatchIdx.equals(matchIdx)) {
				request.setAttribute("msg","이미 용병으로 신청한 매치입니다.");
				request.setAttribute("url", "/matching/mercenary/mercenary-list");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}
			
			if (hostId.equals(userId)) {
				request.setAttribute("msg","본인이 올린글을 신청하실수 없습니다.");
				request.setAttribute("url", "/matching/mercenary/mercenary-list");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}
			matchGame.setUserId(userId);
		}
		
		
		matchMaster.setMmIdx(matchIdx);
		matchMaster.setTitle(title);
		matchMaster.setMatchDate(matchDate);
		matchMaster.setMatchTime(matchTime);
		
		alarmService.insertAlarm(matchMaster, userId);
		
		matchGame.setMmIdx(matchIdx);
		matchGame.setMatchDate(sb.toString());
		
		
		//매치게임에 등록
		if (res == matchingService.matchGameRegister(matchGame,match)) {
			request.setAttribute("msg", "에러가 발생했습니다.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
		//모집상태 변경
		matchingService.matchRequset(matchIdx,match);
		
		//team에 전적수 추가
		//신청팀 전적 추가
		if (match.equals("team")) {
			matchingService.matchUpdate(requsetTeamCode);
		}
		//주최팀 전적 추가
		matchingService.matchUpdate(tmCode);
		
		request.setAttribute("msg", "매치가 성사되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/common/result").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		

	
		
	}


	private void MatchSearch(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String localCode = request.getParameter("localCode");
		String date = request.getParameter("date");
		String level = request.getParameter("level");
		String match = request.getParameter("match");
		if (match.equals("team")) {
			match = "NULL";
		}else if(match.equals("mercenary")) {
			match = "NOT NULL";
		}
		
		List<MatchMaster> matchList = matchingService.matchListSearch(localCode,date,level,match); 	
		
		
		request.setAttribute("matchList", matchList);
		System.out.println(match);
		
		if (match.equals("NULL")) {
			request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
		}else if(match.equals("NOT NULL")) {
			request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
		}
		
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
		List<MatchMaster> matchList = matchingService.mercenaryListView(); 	
		for (MatchMaster matchMaster : matchList) {
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
		}
		
		request.setAttribute("matchList", matchList);
		
		
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
		for (MatchMaster matchMaster : matchList) {
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
		}
		
		request.setAttribute("matchList", matchList);
		
		
		request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
