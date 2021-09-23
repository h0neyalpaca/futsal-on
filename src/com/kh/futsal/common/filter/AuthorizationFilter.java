package com.kh.futsal.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.futsal.common.code.ErrorCode;
import com.kh.futsal.common.code.member.MemberGrade;
import com.kh.futsal.common.exception.HandlableException;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.team.model.dto.Team;
import com.kh.futsal.team.model.service.TeamService;

public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String[] uriArr = httpRequest.getRequestURI().split("/");

		if (uriArr.length != 0) {
			switch (uriArr[1]) {
<<<<<<< HEAD
				case "mypage":
					  //if(httpRequest.getSession().getAttribute("authentication") == null) { 
						  //throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE); 
					  //}
					  //mypageAuthorize(httpRequest,httpResponse,uriArr);
					break;
				case "team":
					teamAuthorize(httpRequest,httpResponse,uriArr);
					break;
				case "matching":
					matchingAuthorize(httpRequest,httpResponse,uriArr);
					break;
				case "member":
					memberAuthorize(httpRequest,httpResponse,uriArr);
					break;
				case "notice":
					noticeAuthorize(httpRequest,httpResponse,uriArr);
					break;
				default:
					break;
=======
			case "mypage":
				// mypageAuthorize(httpRequest,httpResponse,uriArr);
				break;
			case "team":
				// myteamAuthorize(httpRequest,httpResponse,uriArr);
				break;
			case "matching":
				// matchingAuthorize(httpRequest,httpResponse,uriArr);
				break;
			case "member":
				memberAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "notice":
				// noticeAuthorize(httpRequest,httpResponse,uriArr);
				break;
			default:
				break;
>>>>>>> refs/heads/dev
			}
		}
		chain.doFilter(request, response);
	}

	private void noticeAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
<<<<<<< HEAD
		
=======

		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

>>>>>>> refs/heads/dev
		switch (uriArr[2]) {
		case "notice-posting":
<<<<<<< HEAD
			
			Member member = (Member) httpRequest.getSession().getAttribute("authentication");
			MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());
			
			if(!adminGrade.ROLE.equals("admin")) {
=======
			if (!adminGrade.ROLE.equals("admin")) {
>>>>>>> refs/heads/dev
				throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
			}
			break;
		default:
			break;
		}

	}

	private void memberAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
<<<<<<< HEAD
		
		String serverToken = (String) httpRequest.getSession().getAttribute("persist-token");
		String clientToken = httpRequest.getParameter("persist-token");
		
=======

>>>>>>> refs/heads/dev
		switch (uriArr[2]) {
<<<<<<< HEAD
			case "join-impl":
				if(serverToken == null || !serverToken.equals(clientToken)) {
					throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
				}
				break;
			default:
				break;
=======
		case "join-impl":
			String serverToken = (String) httpRequest.getSession().getAttribute("persist-token");
			String clientToken = httpRequest.getParameter("persist-token");

			if (serverToken == null || !serverToken.equals(clientToken)) {
				throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
			}
			break;
		default:
			break;
>>>>>>> refs/heads/dev
		}

	}
	
	private void matchingAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) throws ServletException, IOException {

<<<<<<< HEAD
=======
	private void mypageAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

>>>>>>> refs/heads/dev
		switch (uriArr[2]) {
<<<<<<< HEAD
			case "list-up":
				Member member = (Member) httpRequest.getSession().getAttribute("authentication");
				
				MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());
				
				if(!adminGrade.DESC.equals("leader")) {
					throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
				}
				break;
			default:matchingDetailAuthorize(httpRequest,httpResponse,uriArr);
				break;
=======
		case "personal-notice":
			if (httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "support":
			if (httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "myApplication":
			if (httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "modify":
			if (httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "leave-id":
			if (httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		default:
			break;
>>>>>>> refs/heads/dev
		}
<<<<<<< HEAD
	}
=======
>>>>>>> refs/heads/dev

	}

	private void matchingDetailAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse,String[] uriArr) {
		
		switch (uriArr[3]) {
			case "mercenary-list":
				break;
			case "team-list":
				break;
			default:matchingOnlyLeaderAuthorize(httpRequest,httpResponse,uriArr);
				break;
		}
	}


	private void matchingOnlyLeaderAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			String[] uriArr) {
		
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
<<<<<<< HEAD
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());
		
		switch (uriArr[3]) {
			
			case "mercenary-match-form":
				if(!adminGrade.DESC.equals("leader")) {
					throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
				}
				break;
			case "mercenary-modify":
				if(!adminGrade.DESC.equals("leader")) {
					throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
				}
				break;
			case "team-match-form":
				if(!adminGrade.DESC.equals("leader")) {
					throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
				}
				break;
			case "team-modify":
				if(!adminGrade.DESC.equals("leader")) {
					throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
				}
				break;
			default:
				break;
		}
		
	}


	private void teamAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

		/* 테스트용 아이디 */
		Member member = new Member();
		member.setUserId("alpaca_n");
		member.setGrade("ME00");
		httpRequest.setAttribute("authentication", member);
		
		/* 테스트용 팀 */
		TeamService ts = new TeamService();
		Team team = ts.selectTeamByUserId(member.getUserId());
		httpRequest.setAttribute("team", team);
		
		//member = (Member) httpRequest.getSession().getAttribute("authentication");
		//team = (Team) httpRequest.getSession().getAttribute("teamInfo");
		
		if(member == null) {
=======

		if (member == null) {
>>>>>>> refs/heads/dev
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		}
<<<<<<< HEAD
		
		MemberGrade grade = MemberGrade.valueOf(member.getGrade());
=======

		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

>>>>>>> refs/heads/dev
		switch (uriArr[2]) {
<<<<<<< HEAD
		case "managing":
			if(!grade.ROLE.equals("team")) {
=======
		case "main":
			if (!adminGrade.DESC.equals("nomal")) {
>>>>>>> refs/heads/dev
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "create-form":
<<<<<<< HEAD
			if(!grade.ROLE.equals("user")) {
=======

			if (!adminGrade.DESC.equals("nomal")) {
>>>>>>> refs/heads/dev
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "join-team":
<<<<<<< HEAD
			if(!grade.ROLE.equals("user")) {
=======

			if (!adminGrade.DESC.equals("nomal")) {
>>>>>>> refs/heads/dev
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
<<<<<<< HEAD
		default: break;
=======
		case "managing":
			if (!adminGrade.ROLE.equals("team")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;

		default:
			break;
>>>>>>> refs/heads/dev
		}
	}

<<<<<<< HEAD
	private void mypageAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
=======
	private void matchingAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr)
			throws ServletException, IOException {

>>>>>>> refs/heads/dev
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
<<<<<<< HEAD
=======

>>>>>>> refs/heads/dev
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());
<<<<<<< HEAD
		
		switch (uriArr[2]) {
		case "support":
			if(member == null || !adminGrade.ROLE.equals("admin")) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
=======

		switch (uriArr[3]) {
		case "mercenary-match-form":
			if (!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "team-match-form":
			if (!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "mercenary-modify":
			if (!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "team-modify":
			if (!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "matching-list":
			if (!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
>>>>>>> refs/heads/dev
			}
			break;
		default:
			break;
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
