package com.kh.futsal.support.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.support.model.dto.Support;
import com.kh.futsal.support.model.service.SupportService;

/**
 * Servlet implementation class SupportController
 */
@WebServlet("/mypage/support/*")
public class SupportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SupportService supportService = new SupportService();
	
    public SupportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length-1]) {
		
		case "support-list":
			supportList(request,response);
			break;
		case "support-modify":
			supportModify(request,response);
			break;
		case "support-detail":
			supportDetail(request,response);
			break;
		case "support-form":
			supportForm(request,response);
			break;
		case "support-upload":
			supportUpload(request,response);
			break;
		case "support-update":
			supportUpdate(request,response);
			break;
		default:
		}
	}
	
	private void supportUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bdIdx = request.getParameter("bdIdx");
		String content = request.getParameter("content");
		
		System.out.println(bdIdx +  content);
		supportService.updateBoard(bdIdx,content);
		
		request.getRequestDispatcher("/mypage/support/support-list").forward(request, response);
	}

	private void supportUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		Support support = new Support();
		support.setUserId(member.getUserId());
		support.setTitle(request.getParameter("title"));
		support.setContent(request.getParameter("content"));
		support.setType(Integer.parseInt(request.getParameter("type")));

		supportService.insertBoard(support);
		
		request.getRequestDispatcher("/mypage/support/support-list").forward(request, response);
	}
	
	private void supportForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/mypage/support/support-form").forward(request, response);
	}
	
	private void supportDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bdIdx = request.getParameter("bdIdx");
		
		Support support = supportService.selectBoardDetail(bdIdx);
		
		request.setAttribute("support", support);
		request.getRequestDispatcher("/mypage/support/support-detail").forward(request, response);
	}
	
	private void supportModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bdIdx = request.getParameter("bdIdx");
		
		Support support = supportService.selectBoardDetail(bdIdx);
		
		request.setAttribute("support", support);
		request.getRequestDispatcher("/mypage/support/support-modify").forward(request, response);
	}
	
	private void supportList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getUserId();
		
		List<Support> supportList = supportService.selectSupportList(userId);
		
		request.setAttribute("supportList", supportList);
		request.getRequestDispatcher("/mypage/support/support-list").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
