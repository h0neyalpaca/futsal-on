package com.kh.futsal.team.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public int insertTeam(Team team, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "INSERT INTO TEAM"+
					"(TM_CODE,LOCAL_CODE,MANAGER_ID,TM_NAME,TM_GRADE,TM_INFO) "+
					"VALUES(?,?,?,?,?,?) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, team.getTmCode());
			pstm.setString(2, team.getLocalCode());
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

	//팀 생성, 팀 가입
	public int updateMemberIntoTeam(Member member, Team team, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE MEMBER SET TM_CODE = ?, GRADE = ? WHERE USER_ID = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, team.getTmCode());
			if(member.getUserId().equals(team.getManagerId())) {
				pstm.setString(2, "ME03");
			} else {
				pstm.setString(2, "ME01");
			};
			pstm.setString(3, member.getUserId());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	//팀원 등급 변경
	public int updateGrade(String userId, String grade, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE MEMBER SET GRADE = ? WHERE USER_ID = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, grade);
			pstm.setString(2, userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	//팀장위임
	public int updateGrades(String userId, String managerId, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE MEMBER SET GRADE = ? WHERE USER_ID = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "ME01");
			pstm.setString(2, managerId);
			res = pstm.executeUpdate();
			if(res < 1) {
				return res;
			}
			pstm.setString(1, "ME03");
			pstm.setString(2, userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	public int updateTmManager(String userId, String tmCode, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE TEAM SET MANAGER_ID = ? WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, tmCode);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	//회원정보에서 팀 정보를 삭제(팀추방 ,팀탈퇴,팀해체)
	public int updateMemberForLeaveTeam(String userId, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE MEMBER SET TM_CODE = '', GRADE = 'ME00' WHERE USER_ID = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	//팀 해체 일자 업데이트
	public int updateDelDateForLeaveTeam(String tmCode, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE TEAM SET DEL_DATE = SYSDATE WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	//tmCode로 팀 검색
	public Team selectTeamByTmCode(String tmCode, Connection conn) {
		Team team = new Team();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM TEAM WHERE TM_CODE = ? ";
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
	
	//userId로 팀 검색
	public Team selectTeamByUserId(String userId, Connection conn) {
		Team team = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM TEAM WHERE TM_CODE = (SELECT TM_CODE FROM MEMBER WHERE USER_ID=?) ";
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
	
	//팀코드로 팀원 리스트 호출
	public List<Member> selectTmMembersByTeamCode(String tmCode, Connection conn) {
		List<Member> tmMembers = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT USER_ID,GRADE FROM MEMBER WHERE TM_CODE = ? ORDER BY GRADE DESC ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			while(rset.next()) {
				tmMembers.add(convertRowToMember(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return tmMembers;
	}
	
	private Member convertRowToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setUserId(rset.getString("USER_ID"));
		member.setGrade(rset.getString("GRADE"));
		return member;
		
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
		team.setDelDate(rset.getDate("DEL_DATE"));
		return team;
	}

}
