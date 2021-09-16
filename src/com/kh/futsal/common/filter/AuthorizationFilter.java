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



public class AuthorizationFilter implements Filter {

    
    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String[] uriArr = httpRequest.getRequestURI().split("/");
		
		if(uriArr.length != 0) {
			switch (uriArr[1]) {
				case "mypage":
					mypageAuthorize(httpRequest,httpResponse,uriArr);
					break;
				case "myteam":
					myteamAuthorize(httpRequest,httpResponse,uriArr);
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
			}
		}
		chain.doFilter(request, response);
	}
	
	


	private void noticeAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
		
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());
		
		switch (uriArr[2]) {
		case "notice-posting":
			if(!adminGrade.ROLE.equals("admin")) {
				throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
			}
			break;
		default:
			break;
		}
		
	}


	private void memberAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		switch (uriArr[2]) {
		case "join-impl":
			String serverToken = (String) httpRequest.getSession().getAttribute("persist-token");
			String clientToken = httpRequest.getParameter("persist-token");
			
			if(serverToken == null || !serverToken.equals(clientToken)) {
				throw new HandlableException(ErrorCode.AUTHENTICATION_FAILED_ERROR);
			}
			break;
		default:
			break;
		}
		
	}


	private void mypageAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		switch (uriArr[2]) {
		case "personal-notice":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "support":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "myApplication":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "modify":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		case "leave-id":
			if(httpRequest.getSession().getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT_LOGIN_PAGE_NO_MESSAGE);
			}
			break;
		default:
			break;
		}
		
	}


	private void myteamAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
		
		if(member == null) {
			throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
		}
		
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

		switch (uriArr[2]) {
		case "main":
			if(!adminGrade.DESC.equals("nomal")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "create-form":
			
			if(!adminGrade.DESC.equals("nomal")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "join-team":
			
			if(!adminGrade.DESC.equals("nomal")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "managing":
			if(!adminGrade.ROLE.equals("team")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		
		default: break;
		}
	}



	private void matchingAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) throws ServletException, IOException {
		
		Member member = (Member) httpRequest.getSession().getAttribute("authentication");
		
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());

		switch (uriArr[3]) {
		case "mercenary-match-form":
			if(!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "team-match-form":
			if(!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "mercenary-modify":
			if(!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "team-modify":
			if(!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
			}
			break;
		case "matching-list":
			if(!adminGrade.DESC.equals("leader")) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE);
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
