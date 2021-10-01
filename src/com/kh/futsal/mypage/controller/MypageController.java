package com.kh.futsal.mypage.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		case "alarm-check":
			alarmCheck(request,response);
			break;
		default:
		}
	}
	
	//매치신청 취소
	private void myApplicationDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mgIdx = request.getParameter("mgIdx");
		MatchGame match = matchingService.selectMatch(mgIdx);

		boolean flag = checkDate(match);
		
		if(flag) { 
			 matchingService.deleteMyApplicant(mgIdx,match);
			 request.setAttribute("msg","신청이 취소되었습니다"); 
		}else {
			request.setAttribute("msg","경기시작이 얼마남지 않아 취소가 불가합니다"); 
		}
	    request.setAttribute("url", "/mypage/my-application");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	//게임 시작 4시간전에는 취소불가해서 시간 확인하는 메소드
	private boolean checkDate(MatchGame match){
		String matchDayTime= match.getMatchDate();
		String matchDay = matchDayTime.substring(0,10);
		int matchTime = Integer.parseInt(matchDayTime.substring(11,13));
		
		String today = getToday();
		String day = today.substring(0,10);
		int time =  Integer.parseInt(today.substring(11,13));
		
		if(matchDay.equals(day)) {
			if((matchTime -4) <= time) {
				return false;
			}
		}
		return true;
	}
	
	//닉네임 중복체크
	private void nickCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = request.getParameter("nickName");
		Member member = memberService.selectMemberByNick(nickName);
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
	}
	
	//비밀번호 일치 체크
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
	
	//탈퇴처리
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
	
	//회원정보 수정
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
	
	//용병신청 리스트
	private void myApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		List<MatchGame> mgList = matchingService.matchMgList(userId);
		List<MatchMaster> matchList = matchingService.matchGameList(mgList);
		List<Team> teamInfos = new ArrayList<Team>();
		Map<String,Object> matchTeamList =  new HashMap<String, Object>();
		
		for (int i = 0; i < matchList.size(); i++) {
			Team team = teamService.selectTeamByTmCode(matchList.get(i).getTmCode());
			team.setTmRating(teamService.selectTmAvgRating(matchList.get(i).getTmCode()));
			teamInfos.add(team);
		}		
		
		matchTeamList.put("mgList",mgList);
		matchTeamList.put("matchList", matchList);
		matchTeamList.put("teamList",teamInfos);
		request.setAttribute("datas", matchTeamList);
		request.getRequestDispatcher("/mypage/my-application").forward(request, response);
	}
	
	//개인알람리스트
	private void personalNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		List<String> times = new ArrayList<String>();
		
		List<Alarm> alarms = alarmService.selectNoticetList(userId);
		for (int i = 0; i < alarms.size(); i++) {
			times.add(checkAlarmState(alarms.get(i)));
		}
		request.setAttribute("alarms", alarms);
		request.setAttribute("times",times);
		request.getRequestDispatcher("/mypage/personal-notice").forward(request, response);
	}

	//알람 상태 업데이트용
	private String checkAlarmState(Alarm alarm) {
		String alarmDate = alarm.getNtDate();
		int alarmTime = Integer.parseInt(alarm.getMatchTime().substring(0, 2));
				
		String today = getToday();
		String day = today.substring(0,10);
		int time =  Integer.parseInt(today.substring(11,13));
		
		if(alarmDate.equals(day) && (alarmTime -4) <= time) {
			alarmService.updateAlarmIsStart(alarm.getNtIdx());
		}
		return (alarmTime-4)+":"+ alarm.getMatchTime().substring(3);
	}
	
	//알람 확인 상태 업데이트
	private void alarmCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ntIdx = request.getParameter("ntIdx");
		alarmService.updateAlarm(ntIdx);
		response.sendRedirect("/mypage/my-application");
	}
	
	//오늘 날짜와 시간 가져오는 메소드
	private String getToday() {
		LocalDateTime today = LocalDateTime.now();
		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String todayTime = today.format(Formatter);
		return todayTime;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
