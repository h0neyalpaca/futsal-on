package com.kh.futsal.support.model.service;

import java.sql.Connection;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.support.model.dao.SupportDao;
import com.kh.futsal.support.model.dto.Support;

public class SupportService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private SupportDao supportDao = new SupportDao();
	
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
	
}
