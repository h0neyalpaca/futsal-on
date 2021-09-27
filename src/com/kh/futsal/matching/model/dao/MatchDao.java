package com.kh.futsal.matching.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.matching.model.dto.MatchGame;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.support.model.dto.Support;

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


	public List matchListView(Connection conn) {
		List<MatchMaster> memberList = new ArrayList<MatchMaster>();	
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select "
				+ "MM_IDX,USER_ID,TM_CODE,LOCAL_CODE,ADDRESS,"
				+ "REG_DATE,TITLE,EXPENSE,GRADE,CONTENT,TM_MATCH"
				+ ",MATCH_TIME"
				+ " from MATCH_MASTER";
		
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
	
	public List<MatchGame> matchGameList(String userId,Connection conn){
		
		List<MatchGame> gameList = new ArrayList<MatchGame>();	
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select mm_idx, mg_idx from match_game where APPLICANT_CODE = ?";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				MatchGame match = new MatchGame();
				match.setMmIdx(rset.getString("mm_idx"));
				match.setMgIdx(rset.getString("mg_idx"));
				gameList.add(match);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		
		return gameList;
	}
	
	public MatchMaster matchGame(String mmIdx, Connection conn) {
		
		String sql = "select * from match_master where mm_idx = ? ";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		MatchMaster matchMaster = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,mmIdx);
			rset = pstm.executeQuery();

			if(rset.next()) {
				matchMaster = convertRowToMatchListWithMatchNum(rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return matchMaster;
	}
	
	public void deleteMatchGame(String mgIdx, Connection conn) {
		
		String sql = "delete from match_game where mg_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, mgIdx);
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
	}	
	
	private MatchMaster convertRowToMatchListWithMatchNum(ResultSet rset) throws SQLException {
		MatchMaster match = new MatchMaster();
		
		match.setMmIdx(rset.getString("MM_IDX"));
		match.setUserId(rset.getString("USER_ID"));
		match.setTmCode(rset.getString("TM_CODE"));
		match.setLocalCode(rset.getString("LOCAL_CODE"));
		match.setAddress(rset.getString("ADDRESS"));
		match.setRegDate(rset.getDate("REG_DATE"));
		match.setTitle(rset.getString("TITLE"));
		match.setExpense(rset.getString("EXPENSE"));
		match.setMatchNum(rset.getInt("MATCH_NUM"));
		match.setGrade(rset.getString("GRADE"));
		match.setContent(rset.getString("CONTENT"));
		match.setTmMatch(rset.getInt("TM_MATCH"));
		match.setMatchTime(rset.getString("MATCH_TIME"));
		
		return match;
	}	
	
	private MatchMaster convertRowToMatchList(ResultSet rset) throws SQLException {
		MatchMaster match = new MatchMaster();
		
		match.setMmIdx(rset.getString("MM_IDX"));
		match.setUserId(rset.getString("USER_ID"));
		match.setTmCode(rset.getString("TM_CODE"));
		match.setLocalCode(rset.getString("LOCAL_CODE"));
		match.setAddress(rset.getString("ADDRESS"));
		match.setRegDate(rset.getDate("REG_DATE"));
		match.setTitle(rset.getString("TITLE"));
		match.setExpense(rset.getString("EXPENSE"));
		match.setGrade(rset.getString("GRADE"));
		match.setContent(rset.getString("CONTENT"));
		match.setTmMatch(rset.getInt("TM_MATCH"));
		match.setMatchTime(rset.getString("MATCH_TIME"));
		
		return match;
	}

}
