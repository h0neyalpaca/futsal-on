package com.kh.futsal.common.pagination;

public class Pagination {
 //PageInfo 객체를 리턴하는 static 메서드
 //매개변수 : curPage, totalNoticeCnt
	
	public static PageInfo getPageInfo(int curPage, int totalNoticeCnt) {
		
		PageInfo pageInfo = null;
		
		int pageSize = 2; //한 번에 표시할 페이지 수
		int startPage = 0; //현재 페이지의 시작 번호
		int endPage = 0; //현제 페이지의 마지막 번호
		int totalPage = 0; //페이지 총 갯수
		int startNo = 0;
		int endNo = 0;
		
		int noticeSize = 3; //페이지당 게시물 수 
		 
		//totalPage 페이지 총 갯수
		if(totalNoticeCnt % noticeSize > 0) {
			totalPage = totalNoticeCnt/noticeSize+1;
		}else {
			totalPage = totalNoticeCnt/noticeSize;
		}
		
		//현재 페이지의 첫 게시물 번호
		startNo = (curPage-1) * noticeSize +1;
		//System.out.println("startNo : "+startNo);
		//System.out.println("noticeSize : "+noticeSize);

		//현제 페이지의 마지막 게시물 번호
		endNo = startNo + noticeSize - 1;
		if(endNo > totalNoticeCnt) {
			endNo = totalNoticeCnt;
		}
		//System.out.println("endNo : "+endNo);
		
		//startPage 현재 페이지의 시작 번호
		startPage = (curPage-1)/pageSize*pageSize+1;
		
		//endPage 현제 페이지의 마지막 번호
		endPage = startPage + pageSize - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		pageInfo = new PageInfo(curPage, startPage, endPage, pageSize, totalNoticeCnt, totalPage, startNo, endNo);
		
		
		return pageInfo;
	}
}
