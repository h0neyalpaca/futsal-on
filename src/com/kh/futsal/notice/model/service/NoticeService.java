package com.kh.futsal.notice.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.notice.model.dao.NoticeDao;
import com.kh.futsal.notice.model.dto.Notice;

public class NoticeService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private NoticeDao noticeDao = new NoticeDao();
	
	
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


	public List<Notice> selectNoticeList() {

		List<Notice> noticeList = null;
		Connection conn = template.getConnection();
		
		try {
			noticeList = noticeDao.selectNoticeList(conn);
		} finally {
			template.close(conn);
		}
		
		
		return noticeList;
	}
	
	
	

}
