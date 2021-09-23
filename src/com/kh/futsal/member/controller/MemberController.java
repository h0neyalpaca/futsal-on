package com.kh.futsal.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		case "lost-id":
			lostId(request,response);
			break;
		case "lost-password":
			lostPw(request,response);
			break;
		case "result":
			resultPage(request,response);
			break;
		default:
		}
	}

	private void join(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("authentication");
		response.sendRedirect("/");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
//		System.out.println(userId + password);
		Member member = memberService.memberAuthenticate(userId,password);
		
		if(member == null) {
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
