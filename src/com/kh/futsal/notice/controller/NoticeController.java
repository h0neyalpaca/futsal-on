package com.kh.futsal.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//Member member = (Member) request.getSession().getAttribute("authentication");
		
	
		//response.sendRedirect("/notice/notice-list");	
		
		
	}

	private void noticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Notice noticeDetail = new Notice();
		
		String nwIdx = request.getParameter("noticeNo");
		//System.out.println("nwIdx : " + nwIdx);
		
		//로그인되어있는 id 가져오기
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("authentication");
		System.out.println("member : " + member);
	
		//기존에 존재하던 쿠키 가져오기
		Cookie[] cookies = request.getCookies();
		
		//null값 비교용 쿠키
		Cookie nullCookie = null;
		
		//기존 쿠키 불러오기 (조회수 증가 X)
		//로그인이 되있는 경우 userId와 nwIdx로 된 쿠키를 가져옴
		if(cookies != null && cookies.length > 0 && member != null){ //cookie 존재하고 로그인되어있다면
			for (int i = 0; i < cookies.length; i++) {
				System.out.println("로그인 된 cookies[i].getName() : "+cookies[i].getName()); //cookieleader1001
				if(cookies[i].getName().equals("cookie" + member.getUserId() + nwIdx)){ //cookie가 현재 로그인되어있는 id, 클릭한 nwIdx와 일치하다면
					nullCookie = cookies[i]; 
				}
			}
		}
			
		//로그인 되지 않았다면 게시글 번호로만 된 쿠키 가져오기
		if(cookies != null && cookies.length > 0 && member == null){ //cookie가 있고 로그인되지 않았다면
			for (int i = 0; i < cookies.length; i++) {
				System.out.println("로그인되지 않은 cookies[i].getName() : " + cookies[i].getName()); //cookie1000
				
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

		request.setAttribute("noticeDetail", noticeDetail);
		
		request.getRequestDispatcher("/notice/notice-detail").forward(request, response);
		
	}
	
	
	private void noticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//[1][2] -> [3][4]
		// 3  3      3  1
		
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
		System.out.println("totalNoticeCnt : " + totalNoticeCnt);
		//페이지 총 갯수
		if(totalNoticeCnt % noticeSize > 0) {
			totalPage = totalNoticeCnt/noticeSize+1;
		}else {
			totalPage = totalNoticeCnt/noticeSize;
		}
		System.out.println("totalPage : " + totalPage);
		
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
