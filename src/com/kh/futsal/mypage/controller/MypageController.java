package com.kh.futsal.mypage.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.kh.futsal.common.file.FileDTO;
import com.kh.futsal.common.pagination.PageInfo;
import com.kh.futsal.common.pagination.Pagination;
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
	
	//???????????? ??????
	private void myApplicationDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mgIdx = request.getParameter("mgIdx");
		MatchGame match = matchingService.selectMatch(mgIdx);

		boolean flag = checkDate(match);
		
		if(flag) { 
			 matchingService.deleteMyApplicant(mgIdx,match);
			 request.setAttribute("msg","????????? ?????????????????????"); 
		}else {
			request.setAttribute("msg","??????????????? ???????????? ?????? ????????? ???????????????"); 
		}
	    request.setAttribute("url", "/mypage/my-application");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	//?????? ?????? 4??????????????? ?????????????????? ?????? ???????????? ?????????
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
	
	//????????? ????????????
	private void nickCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = request.getParameter("nickName");
		Member member = memberService.selectMemberByNick(nickName);
		System.out.println(nickName);
		System.out.println((Member) request.getSession().getAttribute("authentication"));
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
	}
	
	//???????????? ?????? ??????
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
	
	//????????????
	private void leave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		memberService.leaveID(userId);
		
		request.getSession().removeAttribute("authentication");
		
		request.setAttribute("msg","???????????? ???????????????");
	    request.setAttribute("url", "/index");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	
	private void leaveId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/leave-id").forward(request, response);
	}
	
	//???????????? ??????
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
		
		request.setAttribute("msg","???????????? ????????? ?????? ???????????????");
	    request.setAttribute("url", "/mypage/modify-form");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	private void mypageModifyForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/modify-form").forward(request, response);
	}
	
	//???????????? ?????????
	private void myApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		int curPage = 0;
		int totalNoticeCnt = 0;
		totalNoticeCnt = matchingService.selectBoardCnt(userId);
				
		String pageNum = request.getParameter("curPage");
		if(pageNum == null) {
			pageNum = "1";
		}
		curPage =  Integer.parseInt(pageNum);
		
		PageInfo page = Pagination.getPageInfo(curPage, totalNoticeCnt);
		
		List<MatchGame> mgList = matchingService.matchMgList(userId, page);
		List<MatchMaster> matchList = matchingService.matchGameList(mgList);
		List<Team> teamInfos = new ArrayList<Team>();
		List<FileDTO> files = new ArrayList<FileDTO>();
		Map<String,Object> matchTeamList =  new HashMap<String, Object>();
		
		for (int i = 0; i < matchList.size(); i++) {
			Team team = teamService.selectTeamByTmCode(matchList.get(i).getTmCode());
			team.setTmRating(teamService.selectTmAvgRating(matchList.get(i).getTmCode()));
			teamInfos.add(team);
			
			if(checkGameEnd(matchList.get(i))) {
				alarmService.updateAlarmIsEnd(matchList.get(i),userId);
				matchList.get(i).setState(2);
			}
		}
		
		for (int j = 0; j < teamInfos.size(); j++) {
			FileDTO file = teamService.selectFile(teamInfos.get(j).getTmCode());
			files.add(file);
		}
		
		matchTeamList.put("mgList",mgList);
		matchTeamList.put("matchList", matchList);
		matchTeamList.put("teamList",teamInfos);
		matchTeamList.put("fileList",files);
		request.setAttribute("datas", matchTeamList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/mypage/my-application").forward(request, response);
	}
	
	private boolean checkGameEnd(MatchMaster match) {
		String alarmDate = match.getMatchDate();
		int alarmTime = Integer.parseInt(match.getMatchTime().substring(0, 2));
		int alarmMonth = Integer.parseInt(alarmDate.substring(5, 7));
		int alarmDay = Integer.parseInt(alarmDate.substring(8, 10));
		
		String today = getToday();
		String day = today.substring(0,10);
		int time =  Integer.parseInt(today.substring(11,13));
		int nowMonth = Integer.parseInt(day.substring(5, 7));
		int nowDate = Integer.parseInt(day.substring(8, 10));
		
		if(alarmDate.equals(day)) {
			if(alarmTime < time) {
					return true;
			}
		}else if(alarmMonth < nowMonth) {
			return true;
		}else if(alarmMonth == nowMonth) {
			if(alarmDay < nowDate) {
				return true;
			}
		}
		return false;
	}
	//?????????????????????
	private void personalNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		List<String> times = new ArrayList<String>();
		
		int curPage = 0;
		int totalNoticeCnt = 0;
		totalNoticeCnt = alarmService.selectBoardCnt(userId);
		
		String pageNum = request.getParameter("curPage");
		if(pageNum == null) {
			pageNum = "1";
		}
		curPage =  Integer.parseInt(pageNum);
		
		PageInfo page = Pagination.getPageInfo(curPage, totalNoticeCnt);
		List<Alarm> alarms = alarmService.selectNoticetList(userId);
		
		List<Alarm> alarmsPage = alarmService.selectAlarmListPage(userId , page);

		for (int i = 0; i < alarms.size(); i++) {
			checkAlarmState(alarms.get(i));
		}
		
		for (int i = 0; i < alarmsPage.size(); i++) {
			times.add(checkAlarmState(alarmsPage.get(i)));
		}
		
		
		request.setAttribute("page", page);
		request.setAttribute("alarms", alarms);
		request.setAttribute("alarmsPage", alarmsPage);
		request.setAttribute("times",times);
		request.getRequestDispatcher("/mypage/personal-notice").forward(request, response);
	}
	
	
	//?????? ?????? ???????????????
	private String checkAlarmState(Alarm alarm) {
		String alarmDate = alarm.getNtDate();
		int alarmTime = Integer.parseInt(alarm.getMatchTime().substring(0, 2));
		int alarmMonth = Integer.parseInt(alarmDate.substring(5, 7));
		int alarmDay = Integer.parseInt(alarmDate.substring(8, 10));
		
		String today = getToday();
		String day = today.substring(0,10);
		int nowMonth = Integer.parseInt(day.substring(5, 7));
		int nowDate = Integer.parseInt(day.substring(8, 10));
		int time =  Integer.parseInt(today.substring(11,13));
		
		
		if(alarmDate.equals(day) && (alarmTime -4) <= time) {
			 return alarmTimeCheck(alarm,alarmTime);
		}else if(alarmMonth < nowMonth) {
			return alarmTimeCheck(alarm,alarmTime);
		}else if(alarmMonth == nowMonth) {
			if(alarmDay < nowDate) {
				return alarmTimeCheck(alarm,alarmTime);
			}
		}else {
			if(alarm.getContent().contains("??????")) {
				return (alarmTime+1)+":"+ alarm.getMatchTime().substring(3);
			}
		}
		return (alarmTime-4)+":"+ alarm.getMatchTime().substring(3);
	}
	
	//?????? ?????? ?????? ????????????
	private void alarmCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ntIdx = request.getParameter("ntIdx");
		alarmService.updateAlarm(ntIdx);
		response.sendRedirect("/mypage/my-application");
	}
	
	//?????? ????????? ?????? ???????????? ?????????
	private String getToday() {
		LocalDateTime today = LocalDateTime.now();
		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String todayTime = today.format(Formatter);
		return todayTime;
	}
	
	private String alarmTimeCheck(Alarm alarm,int alarmTime) {
		
		if((alarm.getIsStart() == 0 && !alarm.getContent().contains("??????"))){
			alarmService.updateAlarmIsStart(alarm.getNtIdx(),alarm);
		}else {
			if(alarm.getContent().contains("??????")) {
				return (alarmTime+1)+":"+ alarm.getMatchTime().substring(3);
			}
		}
		return (alarmTime-4)+":"+ alarm.getMatchTime().substring(3);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
