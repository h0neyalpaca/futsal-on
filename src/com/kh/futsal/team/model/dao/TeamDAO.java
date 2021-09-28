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
import com.kh.futsal.common.file.FileDTO;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.team.model.dto.ResultDTO;
import com.kh.futsal.team.model.dto.Team;

public class TeamDAO {

	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public TeamDAO() {
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
	
	public int insertFile(FileDTO fileDTO, String tmCode, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "INSERT INTO FILE_INFO"+
					"(FL_IDX,TM_CODE,ORIGIN_FILE_NAME,RENAME_FILE_NAME,SAVE_PATH) "+
					"VALUES(SC_FL_IDX.NEXTVAL,?,?,?,?) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			pstm.setString(2, fileDTO.getOriginFileName());
			pstm.setString(3, fileDTO.getRenameFileName());
			pstm.setString(4, fileDTO.getSavePath());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int deleteFile(String tmCode, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "DELETE FILE_INFO WHERE TM_CODE = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
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

	public int updateTeam(Team team, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE TEAM SET TM_GRADE = ?, LOCAL_CODE = ?, TM_INFO = ? WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, team.getTmGrade());
			pstm.setString(2, team.getLocalCode());
			pstm.setString(3, team.getTmInfo());
			pstm.setString(4, team.getTmCode());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
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
	
	public int updateGrades(String userId, Team team, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE MEMBER SET GRADE = ? WHERE USER_ID = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "ME01");
			pstm.setString(2, team.getManagerId());
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

	public int updateTmManager(String userId, Team team, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE TEAM SET MANAGER_ID = ? WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, team.getTmCode());
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
	
	//tmName로 팀 검색
	public Team selectTeamByTmName(String tmName, Connection conn) {
		Team team = new Team();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM TEAM WHERE TM_NAME= ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmName);
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
	
	//tmCode로 파일 검색
	public FileDTO selectFileByTmCode(String tmCode, Connection conn) {
		FileDTO file = new FileDTO();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM FILE_INFO WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			if(rset.next()) {
				file.setFlIdx(rset.getString("FL_IDX"));
				file.setTmCode(rset.getString("TM_CODE"));
				file.setOriginFileName(rset.getString("ORIGIN_FILE_NAME"));
				file.setRenameFileName(rset.getString("RENAME_FILE_NAME"));
				file.setSavePath(rset.getString("SAVE_PATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return file;
	}

	//팀코드로 팀원 리스트 호출
	public List<Member> selectTmMembers(String tmCode, Connection conn) {
		List<Member> tmMembers = new ArrayList<Member>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT USER_ID,USER_NAME,GRADE FROM MEMBER WHERE TM_CODE = ? ORDER BY GRADE DESC ";
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
	
	//tmCode로 경기결과 리스트 호출
	public List<ResultDTO> selectMatchGame(String tmCode, Connection conn) {
		List<ResultDTO> results = new ArrayList<ResultDTO>();
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "select mg.mg_idx, mm.match_date, mm.match_time, mg.applicant_code, tm.tm_name as tm_name1, te.tm_name as tm_name2, rs.winner, rs.host_rating, rs.rival_rating "
					+ "from match_master mm "
					+ "join match_game mg using(mm_idx) "
					+ "join team tm on (mm.tm_code = tm.tm_code) "
					+ "join team te on (mg.applicant_code = te.tm_code) "
					+ "left join result rs on (mg.mg_idx = rs.mg_idx) "
					+ "where (mm.tm_code = ? or mg.applicant_code = ?) and mm.state = 1 order by mg.mg_idx desc ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			pstm.setString(2, tmCode);
			rset = pstm.executeQuery();
			while(rset.next()) {
				ResultDTO resultDTO = new ResultDTO();
				resultDTO.setMgIdx(rset.getString("MG_IDX"));
				resultDTO.setMatchDate(rset.getString("MATCH_DATE"));
				resultDTO.setMatchTime(rset.getString("MATCH_TIME"));
				resultDTO.setApplicantCode(rset.getString("APPLICANT_CODE"));
				resultDTO.setTmName(rset.getString("TM_NAME1"));
				resultDTO.setApplicantName(rset.getString("TM_NAME2"));
				resultDTO.setWinner(rset.getString("WINNER"));
				resultDTO.setHostRating(rset.getInt("HOST_RATING"));
				resultDTO.setRivalRating(rset.getInt("RIVAL_RATING"));
				results.add(resultDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return results;
	}

	//팀코드로 팀이 올린 게시글 호출
	public List<MatchMaster> selectTmBoards(String tmCode, Connection conn) {
		List<MatchMaster> tmBoards = new ArrayList<MatchMaster>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT * FROM MATCH_MASTER WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			while(rset.next()) {
				tmBoards.add(convertRowToMatchMaster(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return tmBoards;
	}
	
	private Member convertRowToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setUserId(rset.getString("USER_ID"));
		member.setUserName(rset.getString("USER_NAME"));
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

	private MatchMaster convertRowToMatchMaster(ResultSet rset) throws SQLException {
		MatchMaster matchMaster = new MatchMaster();
		matchMaster.setMmIdx(rset.getString("MM_IDX"));
		matchMaster.setUserId(rset.getString("USER_ID"));
		matchMaster.setTmCode(rset.getString("TM_CODE"));
		matchMaster.setLocalCode(rset.getString("LOCAL_CODE"));
		matchMaster.setAddress(rset.getString("ADDRESS"));
		matchMaster.setRegDate(rset.getDate("REG_DATE"));
		matchMaster.setTitle(rset.getString("TITLE"));
		matchMaster.setExpense(rset.getString("EXPENSE"));
		matchMaster.setGrade(rset.getString("GRADE"));
		matchMaster.setContent(rset.getString("CONTENT"));
		matchMaster.setTmMatch(rset.getInt("TM_MATCH"));
		matchMaster.setMatchTime(rset.getString("MATCH_TIME"));
		matchMaster.setMatchDate(rset.getString("MATCH_DATE"));
		return matchMaster;
	}

}
