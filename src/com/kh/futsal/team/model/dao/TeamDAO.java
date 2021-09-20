package com.kh.futsal.team.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.futsal.common.code.member.MemberGrade;
import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.team.model.dto.Team;

public class TeamDAO {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public TeamDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public int createTeam(Team team, Connection conn) {
		
		int res = 0;
		
		PreparedStatement pstm = null;
		String sql = "INSERT INTO TEAM"+
				"(TM_CODE,LOCAL_CODE,MANAGER_ID,TM_NAME,TM_GRADE,TM_INFO) "+
				"VALUSE(?,?,?,?,?,?) ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, team.getTmCode());
			pstm.setString(2, team.getLocalCode().toString());
			pstm.setString(3, team.getManagerId());
			pstm.setString(4, team.getTmName());
			pstm.setString(5, team.getTmGrade());
			pstm.setString(6, team.getTmInfo());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	public int updateByTmCode(Member member, String tmCode, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		String sql = "UPDATE MEMBER SET TM_CODE = ?, GRADE = ? WHERE USER_ID = ? ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);			
			pstm.setString(2, "ME01");
			pstm.setString(3, member.getUserId());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	public Team selectTeamByUserId(String userId, Connection conn) {
		Team team = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM TEAM WHERE TM_CODE = (SELECT TM_CODE FROM MEMBER WHERE USER_ID=?) ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				team = convertRowToTeam(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return team;
	}
	
	public Team selectTeamByTmCode(String tmCode, Connection conn) {
		Team team = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM TEAM WHERE TM_CODE = ? ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			if(rset.next()) {
				team = convertRowToTeam(rset);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return team;
	}
	
	private Team convertRowToTeam(ResultSet rset) throws SQLException {
		Team team = new Team();
		team.setTmCode(rset.getString("TM_CODE"));
		team.setLocalCode(rset.getString("LOCAL_CODE"));
		team.setManagerId(rset.getString("MANAGER_ID"));
		team.setTmName(rset.getString("TM_NAME"));
		team.setTmGrade(rset.getString("TM_GRADE"));
		team.setTmInfo(rset.getString("TM_INFO"));
		team.setGameCnt(rset.getInt("GAME_CNT"));
		team.setTmScore(rset.getInt("TM_SCORE"));
		team.setTmWin(rset.getInt("TM_WIN"));
		team.setRegDate(rset.getDate("REG_DATE"));
		return team;
	}

	

}
