package com.kh.futsal.matching.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.matching.model.dto.MatchMaster;

public class MatchDao {
	JDBCTemplate template = JDBCTemplate.getInstance();

	public int matchRegister(MatchMaster matchMaster, Connection conn) {
		int res = 0;		
		PreparedStatement pstm = null;
		
		String query = "INSERT INTO MATCH_MASTER (MM_IDX, USER_ID, TM_CODE, LOCAL_CODE, ADDRESS, REG_DATE, TITLE,EXPENSE, GRADE, CONTENT, TM_MATCH, MATCH_TIME,MATCH_DATE)"
					 + " VALUES(SC_MM_IDX.nextval,'leader','TEAMCODE',?,?,sysdate,?, ?, ?, ?,?, ?,?)";
		
		 
		
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
			pstm.setString(9, matchMaster.getMatchDate());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
		return res;
	}


	public List matchListView(Connection conn) {
		List<MatchMaster> memberList = new ArrayList<MatchMaster>();	
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select "
				+ "MM_IDX,USER_ID,MATCH_MASTER.TM_CODE,LOCAL_CITY,ADDRESS,"
				+ "MATCH_MASTER.REG_DATE,TITLE,EXPENSE,GRADE,CONTENT,TM_MATCH"
				+ ",MATCH_TIME,MATCH_DATE,STATE"
				+ ",team.tm_name,TM_GRADE,TM_INFO,GAME_CNT,TM_SCORE,TM_WIN"
				+ " from MATCH_MASTER" + 
				" left outer join location" + 
				" on MATCH_MASTER.LOCAL_CODE = location.LOCAL_CODE"
				+ " left outer join team"
				+ " on MATCH_MASTER.TM_CODE = team.TM_CODE";
		
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			while(rset.next()) {
				memberList.add(convertRowToMatchList(rset));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return memberList;
	}
	
	private MatchMaster convertRowToMatchList(ResultSet rset) throws SQLException {
		MatchMaster match = new MatchMaster();
		
		match.setMmIdx(rset.getString("MM_IDX"));
		match.setUserId(rset.getString("USER_ID"));
		match.setTmCode(rset.getString("TM_CODE"));
		match.setLocalCode(rset.getString("LOCAL_CITY"));
		match.setAddress(rset.getString("ADDRESS"));
		match.setRegDate(rset.getDate("REG_DATE"));
		match.setTitle(rset.getString("TITLE"));
		match.setExpense(rset.getString("EXPENSE"));
		
		match.setGrade(rset.getString("GRADE"));
		match.setContent(rset.getString("CONTENT"));
		match.setTmMatch(rset.getInt("TM_MATCH"));
		match.setState(rset.getInt("STATE"));
		match.setMatchTime(rset.getString("MATCH_TIME"));
		match.setMatchDate(rset.getString("MATCH_DATE"));
		
		match.setTmName(rset.getString("tm_name"));
		match.setTmGrade(rset.getString("TM_GRADE"));
		match.setTmInfo(rset.getString("TM_INFO"));
		match.setGameCnt(rset.getInt("GAME_CNT"));
		match.setTmScore(rset.getInt("TM_SCORE"));
		match.setTmWin(rset.getInt("TM_WIN"));
		
		
		
		return match;
	}


	public List matchListSearch(Connection conn, String localCode, String date, String level) {
		List<MatchMaster> memberList = new ArrayList<MatchMaster>();	
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select "
				+ "MM_IDX,USER_ID,MATCH_MASTER.TM_CODE,LOCAL_CITY,ADDRESS,"
				+ "MATCH_MASTER.REG_DATE,TITLE,EXPENSE,GRADE,CONTENT,TM_MATCH"
				+ ",MATCH_TIME,MATCH_DATE,STATE"
				+ ",team.tm_name,TM_GRADE,TM_INFO,GAME_CNT,TM_SCORE,TM_WIN"
				+ " from MATCH_MASTER" + 
				" left outer join location" + 
				" on MATCH_MASTER.LOCAL_CODE = location.LOCAL_CODE"
				+ " left outer join team"
				+ " on MATCH_MASTER.TM_CODE = team.TM_CODE" 
				+ " where MATCH_MASTER.LOCAL_CODE = ? and MATCH_DATE = ? and GRADE = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1,localCode);
			pstm.setString(2,date); 
			pstm.setString(3,level);
			 
			
			rset = pstm.executeQuery();
			while(rset.next()) {
				memberList.add(convertRowToMatchList(rset));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return memberList;
	}


	
	
	
}
