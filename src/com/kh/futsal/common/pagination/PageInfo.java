package com.kh.futsal.common.pagination;

public class PageInfo {

	private int curPage = 0; //현재 페이지 번호
	private int startPage = 0; //현재 페이지의 시작 번호
	private int endPage = 0; //현제 페이지의 마지막 번호	
	private int pageSize = 2; //한 번에 표시할 페이지 수
	private int totalNoticeCnt = 0; //게시물 총 갯수
	private int totalPage = 0; //페이지 총 갯수
	private int startNo = 0; //현재 페이지의 첫 게시물 번호
	private int endNo = 0; //현제 페이지의 마지막 게시물 번호

	private int boardSize = 0;
	
	public PageInfo() {
		// TODO Auto-generated constructor stub
	}

	public PageInfo(int curPage, int startPage, int endPage, int pageSize, int totalNoticeCnt, int totalPage,
			int startNo, int endNo, int boardSize) {
		super();
		this.curPage = curPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.pageSize = pageSize;
		this.totalNoticeCnt = totalNoticeCnt;
		this.totalPage = totalPage;
		this.startNo = startNo;
		this.endNo = endNo;
		this.boardSize = boardSize;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalNoticeCnt() {
		return totalNoticeCnt;
	}

	public void setTotalNoticeCnt(int totalNoticeCnt) {
		this.totalNoticeCnt = totalNoticeCnt;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	@Override
	public String toString() {
		return "PageInfo [curPage=" + curPage + ", startPage=" + startPage + ", endPage=" + endPage + ", pageSize="
				+ pageSize + ", totalNoticeCnt=" + totalNoticeCnt + ", totalPage=" + totalPage + ", startNo=" + startNo
				+ ", endNo=" + endNo + ", boardSize=" + boardSize + "]";
	}
	

	
	
	
}
