package com.kh.futsal.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		case "support-list":
			supportList(request,response);
			break;
		case "support-detail":
			supportDetail(request,response);
			break;
		case "support-modify":
			supportModify(request,response);
			break;
		case "modify":
			mypageModify(request,response);
			break;
		case "leave-id":
			leaveId(request,response);
			break;
		default:
		}
	}

	private void leaveId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/leave-id").forward(request, response);
		
	}
	private void mypageModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/modify").forward(request, response);
		
	}
	private void supportDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/support/support-detail").forward(request, response);
		
	}
	private void supportModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/support/support-modify").forward(request, response);
		
	}
	private void supportList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/support/support-list").forward(request, response);
		
	}
	private void myApplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/my-application").forward(request, response);
		
	}
	private void personalNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/personal-notice").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
