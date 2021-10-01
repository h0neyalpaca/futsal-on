package com.kh.futsal.support.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.pagination.PageInfo;
import com.kh.futsal.support.model.dao.SupportDao;
import com.kh.futsal.support.model.dto.Support;

public class SupportService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private SupportDao supportDao = new SupportDao();
	
	public void insertBoard(Support support) {
		
		Connection conn = template.getConnection();
		
		try {
			supportDao.insertBoard(support,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	public Support selectBoardDetail(String bdIdx) {
		
		 Connection conn = template.getConnection();
		 Support support = null;

		try {
			 support = supportDao.selectBoardDetail(bdIdx, conn);
		} finally {
			template.close(conn);
		}
		return support;
	}
	
	public List<Support> selectSupportList(String userId, PageInfo page) {
		
		 Connection conn = template.getConnection();
		 List<Support> supports = null;

		try {
			supports = supportDao.selectSupportList(userId, page, conn);
		} finally {
			template.close(conn);
		}
		return supports;
	}

	public void updateBoard(String bdIdx,String content) {
		
		Connection conn = template.getConnection();
		
		try {
			supportDao.updateBoard(bdIdx,content,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		
	}

	public void deleteBoard(String bdIdx) {
		
		Connection conn = template.getConnection();
		
		try {
			supportDao.deleteBoard(bdIdx,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		
	}

	public List<Support> selectAllSupportList() {
		
		Connection conn = template.getConnection();
		List<Support> supports = null;

		try {
			supports = supportDao.selectAllSupportList(conn);
		} finally {
			template.close(conn);
		}
		
		return supports;
	}

	public void updateAnswer(String bdIdx, String answer) {
		Connection conn = template.getConnection();
		
		try {
			supportDao.updateAnswer(bdIdx,answer,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		
	}

	public int selectBoardCnt(String userId) {
		int res = 0;
		Connection conn = template.getConnection();
		
		try {
			res = supportDao.selectBoardCnt(conn, userId);
		} finally {
			template.close(conn);
		}
		
		return res;
	}
	
}
