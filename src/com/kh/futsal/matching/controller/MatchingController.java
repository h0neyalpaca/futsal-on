package com.kh.futsal.matching.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
			matchRegister(request,response);
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
			modify(request,response);
			break;
		case "team-modify-register":
			modifyRegister(request,response);
			break;
		case "mercenary-modify-register":
			modifyRegister(request,response);
			break;
		case "mercenary-list":
			mercenaryList(request,response);
			break;
		case "mercenary-match-form":
			mercenaryMathForm(request,response);
			break;
		case "mercenary-match-register":
			matchRegister(request,response);
			break;
		case "mercenary-modify":
			modify(request,response);
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
			matchMaster.setCheckMatchTime(matchTimeCheck(matchMaster.getMatchDate(),matchMaster.getMatchTime()));
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
		}
		
		//matchList.sort(Comparator.comparing(MatchMaster::getTmRating,Comparator.reverseOrder()));
		//????????? db??? ????????????????????? ????????? ???????????? ?????? ?????? dto??? ?????? ????????? ?????? ??? ????????????.
		List<MatchMaster> sortedList = stateAndRatingSort(matchList);
		
		
		
		request.setAttribute("matchList", sortedList);
		
		if (match.equals("team")) {
			request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
		}else if(match.equals("mercenary")) {
			request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
		}
		
		
	}
	

	private List stateAndRatingSort(List<MatchMaster> matchList) {
		List<MatchMaster> firstList = new ArrayList<MatchMaster>();
		List<MatchMaster> secondList = new ArrayList<MatchMaster>();
		List<MatchMaster> res = new ArrayList<MatchMaster>();
		
		for (MatchMaster matchMaster : matchList) {
			if (matchMaster.getState()==0) {
				firstList.add(matchMaster);
			}else {
				secondList.add(matchMaster);
			}
		}
		
		for (MatchMaster matchMaster : firstList) {
			System.out.println(matchMaster);
		}
		//?????? ????????? sort
		firstList.sort(Comparator.comparing(MatchMaster::getTmRating,Comparator.reverseOrder()));
		secondList.sort(Comparator.comparing(MatchMaster::getTmRating,Comparator.reverseOrder()));
		
		//??????
		res.addAll(firstList);
		res.addAll(secondList);
		
		
		
		//??????
		return res;
		
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
			matchMaster.setCheckMatchTime(matchTimeCheck(matchMaster.getMatchDate(),matchMaster.getMatchTime()));
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
		}
		request.setAttribute("matchList", matchList);
		
		if (match.equals("NULL")) {
			request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
		}else if(match.equals("NOT NULL")) {
			request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
		}
		
	}


	private void modifyRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String delAndModify = request.getParameter("modify");
		String matchIdx = request.getParameter("matchIdx");
		String match = request.getParameter("match");
		MatchMaster matchMaster = new MatchMaster();
		int res = 0;
		System.out.println("??????");
		System.out.println(matchIdx);
		if (delAndModify.equals("??????")) {
			if (match.equals("mercenary")) {
				String mercenary = request.getParameter("mercenary");
				matchMaster.setMatchNum(Integer.parseInt(mercenary));
			}
			String localCode = request.getParameter("localCode");
			String detailAddress = request.getParameter("detailAddress");
			int size = Integer.parseInt(request.getParameter("size"));
			
			String expense = request.getParameter("cost");
			String grade = request.getParameter("level");
			String content = request.getParameter("content");
			
			String matchDate = request.getParameter("date");
			String matchTime = request.getParameter("time");
			String userId = request.getParameter("userId");
			
			
			
			
			matchMaster.setLocalCode(localCode);
			matchMaster.setAddress(detailAddress);
			matchMaster.setMmIdx(matchIdx);
			matchMaster.setExpense(expense);
			matchMaster.setGrade(grade);
			matchMaster.setTmMatch(size);
			if (match.equals("team")) {
				matchMaster.setTitle(detailAddress+" ?????? ?????? ????????????!");
			}else if(match.equals("mercenary")) {
				matchMaster.setTitle(detailAddress+" ?????? ?????????!");
			}
			matchMaster.setMatchTime(matchTime);
			matchMaster.setMatchDate(matchDate);
			matchMaster.setContent(content);
		
			if (res == matchingService.matchModify(matchMaster,match)) {
				request.setAttribute("msg", "????????? ?????????????????????.");
				request.setAttribute("url", "/team/managing/team-board");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}
			alarmService.deleteAlarm(matchIdx);
			alarmService.insertAlarm(matchMaster, userId);
			request.setAttribute("msg", "????????? ????????? ?????????????????????.");
			request.setAttribute("url", "/team/managing/team-board");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}else if (delAndModify.equals("??????")) {			
			if (res == matchingService.matchDel(matchIdx)) {
				request.setAttribute("msg", "????????? ?????????????????????.");
				request.setAttribute("url", "/team/managing/team-board");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}
			alarmService.deleteAlarm(matchIdx);
			request.setAttribute("msg", "????????? ????????? ?????????????????????.");
			request.setAttribute("url", "/team/managing/team-board");
			request.getRequestDispatcher("/common/result").forward(request, response);
		} else if (delAndModify.equals("??????")) {
			if (res == matchingService.matchCancel(matchIdx,request.getParameter("hostCode"),request.getParameter("rivalCode"))) {
				//alarmService.deleteAlarm(matchIdx); //?????? ????????? ??????
				request.setAttribute("msg", "????????? ?????????????????????.");
				request.setAttribute("url", "/team/managing/team-board");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}
			request.setAttribute("msg", "????????? ????????? ?????????????????????.");
			request.setAttribute("url", "/team/managing/team-board");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
	}


	//????????????
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
		
		//?????? ???????????? ????????? ??????????????? ???????????? ???????????? 1????????? ?????? ?????? ??????????????? ?????? ?????? ??????.
		if (matchTimeCheck(matchDate,matchTime)) {
			if (match.equals("team")) {
				request.setAttribute("msg","???????????? ?????? ?????? ???????????????.(???????????? 1?????? ????????? ????????????)");
				request.setAttribute("url", "/matching/team/team-list");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}else if (match.equals("mercenary")) {
				request.setAttribute("msg","???????????? ?????? ?????? ???????????????.(???????????? 1?????? ????????? ????????????)");
				request.setAttribute("url", "/matching/mercenary/mercenary-list");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}
			
		}
		
		
		
		//?????? ?????? ??????
		//???????????? ???????????? ????????????
		//???????????? ?????? ??? ?????? ????????? ????????????		
		if (match.equals("team")) {
			requsetTeamCode = matchingService.checkRequset(userId);
			
			if (requsetTeamCode == null) {
				request.setAttribute("msg","?????? ???????????? ????????? ??????????????? ????????????.?????? ?????????????????? ?????? ??????????????????!");
				request.setAttribute("url", "/matching/team/team-list");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}else if (requsetTeamCode.equals(tmCode)) {
				response.sendRedirect("matching/team/team-list?err=1");
				return;
			}
			matchGame.setApplicantCode(requsetTeamCode);
		
		}else if (match.equals("mercenary")) {
			//?????????????????? ?????? ????????? ????????? ?????? ???????????? ???????????? ??????
			//????????? ?????????????????? ???????????? ???????????? ???????????? ????????? ??????????????? ???????????? ????????? ????????????
			//?????????????????? ??????????????? ???????????? ???????????? ???????????? ????????????.
			String checkMatchIdx = matchingService.checkMatchIdx(sb.toString(),userId);
			
			if (checkMatchIdx.equals(matchIdx)) {
				request.setAttribute("msg","?????? ???????????? ????????? ???????????????.");
				request.setAttribute("url", "/matching/mercenary/mercenary-list");
				request.getRequestDispatcher("/common/result").forward(request, response);
				return;
			}
			
			if (hostId.equals(userId)) {
				request.setAttribute("msg","????????? ???????????? ??????????????? ????????????.");
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
		
		
		//??????????????? ??????
		if (res == matchingService.matchGameRegister(matchGame,match)) {
			request.setAttribute("msg", "????????? ??????????????????.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
		//???????????? ??????
		if (match.equals("mercenary")) {
			String matchNum = request.getParameter("matchNum");
			matchMaster.setMatchNum(Integer.parseInt(matchNum));
			System.out.println(matchMaster.getMatchNum()-1);
			if (0==(matchMaster.getMatchNum()-1)) {
				matchingService.matchRequset(matchIdx,"end");
			}else {
				matchingService.matchRequset(matchIdx,match);
			}
		}else {
			matchingService.matchRequset(matchIdx,match);
		}
		
		
		//team??? ????????? ??????
		//????????? ?????? ??????
		if (match.equals("team")) {
			matchingService.matchUpdate(requsetTeamCode);
		}
		//????????? ?????? ??????
		matchingService.matchUpdate(tmCode);
		
		request.setAttribute("msg", "????????? ?????????????????????.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/common/result").forward(request, response);
	
		
	}


	public boolean matchTimeCheck(String matchDate,String matchTime) {
		LocalDate dateNow = LocalDate.now();
		LocalTime timeNow = LocalTime.now();

		String[] date = matchDate.split("-");
		String[] time = matchTime.split(":");
		
		
		//?????? ?????? ?????? ???????????? ??? ????????? ????????????
		int nowYear = dateNow.getYear();
	
		if (nowYear > Integer.parseInt(date[0])) {
			return true;
		}
		
		//??? ??????
		int nowMonth = dateNow.getMonthValue();
		
		if (nowMonth > Integer.parseInt(date[1])) {
			return true;
		}
		//??? ??????
		int nowDay = dateNow.getDayOfMonth();
	
		if (nowDay > Integer.parseInt(date[2])) {
			return true;
		}else if(nowDay == Integer.parseInt(date[2])) {
			//?????? ?????? 1?????? ??? ???????????? ??????
			int hour = timeNow.getHour();
		
			if (hour > (Integer.parseInt(time[0])-1)) {
				return true;
			}
			
			//??? ??????
			int minute = timeNow.getMinute();
			

			if (minute > Integer.parseInt(time[1])) {
				return true;
			}
		}
		

		return false;
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
			return;
		}else if(match.equals("NOT NULL")) {
			request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/index").forward(request, response);
		
	}


	private void matchRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MatchMaster matchMaster = new MatchMaster();
		StringBuffer sb = new StringBuffer();
		
		String match = request.getParameter("match");
		
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
		
		
		System.out.println(size); 
		System.out.println(localCode);
		
		sb.append(matchDate+" ");
		sb.append(matchTime);
		
		
		if (match.equals("mercenary")) {
			String mercenary = request.getParameter("mercenary");
			matchMaster.setMatchNum(Integer.parseInt(mercenary));
		}
		matchMaster.setUserId(userId);
		matchMaster.setTmCode(teamCode);
		matchMaster.setLocalCode(localCode);
		matchMaster.setAddress(detailAddress);
		matchMaster.setExpense(expense);
		matchMaster.setGrade(grade);
		matchMaster.setTmMatch(size);
		
		if (match.equals("team")) {
			matchMaster.setTitle(detailAddress+" ?????? ?????? ????????????!");
		}else if(match.equals("mercenary")) {
			matchMaster.setTitle(detailAddress+" ?????? ?????????!");
		}
		
		matchMaster.setMatchTime(matchTime);
		matchMaster.setMatchDate(matchDate);
		matchMaster.setContent(content);
		
		matchingService.matchRegister(matchMaster,match);
		

		
		alarmService.regAlarm(matchMaster, userId);
		if (match.equals("team")) {
			request.setAttribute("msg", "????????? ????????? ?????????????????????.");
			request.setAttribute("url", "/matching/team/team-list");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}else if(match.equals("mercenary")) {
			request.setAttribute("msg", "??????????????? ????????? ?????????????????????.");
			request.setAttribute("url", "/matching/mercenary/mercenary-list");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		
	
	}


	
	private void mercenaryMathForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/mercenary/mercenary-match-form").forward(request, response);
	}
	private void mercenaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MatchMaster> matchList = matchingService.mercenaryListView(); 	
		for (MatchMaster matchMaster : matchList) {
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
			matchMaster.setCheckMatchTime(matchTimeCheck(matchMaster.getMatchDate(),matchMaster.getMatchTime()));
			if (matchMaster.isCheckMatchTime()) {
				matchingService.matchRequset(matchMaster.getMmIdx(),"team");
			}	
		}
		
		request.setAttribute("matchList", matchList);
		
		
		request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String matchIdx = request.getParameter("matchIdx");
		
		
		System.out.println(matchIdx);
		//?????? ????????? ???????????? ?????? ????????? ????????????.
		MatchMaster matchModify = matchingService.getMatchModify(matchIdx);
		
		if (matchModify == null) {
			request.setAttribute("msg", "????????? ??????????????????.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/common/result").forward(request, response);
		}
		//????????? ????????? ????????????
		System.out.println("matchNum?????? null?????? ?????? : " + matchModify.getMatchNum());
		
		if (matchModify.getMatchNum() != 0) {
			request.setAttribute("matchIdx", matchIdx);
			request.setAttribute("matchModify", matchModify);
			request.getRequestDispatcher("/matching/mercenary/mercenary-modify").forward(request, response);
			
		}else {
			
			request.setAttribute("matchIdx", matchIdx);
			request.setAttribute("matchModify", matchModify);
			request.getRequestDispatcher("/matching/team/team-modify").forward(request, response);
			
		}
		
		
	}
	private void teamMathForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/team/team-match-form").forward(request, response);
	}
	private void teamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MatchMaster> matchList = matchingService.matchListView(); 	
		for (MatchMaster matchMaster : matchList) {
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
			matchMaster.setCheckMatchTime(matchTimeCheck(matchMaster.getMatchDate(),matchMaster.getMatchTime()));
			if (matchMaster.isCheckMatchTime()) {
				matchingService.matchRequset(matchMaster.getMmIdx(),"team");
			}
		}
		
		request.setAttribute("matchList", matchList);
		
		
		request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
