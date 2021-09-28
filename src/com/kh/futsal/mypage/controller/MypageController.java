package com.kh.futsal.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.alarm.model.dto.Alarm;
import com.kh.futsal.alarm.model.service.AlarmService;
import com.kh.futsal.matching.model.dto.MatchGame;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.matching.model.service.MatchingService;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.member.model.service.MemberService;
import com.kh.futsal.team.model.dto.Team;
import com.kh.futsal.team.model.service.TeamService;

@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private MatchingService matchingService = new MatchingService();
    private TeamService teamService = new TeamService();   
    private AlarmService alarmService = new AlarmService();   
	
    public MypageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length-1]) {
		
		case "personal-notice":
			personalNotice(request,response);
			break;
		case "my-application":
			myApplication(request,response);
			break;
		case "my-application-delete":
			myApplicationDelete(request,response);
			break;
		case "modify-form":
			mypageModifyForm(request,response);
			break;
		case "modify":
			mypageModify(request,response);
			break;
		case "leave-id":
			leaveId(request,response);
			break;
		case "leave":
			leave(request,response);
			break;
		case "pw-check":
			pwCheck(request,response);
			break;
		case "nick-check":
			nickCheck(request,response);
			break;
		default:
		}
	}

	private void myApplicationDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mgIdx = request.getParameter("mgIdx");
		
		boolean flag = checkDate(mgIdx);
		if(flag) {
			matchingService.deleteMyApplicant(mgIdx);
			request.setAttribute("msg","신청이 취소되었습니다");
		}else {
			request.setAttribute("msg","경기시작이 얼마남지 않아 취소가 불가합니다");
		}
		
	    request.setAttribute("url", "/mypage/my-application");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	private boolean checkDate(String mgIdx) throws ServletException, IOException {
		MatchGame match = matchingService.selectMatch(mgIdx);
		
		String matchDay= match.getMatchDate();
		int matchMonth = Integer.parseInt(matchDay.substring(5, 7));
		int matchDate = Integer.parseInt(matchDay.substring(8, 10));
		int matchHour = Integer.parseInt(matchDay.substring(11, 13));
		
		LocalDateTime today = LocalDateTime.now();
		int month = today.getMonthValue();
		int date =  today.getDayOfMonth();
		int hour = today.getHour();
		
		if(matchMonth == month && matchDate == date) {
			if((matchHour-4) <= hour) {
				return false;
			}
		}else if(matchMonth == month && matchDate < date){
			return false;
		}else if(matchMonth > month){
			return false;
		}
		return true;
	}

	private void nickCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = request.getParameter("nickName");
		
		Member member = memberService.selectMemberByNick(nickName);
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
		
	}

	private void pwCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String password = member.getPassword();
		String checkPw = request.getParameter("password");
		
		if(password.equals(checkPw)) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
		
	}

	private void leave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		memberService.leaveID(userId);
		
		request.getSession().removeAttribute("authentication");
		
		request.setAttribute("msg","탈퇴처리 되었습니다");
	    request.setAttribute("url", "/index");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	private void leaveId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/leave-id").forward(request, response);
		
	}
	
	private void mypageModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		if(!request.getParameter("new-password").equals("")) {
			member.setPassword(request.getParameter("new-password"));
		}
		
		member.setUserNick(request.getParameter("nickName"));
		member.setTell(request.getParameter("tell"));
		member.setCapacity(request.getParameter("capacity"));
		
		memberService.updateMember(member);
		
		request.setAttribute("msg","회원정보 수정이 완료 되었습니다");
	    request.setAttribute("url", "/mypage/modify-form");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	private void mypageModifyForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/modify-form").forward(request, response);
	}
	
	private void myApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		List<MatchMaster> matchList = matchingService.matchGameList(userId);
		List<Team> teamInfos = new ArrayList<Team>();
		List<MatchGame> mgList = matchingService.matchMgList(userId);
		
		Map<String,Object> matchTeamList =  new HashMap<String, Object>();
		
		
		for (int i = 0; i < matchList.size(); i++) {
			teamInfos.add(teamService.selectTeamByTmCode(matchList.get(i).getTmCode()));
		}		
		
		
		matchTeamList.put("matchList", matchList);
		matchTeamList.put("teamList",teamInfos);
		matchTeamList.put("mgList",mgList);
		
		request.setAttribute("datas", matchTeamList);
		request.getRequestDispatcher("/mypage/my-application").forward(request, response);
	}
	
	private void personalNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		List<Alarm> alarms = alarmService.selectNoticetList(userId);

		request.setAttribute("alarms", alarms);
		request.getRequestDispatcher("/mypage/personal-notice").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
