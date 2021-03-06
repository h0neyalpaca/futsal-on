package com.kh.futsal.member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.member.model.service.MemberService;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
       
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length-1]) {
		case "login-form":
			loginForm(request,response);
			break;
		case "login":
			login(request,response);
			break;
		case "logout":
			  logout(request,response);
			break;
		case "join-form":
			joinForm(request,response);
			break;
		case "join":
			join(request,response);
			break;
		case "join-impl":
			  joinImpl(request,response);
			break;
		case "id-check":
			  checkID(request,response);
			break;
		case "userNick-check":
			  userNickCheck(request,response);
			break;
		case "lost-id":
			lostId(request,response);
			break;
		case "lost-password":
			lostPw(request,response);
			break;
		case "result":
			resultPage(request,response);
			break;
		case "kakaoLogin":
			kakaoLogin(request,response);
			break;
		case "search-id":
			searchId(request,response);
			break;
		case "search-password":
			searchPassword(request,response);
			break;
		default:
		}
	}

	private void searchPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		
		Member member = memberService.searchByPass(userId,email);
		
		if(member == null) {
			response.sendRedirect("/member/lost-password?err=1");
			return;
		}
		
		String randomPass = memberService.changePass(userId,email);
		memberService.searchPassEmail(member,randomPass);
		
		request.setAttribute("msg", "??????????????? ?????? ?????? ???????????? ?????????????????????.");
		request.setAttribute("url", "/member/login-form");
		request.getRequestDispatcher("/common/result").forward(request, response);	
	}

	private void searchId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		
		Member member = memberService.searchById(userName,email);
		
		if(member == null) {
			response.sendRedirect("/member/lost-id?err=1");
			return;
		}
		
		String originId = member.getUserId();
		String maskingId = "";
		
		if(originId.length() < 3) {
			maskingId = originId.replaceAll("(?<=.{1}).", "*");
		} else {
			maskingId = originId.replaceAll("(?<=.{2}).", "*");
		}
		
		member.setUserId(maskingId);
		System.out.println(maskingId);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/member/login/lost-id").forward(request, response);
		
	}

	private void kakaoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		Member Checkmember = memberService.selectMemberById("kakao_"+userId);
		Member member = new Member();
		
		if(Checkmember == null) {
			
			member.setUserId("kakao_"+userId);
			member.setPassword("kakao");
			member.setUserName("?????????????????????");
			member.setTell(" ");
			member.setEmail("?????????????????????");
			member.setCapacity(" ");
			member.setGrade("ME00");
			memberService.insertMember(member);
			request.getSession().setAttribute("authentication", member);
			
		} else if(Checkmember.getPassword().equals("kakao")) {
			
			request.getSession().setAttribute("authentication", Checkmember);
			
		}
		
		
			
	}

	private void userNickCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNick = request.getParameter("userNick");
		Member member = memberService.selectMemberByNick(userNick);
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}	
	}

	private void checkID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		Member member = memberService.selectMemberById(userId);
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
	}

	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		memberService.insertMember((Member)session.getAttribute("persistUser"));
		
		//?????? persisUser?????? ??? ??? DB??? ???????????? ????????? ????????? ????????? ????????? ????????????
		session.removeAttribute("persistUser");
		session.removeAttribute("persist-token");
		response.sendRedirect("/member/login-form");
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String userNick = request.getParameter("userNick");
		String tell = request.getParameter("tell");
		String email = request.getParameter("email");
		String capacity = request.getParameter("capacity");
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setUserName(userName);
		member.setUserNick(userNick);
		member.setTell(tell);
		member.setEmail(email);
		member.setCapacity(capacity);
		
		String persistToken = UUID.randomUUID().toString();
		request.getSession().setAttribute("persistUser", member);
		request.getSession().setAttribute("persist-token", persistToken);
		
		memberService.authenticateEmail(member,persistToken);
		
		request.setAttribute("msg", "??????????????? ?????? ???????????? ?????????????????????.");
		request.setAttribute("url", "/member/login-form");
		request.getRequestDispatcher("/common/result").forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("authentication");
		response.sendRedirect("/");
		System.out.println("test");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
//		System.out.println(userId + password);
		Member member = memberService.memberAuthenticate(userId,password);
		
		if(member == null || member.getIsLeave() != 0 || member.getPassword().equals("kakao")) {
			response.sendRedirect("/member/login-form?err=1");
			return;
		}
		
		request.getSession().setAttribute("authentication", member);
		response.sendRedirect("/index");
	}

	private void resultPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/result").forward(request, response);
		
	}

	private void joinForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/join-form").forward(request, response);
	}
	
	private void lostPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/login/lost-password").forward(request, response);
	}
	
	private void lostId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/login/lost-id").forward(request, response);
		
	}
	
	private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/login/login-form").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
