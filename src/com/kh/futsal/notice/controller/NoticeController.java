package com.kh.futsal.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.notice.model.dto.Notice;
import com.kh.futsal.notice.model.service.NoticeService;



@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService = new NoticeService();
       
    public NoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		
		case "notice-list": //공지사항 목록
			noticeList(request,response);
			break;
		case "notice-detail": //공지사항 게시물
			noticeDetail(request,response);
			
		case "notice-upload": //공지사항 업로드
			noticeUpload(request,response);
			break;
			
		case "notice-form": //공지사항 작성페이지
			noticeForm(request,response);
			break;
			
		default:
		}
	}
	
	private void noticeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//notice 작성이 가능한 등급은? 관리자
		request.getRequestDispatcher("/notice/notice-form").forward(request, response);
		
		
	}

	private void noticeUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("authentication");
		
	
		response.sendRedirect("/notice/notice-list");	
		
		
	}

	private void noticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String nwIdx = request.getParameter("nwIdx");
//		Notice datas = noticeService.selectNoticeDetail(nwIdx);
//		request.setAttribute("datas", datas);
		
		request.getRequestDispatcher("/notice/notice-detail").forward(request, response);
		
	}
	private void noticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int curPage = 0; //현재 페이지 번호
		int lastPage = 0; //마지막 페이지 값
		int noticeSize = 5; //페이지당 게시물 수
		int pageSize = 5; //한 번에 표시할 페이지 수
		int totalNoticeCnt = 0; //게시물 총 갯수
		int totalPage = 0; //페이지 총 갯수
		int begin = 0; //가져올 게시물 시작 번호
		int end = 0; //현제 페이지의 마지막 번호
		
		//게시물 총 개수
		totalNoticeCnt = noticeService.selectNoticeCnt();
		//페이지 총 갯수
		if(totalNoticeCnt % 5 > 0) {
			totalPage = totalNoticeCnt/5+1;
		}else {
			totalPage = totalNoticeCnt/5;
		}
		
		
		List<Notice> noticeList = noticeService.selectNoticeList();
		List<Notice> mainNoticeList = noticeService.selectMainNoticeList();
		
		
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("mainNoticeList", mainNoticeList);

		request.getRequestDispatcher("/notice/notice-list").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
