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

public class MatchDao {
	JDBCTemplate template = JDBCTemplate.getInstance();

	public int matchRegister(MatchMaster matchMaster, Connection conn) {
		int res = 0;		
		PreparedStatement pstm = null;
		
		String query = "INSERT INTO MATCH_MASTER (MM_IDX, USER_ID, TM_CODE, LOCAL_CODE, ADDRESS, REG_DATE, TITLE,EXPENSE, GRADE, CONTENT, TM_MATCH, MATCH_TIME,MATCH_DATE)"
					 + " VALUES(SC_MM_IDX.nextval,?,?,?,?,sysdate,?, ?, ?, ?,?, ?,?)";
		
		 
		
		try {
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, matchMaster.getUserId());
			pstm.setString(2, matchMaster.getTmCode());
			
			pstm.setString(3, matchMaster.getLocalCode());
			pstm.setString(4, matchMaster.getAddress());
			pstm.setString(5, matchMaster.getTitle());
			
			pstm.setString(6, matchMaster.getExpense());
			pstm.setString(7, matchMaster.getGrade());
			pstm.setString(8, matchMaster.getContent());
			pstm.setInt(9, matchMaster.getTmMatch());
			pstm.setString(10, matchMaster.getMatchTime());
			pstm.setString(11, matchMaster.getMatchDate());
			
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
	
	private MatchMaster convertRowToMatchListWithMatchNum(ResultSet rset) throws SQLException {
		MatchMaster match = new MatchMaster();
		
		match.setMmIdx(rset.getString("MM_IDX"));
		match.setUserId(rset.getString("USER_ID"));
		match.setTmCode(rset.getString("TM_CODE"));
		match.setLocalCode(rset.getString("LOCAL_CITY"));
		match.setAddress(rset.getString("ADDRESS"));
		match.setRegDate(rset.getDate("REG_DATE"));
		match.setTitle(rset.getString("TITLE"));
		match.setExpense(rset.getString("EXPENSE"));
		match.setMatchNum(rset.getInt("MATCH_NUM"));
		match.setGrade(rset.getString("GRADE"));
		match.setContent(rset.getString("CONTENT"));
		match.setTmMatch(rset.getInt("TM_MATCH"));
		match.setMatchTime(rset.getString("MATCH_TIME"));
		match.setMatchDate(rset.getString("MATCH_DATE"));
		match.setState(rset.getInt("STATE"));

		
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

	public List<MatchGame> matchGameList(String userId,Connection conn){
		
		List<MatchGame> gameList = new ArrayList<MatchGame>();	
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select mm_idx, mg_idx, user_id from match_game where user_id = ? order by state";

		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				MatchGame match = new MatchGame();
				match.setMmIdx(rset.getString("mm_idx"));
				match.setMgIdx(rset.getString("mg_idx"));
				match.setUserId(rset.getString("user_id"));
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
		
		String sql = "select * from match_master left outer join location on MATCH_MASTER.LOCAL_CODE = location.LOCAL_CODE where mm_idx = ? ";
		
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
	
	
	public MatchGame selectMatch(String mgIdx, Connection conn) {
		
		String sql = "select g.applicant_code,m.match_date,m.match_time,g.mm_idx,g.mg_idx,g.user_id from match_game g join match_master m on g.mm_idx = m.mm_idx where mg_idx = ? ";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		MatchGame match = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,mgIdx);
			rset = pstm.executeQuery();

			if(rset.next()) {
				match = new MatchGame();
				match.setApplicantCode(rset.getString("applicant_code"));
				match.setMatchDate(rset.getString("match_date")+" "+rset.getString("match_time"));
				match.setMgIdx(rset.getString("mg_idx"));
				match.setMmIdx(rset.getString("mm_idx"));
				match.setUserId(rset.getString("user_id"));
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return match;
	}


	public int matchRequset(String matchIdx, Connection conn) {
		int res = 0;		
		PreparedStatement pstm = null;
		String query = "update match_master set state = 1 where MM_IDX = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, matchIdx);
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
		return res;
	}


	public String checkRequset(String userId, Connection conn) {
		String id = null;	
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select TM_CODE"
				+ " FROM MEMBER"
				+ " WHERE USER_ID =?";
				
				
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			
			rset = pstm.executeQuery();
			if(rset.next()) {
				id = rset.getString("TM_CODE");
			}
			
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return id;
	}


	public int matchGameRegister(MatchGame matchGame, Connection conn) {
		int res = 0;		
		PreparedStatement pstm = null;
		
		String query = "INSERT INTO MATCH_GAME (MG_IDX, MM_IDX, MATCH_DATE, STATE, APPLICANT_CODE)"
					 + " VALUES(SC_MG_IDX.nextval,?,?,1,?)";
		
		 
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, matchGame.getMmIdx());
			pstm.setString(2, matchGame.getMatchDate());
			pstm.setString(3, matchGame.getApplicantCode());
							
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
		return res;
	}


	public int matchUpdate(String teamCode, Connection conn) {
		int res = 0;		
		PreparedStatement pstm = null;
		String query = "update TEAM set GAME_CNT = GAME_CNT+1 where TM_CODE = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, teamCode);
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
		return res;
	}	
}
