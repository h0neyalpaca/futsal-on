package com.kh.futsal.matching.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.matching.model.dto.MatchMaster;

public class MatchDao {
	JDBCTemplate template = JDBCTemplate.getInstance();

	public int matchRegister(MatchMaster matchMaster, Connection conn) {
		int res = 0;		
		PreparedStatement pstm = null;
		
		String query = "INSERT INTO MATCH_MASTER (MM_IDX, USER_ID, TM_CODE, LOCAL_CODE, ADDRESS, REG_DATE, TITLE,EXPENSE, GRADE, CONTENT, TM_MATCH, MATCH_TIME)"
					 + " VALUES(SC_MM_IDX.nextval,'leader','TEAMCODE',?,?,sysdate,?, ?, ?, ?,?, ?)";
		
		 
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, matchMaster.getLocalCode());
			pstm.setString(2, matchMaster.getAddress());
			pstm.setString(3, matchMaster.getTitle());
			
			pstm.setString(4, matchMaster.getExpense());
			pstm.setString(5, matchMaster.getGrade());
			pstm.setString(6, matchMaster.getContent());
			pstm.setInt(7, matchMaster.getTmMatch());
			pstm.setString(8, matchMaster.getMatchTime());
			
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
		return res;
	}
	
	
}
