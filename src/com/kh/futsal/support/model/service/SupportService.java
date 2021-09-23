package com.kh.futsal.support.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
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
	
	public List<Support> selectSupportList(String userId) {
		
		 Connection conn = template.getConnection();
		 List<Support> supports = null;

		try {
			supports = supportDao.selectSupportList(userId, conn);
		} finally {
			template.close(conn);
		}
		return supports;
	}
	
}
