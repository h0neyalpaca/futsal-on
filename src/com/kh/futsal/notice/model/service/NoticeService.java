package com.kh.futsal.notice.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.pagination.PageInfo;
import com.kh.futsal.notice.model.dao.NoticeDao;
import com.kh.futsal.notice.model.dto.Notice;

public class NoticeService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private NoticeDao noticeDao = new NoticeDao();
	
	
	public void noticeViewCnt(String nwIdx) {
		
		Connection conn = template.getConnection();
		
		try {
			noticeDao.noticeViewCnt(nwIdx, conn);
			template.commit(conn);
			
		} finally {
			template.close(conn);
		}
		
	}
	
	public Notice selectNoticeDetail(String nwIdx) {
		
		Connection conn = template.getConnection();
		Notice notice = null;
		
		try {
			notice = noticeDao.selectBoardDetail(nwIdx, conn);
		} finally {
			template.close(conn);
		}
		return notice;
	}
	
	public Notice selectNoticePrevDetail(String nwIdx) {
		Connection conn = template.getConnection();
		Notice notice = null;
		
		try {
			notice = noticeDao.selectNoticePrevDetail(nwIdx, conn);
		} finally {
			template.close(conn);
		}
		return notice;
	}

	public Notice selectNoticeNextDetail(String nwIdx) {
		Connection conn = template.getConnection();
		Notice notice = null;
		
		try {
			notice = noticeDao.selectNoticeNextDetail(nwIdx, conn);
		} finally {
			template.close(conn);
		}
		return notice;
	}
	
	public List<Notice> selectMainNoticeList() {
		
		List<Notice> mainNoticeList = null;
		Connection conn = template.getConnection();
		
		try {
			mainNoticeList = noticeDao.selectMainNoticeList(conn);
		} finally {
			template.close(conn);
		}
		
		return mainNoticeList;
	}


	public List<Notice> selectNoticeList(PageInfo page) {

		List<Notice> noticeList = null;
		Connection conn = template.getConnection();
		
		try {
			noticeList = noticeDao.selectNoticeList(conn, page);
		} finally {
			template.close(conn);
		}
		
		
		return noticeList;
	}


	public int selectNoticeCnt() {
		int res = 0;
		Connection conn = template.getConnection();
		
		try {
			res = noticeDao.selectNoticeCnt(conn);
		} finally {
			template.close(conn);
		}
		
		return res;
	}

	public List<Notice> selectSearchList(PageInfo page, String searchContent) {
		
		List<Notice> noticeList = null;
		Connection conn = template.getConnection();
		
		try {
			noticeList = noticeDao.selectSearchList(conn, page, searchContent);
		} finally {
			template.close(conn);
		}
		
		
		return noticeList;
	}

	public int selectSearchCnt(String searchContent) {
		int res = 0;
		Connection conn = template.getConnection();
		
		try {
			res = noticeDao.selectSearchCnt(searchContent, conn);
		} finally {
			template.close(conn);
		}
		
		return res;
	}

	

	

}
