package com.kh.futsal.team.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.alarm.model.service.AlarmService;
import com.kh.futsal.common.file.FileDTO;
import com.kh.futsal.common.file.FileUtil;
import com.kh.futsal.common.file.MultiPartParams;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.member.model.service.MemberService;
import com.kh.futsal.team.model.dto.Team;
import com.kh.futsal.team.model.service.TeamService;

@WebServlet("/team/*")
public class TeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TeamService ts = new TeamService();
	private MemberService ms = new MemberService();
	private AlarmService alarmService = new AlarmService();
	
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
		case "chck-penalty":
			chckPenalty(request,response);
			break;
		case "join-team":
			joinTeam(request,response);
			break;
		case "tmCode-check":
			tmCodeChk(request,response);
			break;
		case "join":
			joinFunc(request,response);
			break;
		case "create-form":
			createForm(request,response);
			break;
		case "tmName-check":
			tmNameChk(request,response);
			break;
		case "create":
			createFunc(request,response);
			break;
		//팀 소속된 사람만
		case "modify":
			teamModify(request,response);
			break;
		case "modify-func":
			modifyFunc(request,response);
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
		case "update-winner":
			updateWinner(request,response);
			break;
		case "update-rating":
			updateRating(request,response);
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

//	팀 탈퇴
	private void leaveTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateMemberForLeaveTeam(request.getParameter("userId"));
		
		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = "팀 탈퇴 처리가 완료되었습니다.";
			Member member = ms.selectMemberById(request.getParameter("userId"));
			request.getSession().setAttribute("authentication", member);
		}
		pw(response).print(msg);
	}
	
//	팀 해체
	private void breakTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "처리 도중 오류가 발생하였습니다.";

		List<MatchMaster> tmBoards = ts.selectTmBoards(request.getParameter("tmCode"));
		List<MatchMaster> tmApplications = ts.selectTmApplications(request.getParameter("tmCode"));
		
		if(!tmBoards.isEmpty()) {
			for (MatchMaster tmBoard : tmBoards) {
				if(tmBoard.getState()==0 || (tmBoard.getState()==1 && tmBoard.getMatchSchedule() > searchNowTime())) {
					msg = "현재 작성하신 매치글, 혹은 진행 예정인 매치가 있습니다.<br>삭제 및 취소 후 다시 신청해주세요.";
					pw(response).print(msg);
					return;
				}
			}
		} else if(!tmApplications.isEmpty()) {
			for (MatchMaster tmApplication : tmApplications) {
				if(tmApplication.getState()==0 || (tmApplication.getState()==1 && tmApplication.getMatchSchedule() > searchNowTime())) {
					msg = "현재 작성하신 매치글, 혹은 진행 예정인 매치가 있습니다.<br>삭제 및 취소 후 다시 신청해주세요.";
					pw(response).print(msg);
					return;
				}
			}
		}
		
		//해체가 가능한 경우
		Member member = (Member) request.getSession().getAttribute("authentication");
		List<Member> tmMembers = ts.selectTmMembers(request.getParameter("tmCode"));
		int res = ts.updateDelDate(request.getParameter("tmCode"),member.getUserId(),tmMembers);
		res = 1;
		if(res > 0) {
			msg = "팀 해체가 완료되었습니다.";
			member.setTmCode(null);
			member.setGrade("ME00");
			request.getSession().setAttribute("authentication", member);
			request.getSession().removeAttribute("team");
		}
		pw(response).print(msg);
	}
	
	private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		FileDTO file = ts.selectFile(team.getTmCode());
		request.getSession().setAttribute("file", file);
		request.getRequestDispatcher("/team/managing/delete-team").forward(request, response);
	}
	
	private void teamBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		Long nowDate = searchNowTime();
		
		List<MatchMaster> tmBoards = ts.selectTmBoards(team.getTmCode());
		List<MatchMaster> mcBoards = ts.selectMcBoards(team.getTmCode());
		List<MatchMaster> appliBoards = ts.selectTmApplications(team.getTmCode());

		for (int i = 0; i < tmBoards.size(); i++) {
			if(checkGameEnd(tmBoards.get(i))) {
				alarmService.updateAlarmIsEnd(tmBoards.get(i),userId);
			}
		}
		for (int j = 0; j < mcBoards.size(); j++) {
			if(checkGameEnd(mcBoards.get(j))) {
				alarmService.updateAlarmIsEnd(mcBoards.get(j),userId);
			}
		}
		for (int k = 0; k < appliBoards.size(); k++) {
			if(checkGameEnd(appliBoards.get(k))) {
				alarmService.updateAlarmIsEnd(appliBoards.get(k),userId);
			}
		}
		
		request.setAttribute("nowDate",nowDate);
		request.setAttribute("tmBoards", tmBoards);
		request.setAttribute("mcBoards", mcBoards);
		request.setAttribute("appliBoards", appliBoards);
		request.getRequestDispatcher("/team/managing/team-board").forward(request, response);
	}
	
//	상대팀 평가 저장
	private void updateRating(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateRslt(request.getParameter("mgIdx"),request.getParameter("target"),Integer.parseInt(request.getParameter("rating")));

		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = "평가 등록이 완료되었습니다.";
		}
		pw(response).print(msg);
	}
	
