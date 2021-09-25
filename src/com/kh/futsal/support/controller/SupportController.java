package com.kh.futsal.support.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.common.code.member.MemberGrade;
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
		case "support-delete":
			supportDelete(request,response);
			break;
		case "support-answer":
			supportAnswer(request,response);
			break;
		default:
		}
	}
	
	private void supportAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bdIdx = request.getParameter("bdIdx");
		String answer = request.getParameter("answer");
		
		supportService.updateAnswer(bdIdx,answer);
		
		request.setAttribute("msg","문의사항 답변 등록이 완료되었습니다.");
	    request.setAttribute("url", "/mypage/support/support-list");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	private void supportDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bdIdx = request.getParameter("bdIdx");
		
		supportService.deleteBoard(bdIdx);
		
		request.setAttribute("msg","문의사항 삭제가 완료되었습니다.");
	    request.setAttribute("url", "/mypage/support/support-list");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}
	
	private void supportUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bdIdx = request.getParameter("bdIdx");
		String content = request.getParameter("content");
		
		supportService.updateBoard(bdIdx,content);
		
		request.setAttribute("msg","문의사항 수정이 완료되었습니다.");
	    request.setAttribute("url", "/mypage/support/support-list");
	    request.getRequestDispatcher("/common/result").forward(request, response);
	}

	private void supportUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
		Support support = new Support();
		support.setUserId(member.getUserId());
		support.setTitle(request.getParameter("title"));
		support.setContent(request.getParameter("content"));
		support.setType(Integer.parseInt(request.getParameter("type")));

		supportService.insertBoard(support);
		
		request.setAttribute("msg","문의사항 등록이 완료되었습니다.");
	    request.setAttribute("url", "/mypage/support/support-list");
	    request.getRequestDispatcher("/common/result").forward(request, response);
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
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());
		List<Support> supportList = null;
		
		if(adminGrade.ROLE.equals("admin")) {
			 supportList = supportService.selectAllSupportList();
		}else {
			String userId = member.getUserId();
			supportList = supportService.selectSupportList(userId);
		}
		
		request.setAttribute("supportList", supportList);
		request.getRequestDispatcher("/mypage/support/support-list").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
