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
	
	private void pageCalc() {
		
	}
	private void noticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//[1][2] -> [3][4]
		// 3  3      3  2
		// 1  4      7  10 (첫번째 페이지 번호)
		
		int curPage = 0; //현재 페이지 번호
		int noticeSize = 3; //페이지당 게시물 수
		int pageSize = 2; //한 번에 표시할 페이지 수
		int totalNoticeCnt = 0; //게시물 총 갯수
		int totalPage = 0; //페이지 총 갯수
		int startNo = 0; //현재 페이지의 첫 게시물 번호
		int endNo = 0; //현제 페이지의 마지막 게시물 번호
		int startPage = 0; //현재 페이지의 시작 번호
		int endPage = 0; //현제 페이지의 마지막 번호
		
		
		//게시물 총 개수
		totalNoticeCnt = noticeService.selectNoticeCnt();
		//페이지 총 갯수
		if(totalNoticeCnt % noticeSize > 0) {
			totalPage = totalNoticeCnt/pageSize+1;
		}else {
			totalPage = totalNoticeCnt/pageSize;
		}
				
		//현재 페이지 번호
		String pageNum = request.getParameter("curPage");
		if(pageNum == null) {
			pageNum = "1";
		}		
		curPage = Integer.parseInt(pageNum);
		if(curPage > totalPage) {
			curPage = totalPage;
		}
		System.out.println("curPage : "+curPage);
		
		//현재 페이지의 첫 게시물 번호
		startNo = (curPage-1) * noticeSize +1;
		System.out.println("startNo : "+startNo);
		System.out.println("noticeSize : "+noticeSize);

		//현제 페이지의 마지막 게시물 번호
		endNo = startNo + noticeSize - 1;
		System.out.println("endNo : "+endNo);

		
		//페이지블록의 첫번째 페이지 번호
		startPage = (curPage-1)/pageSize*pageSize+1;
		System.out.println("startPage : "+startPage);

		//페이지블록의 마지막 페이지 번호
		endPage = startPage + pageSize - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		System.out.println("endPage : "+endPage);

		
		
		
		
		List<Notice> noticeList = noticeService.selectNoticeList(startNo, endNo);
		List<Notice> mainNoticeList = noticeService.selectMainNoticeList();
		
		
		
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("mainNoticeList", mainNoticeList);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageSize", pageSize);
		
		request.getRequestDispatcher("/notice/notice-list").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