//	경기 승리팀 저장
	private void updateWinner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateWinner(request.getParameter("mgIdx"),request.getParameter("winner"),request.getParameter("loser"));
		
		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = "결과 등록이 완료되었습니다.";
		}
		pw(response).print(msg);
	}
	
	private void totalScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		Long nowDate = searchNowTime();
		request.setAttribute("nowDate",nowDate);
		request.setAttribute("results", ts.selectMatchGame(team.getTmCode()));
		request.getRequestDispatcher("/team/managing/total-score").forward(request, response);
	}
	
//	팀원 추방
	private void manageExpulsion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateMemberForLeaveTeam(request.getParameter("userId"));

		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = request.getParameter("userId")+"님을 추방하였습니다.";
		}
		pw(response).print(msg);
	}
	
//	팀장 위임
	private void manageDelegation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		String msg = "처리 중 오류가 발생하였습니다.";
		int res = ts.updateGrades(request.getParameter("userId"), team);
		if (res > 0) {
			msg = request.getParameter("userId")+"님에게 팀장을 위임하였습니다.";
			member.setGrade("ME01");
			request.getSession().setAttribute("authentication", member);
		}
		pw(response).print(msg);
	}

//	팀원 등급 변경
	private void manageGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int res = ts.updateGrade(request.getParameter("userId"),request.getParameter("grade"));
		String msg = "처리 도중 오류가 발생하였습니다.";
		if(res > 0) {
			msg = request.getParameter("userId")+"님의 등급 변경이 완료되었습니다.";
		}
		pw(response).print(msg);
	}
	
	private void teamManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		request.setAttribute("tmMembers", ts.selectTmMembers(team.getTmCode()));
		request.getRequestDispatcher("/team/managing/manage").forward(request, response);
	}
	
//	팀 정보 수정
	private void modifyFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		Team team = (Team) request.getSession().getAttribute("team");
		
		team.setLocalCode(params.getParameter("localCode"));
		team.setTmGrade(params.getParameter("tmGrade"));
		team.setTmInfo(params.getParameter("tmInfo"));
		
		List<FileDTO> fileDTOs = params.getFilesInfo();
		ts.updateTeam(team, fileDTOs);
		
		request.getSession().setAttribute("team", team);
		response.sendRedirect("/team/managing/modify?result=3");
	}
	
	private void teamModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		FileDTO file = ts.selectFile(team.getTmCode());
		request.setAttribute("file", file);
		request.getRequestDispatcher("/team/managing/modify").forward(request, response);
	}
	
//	팀이름 중복체크
	private void tmNameChk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = ts.selectTeamByTmName(request.getParameter("tmName"));
		if(team.getTmName() == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}	
	}
	
	private void createFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUtil util = new FileUtil();
		MultiPartParams params = util.fileUpload(request);
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		//random code
		String tmCode = ts.createRandomCode(member.getUserId());
		
		Team team = new Team();
		team.setTmCode(tmCode);
		team.setLocalCode(params.getParameter("localCode"));
		team.setManagerId(member.getUserId());
		team.setTmName(params.getParameter("tmName"));
		team.setTmGrade(params.getParameter("tmGrade"));
		team.setTmInfo(params.getParameter("tmInfo"));
		List<FileDTO> fileDTOs = params.getFilesInfo();
		ts.insertForCreating(team, member, fileDTOs);
		
		member.setTmCode(tmCode);
		member.setGrade("ME03");
		request.getSession().setAttribute("authentication", member);
		response.sendRedirect("/team/managing/modify?result=2");
	}
	
	private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/create-form").forward(request, response);
	}
	
//	팀코드로 팀 유무 체크
	private void tmCodeChk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = ts.selectTeamByTmCode(request.getParameter("tmCode"));
		//팀코드가 있고 삭제되지 않은 팀만 가입 가능
		if(team.getTmCode()!=null && team.getDelDate()==null) {
			response.getWriter().print("available");
		} else {
			response.getWriter().print("disable");
		}
	}
	
	private void joinFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		Team team = ts.selectTeamByTmCode(request.getParameter("tmCode"));
		
		ts.updateMemberIntoTeam(member, team);			
		member.setTmCode(team.getTmCode());
		member.setGrade("ME01");
		request.getSession().setAttribute("authentication", member);
		response.sendRedirect("/team/managing/modify?result=1");
	}
	
	private void joinTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/join-team").forward(request, response);
	}

//	팀코드로 팀 유무 체크
	private void chckPenalty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("authentication");
		if(ts.selectTmPenalty(member.getUserId())) {
			System.out.println("controller 트루인가");
			response.getWriter().print("disable");
		} else {
			System.out.println("controller 펄스인가");
			response.getWriter().print("available");
		}
	}
	
	private void teamMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = (Team) request.getSession().getAttribute("team");
		if(team != null) {
			response.sendRedirect("/team/managing/modify");
			return;
		}
		request.getRequestDispatcher("/team/main").forward(request, response);
	}

//	현재 날짜+시간 Long type으로 변환
	private Long searchNowTime() {
		Date date = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String nowDateStr = sdf.format(date);
		Long nowDate = Long.parseLong(nowDateStr);
		return nowDate;
	}
	
//	메시지 작성
	private PrintWriter pw (HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache, no-store");
		return response.getWriter();
	}
	
	//알림 주기위한 메소드
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
				if(alarmTime <= time) {
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
