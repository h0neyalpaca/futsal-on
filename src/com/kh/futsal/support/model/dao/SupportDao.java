package com.kh.futsal.support.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.support.model.dto.Support;

public class SupportDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public Support selectBoardDetail(String bdIdx, Connection conn) {
		
		String sql = "select bd_idx, user_id, reg_date, title, content , type from board where bd_idx = ? ";
		System.out.println(sql);
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Support support = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,bdIdx);
			rset = pstm.executeQuery();

			if(rset.next()) {
				support = new Support();
				support.setDbIdx(rset.getString("bd_idx"));
				support.setUserId(rset.getString("user_id"));
				support.setRegDate(rset.getDate("reg_date"));
				support.setTitle(rset.getString("title"));
				support.setContent(rset.getString("content"));
				support.setType(rset.getInt("type"));
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return support;
	}
	
}
