package com.kh.futsal.notice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.futsal.common.code.member.MemberGrade;
import com.kh.futsal.common.pagination.PageInfo;
import com.kh.futsal.common.pagination.Pagination;
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
		Member member = (Member) request.getSession().getAttribute("authentication");
		MemberGrade adminGrade = MemberGrade.valueOf(member.getGrade());
		
		if(adminGrade.ROLE.equals("admin")) {
			 //noticeDetail = noticeService.selectAllSupportList();
		}else {
			String userId = member.getUserId();
			//supportList = noticeService.selectSupportList(userId, page);
		}
		
		
		request.getRequestDispatcher("/notice/notice-form").forward(request, response);
		
		
	}

	private void noticeUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Member member = (Member) request.getSession().getAttribute("authentication");
		
	
		//response.sendRedirect("/notice/notice-list");	
		
		
	}

	private void noticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Notice noticeDetail = new Notice();
		
		String nwIdx = request.getParameter("noticeNo"); //클릭한 게시물
		System.out.println("nwIdx : " + nwIdx);
		String curPage = request.getParameter("curPage");
		System.out.println("curPage : " + curPage);
		
		String searchContent = null;
		if(request.getParameter("searchNotice") != null) {
			searchContent = request.getParameter("searchNotice");
		}

		
		//로그인되어있는 id 가져오기
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("authentication");
		//System.out.println("member : " + member);
	
		//기존에 존재하던 쿠키 가져오기
		Cookie[] cookies = request.getCookies();
		
		//null값 비교용 쿠키
		Cookie nullCookie = null;
		
		//기존 쿠키 불러오기 (조회수 증가 X)
		//로그인이 되있는 경우 userId와 nwIdx로 된 쿠키를 가져옴
		if(cookies != null && cookies.length > 0 && member != null){ //cookie 존재하고 로그인되어있다면
			for (int i = 0; i < cookies.length; i++) {
				//System.out.println("로그인 된 cookies[i].getName() : "+cookies[i].getName()); //cookieleader1001
				if(cookies[i].getName().equals("cookie" + member.getUserId() + nwIdx)){ //cookie가 현재 로그인되어있는 id, 클릭한 nwIdx와 일치하다면
					nullCookie = cookies[i]; 
				}
			}
		}
			
		//로그인 되지 않았다면 게시글 번호로만 된 쿠키 가져오기
		if(cookies != null && cookies.length > 0 && member == null){ //cookie가 있고 로그인되지 않았다면
			for (int i = 0; i < cookies.length; i++) {
				//System.out.println("로그인되지 않은 cookies[i].getName() : " + cookies[i].getName()); //cookie1000
				
				if(cookies[i].getName().equals("cookie" + nwIdx)){ //클릭한 nwIdx와 같은 nwIdx를 가진 cookie가 존재한다면 
					nullCookie = cookies[i]; //nullCookie를 cookie+nwIdx로 채운다.
					System.out.println("nullCookie : " + nullCookie.getName());
				}
			}
		}
			
			
		//cookie 생성
		//로그인이 되있는 경우 userId와 nwIdx로 된 쿠키를 만든다.
		if(member != null && nullCookie == null){
			Cookie cookie = new Cookie("cookie"+ member.getUserId() + nwIdx, "cookie"+ member.getUserId() + nwIdx);
			cookie.setMaxAge(60*60*12);
			response.addCookie(cookie);
			noticeService.noticeViewCnt(nwIdx); //조회수 업데이트
		}
		
		//로그인 되지 않았다면 게시글 번호로만 된 쿠키를 만든다.
		if(member == null && nullCookie == null){
			Cookie cookie = new Cookie("cookie" + nwIdx, "cookie" + nwIdx);
			cookie.setMaxAge(60*60*12); //12시간
			response.addCookie(cookie);
			noticeService.noticeViewCnt(nwIdx); //조회수 업데이트
			//Set-Cookie: cookie1000=cookie1000; Max-Age=43200; Expires=Tue, 28-Sep-2021 15:27:50 GMT
			//Set-Cookie: cookieleader1001=cookieleader1001; Max-Age=43200; Expires=Tue, 28-Sep-2021 15:29:56 GMT
		} 
	
		//nwIdx에 따른 게시판 상세페이지	
		noticeDetail = noticeService.selectNoticeDetail(nwIdx);
		System.out.println("noticeDetail : " + noticeDetail);
		
		//이전글, 다음글
		Notice prevDetail = new Notice();
		prevDetail = noticeService.selectNoticePrevDetail(nwIdx);
		System.out.println("prevDetail : " + prevDetail);
		Notice nextDetail = new Notice();
		nextDetail = noticeService.selectNoticeNextDetail(nwIdx);
		System.out.println("nextDetail : " + nextDetail);
		

		
		request.setAttribute("searchContent", searchContent);
		request.setAttribute("noticeDetail", noticeDetail);
		request.setAttribute("prevDetail", prevDetail);
		request.setAttribute("nextDetail", nextDetail);
		request.setAttribute("curPage", curPage);

		request.getRequestDispatcher("/notice/notice-detail").forward(request, response);
		
	}
	
	
	private void noticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int curPage = 0;
		int totalNoticeCnt = 0;
		
		//사용자가 입력한 검색어
		String searchContent = null;
		if(request.getParameter("searchNotice") != null) {
			searchContent = request.getParameter("searchNotice");
		}
				
		//불러올 총 게시물 list 수
		if(searchContent == null) { //아무것도 입력하지 않았다면
			totalNoticeCnt = noticeService.selectNoticeCnt();
			System.out.println("검색 전 totalNoticeCnt :" + totalNoticeCnt);
		}else { //검색어를 입력했다면
			totalNoticeCnt = noticeService.selectSearchCnt(searchContent);
			System.out.println("검색 후 totalNoticeCnt :" + totalNoticeCnt);
		}

		//현재 페이지 번호
		String pageNum = request.getParameter("curPage");
		if(pageNum == null) {
			pageNum = "1";
		}

		curPage =  Integer.parseInt(pageNum);
		System.out.println("curPage :" + curPage);	
		
		PageInfo page = Pagination.getPageInfo(curPage, totalNoticeCnt);
		System.out.println("page :" + page);
		
		List<Notice> mainNoticeList = noticeService.selectMainNoticeList();
		List<Notice> noticeList = new ArrayList<Notice>();
		List<Notice> noticeSearchList = new ArrayList<Notice>();
	
		noticeList = noticeService.selectNoticeList(page);

		noticeSearchList = noticeService.selectSearchList(page, searchContent);

		request.setAttribute("searchContent", searchContent);
		request.setAttribute("totalNoticeCnt", totalNoticeCnt);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("noticeSearchList", noticeSearchList);
		request.setAttribute("mainNoticeList", mainNoticeList);
		request.setAttribute("page", page);
		
		
		request.getRequestDispatcher("/notice/notice-list").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
