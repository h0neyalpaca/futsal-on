package com.kh.futsal.team.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			res = pstm.executeUpdate();
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
	
	public int insertWinner(String mgIdx, String winner, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "INSERT INTO RESULT"+
					"(TH_IDX,WINNER,MG_IDX) "+
					"VALUES(SC_TH_IDX.NEXTVAL,?,?) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, winner);
			pstm.setString(2, mgIdx);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int updateWinner(String mgIdx, String winner, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE RESULT SET WINNER = ? WHERE MG_IDX = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, winner);
			pstm.setString(2, mgIdx);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int insertRslt(String mgIdx, String target, int rating, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "";
			if(target.equals("host")) {
				sql = "INSERT INTO RESULT (TH_IDX,MG_IDX,HOST_RATING) VALUES(SC_TH_IDX.NEXTVAL,?,?) ";
			} else {
				sql = "INSERT INTO RESULT (TH_IDX,MG_IDX,RIVAL_RATING) VALUES(SC_TH_IDX.NEXTVAL,?,?) ";
			}
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mgIdx);
			pstm.setInt(2, rating);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
	public int updateRslt(String mgIdx, String target, int rating, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "";
			if(target.equals("host")) {
				sql = "UPDATE RESULT SET HOST_RATING = ? WHERE MG_IDX = ? ";
			} else {
				sql = "UPDATE RESULT SET RIVAL_RATING = ? WHERE MG_IDX = ? ";
			}
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, rating);
			pstm.setString(2, mgIdx);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}

	public int updateTeamScore(String wnrTmCode, String lsrTmCode, Connection conn) {
		int res = 0;
		System.out.println("w:"+wnrTmCode);
		System.out.println("l:"+lsrTmCode);
		PreparedStatement pstm = null;
		try {
			String sql = "UPDATE TEAM SET TM_WIN = TM_WIN+1 WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, wnrTmCode);
			res = pstm.executeUpdate();
			if(res < 1) {
				return res;
			}
			sql = "UPDATE TEAM SET TM_LOSE = TM_LOSE+1 WHERE TM_CODE = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lsrTmCode);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		return res;
	}
	
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
	
	public int updateTmPenalty(String userId, Connection conn) {
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
	
	public int insertTmPenalty(String userId, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		try {
			String sql = "INSERT INTO TEAM_PENALTY VALUES (?, SYSDATE) ";
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

	public int selectTmPenaltyUser(String userId, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM TEAM_PENALTY WHERE USER_ID = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				res = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return res;
	}
	
	public boolean selectTmPenalty(String userId, Connection conn) {
		boolean flag = false;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT * FROM TEAM_PENALTY WHERE USER_ID = ? AND SYSDATE < (DEL_DATE+(INTERVAL '7'DAY)) ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			if(rset.next()) {
				flag = true;
				System.out.println("dao에서 검색결과가 있으면 패널티 있는것임 "+flag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return flag;
	}
	
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
	
	public FileDTO selectFile(String tmCode, Connection conn) {
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
	
	public List<ResultDTO> selectMatchGame(String tmCode, Connection conn) {
		List<ResultDTO> results = new ArrayList<ResultDTO>();
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "select rs.th_idx, mg.mg_idx, mm.match_date, mm.match_time, tm.tm_code as host_code, tm.tm_name as host_name, te.tm_code as rival_code, te.tm_name as rival_name, rs.winner, rs.host_rating, rs.rival_rating "
					+ "from match_master mm "
					+ "join match_game mg using(mm_idx) "
					+ "join team tm on (mm.tm_code = tm.tm_code) "
					+ "join team te on (mg.applicant_code = te.tm_code) "
					+ "left join result rs on (mg.mg_idx = rs.mg_idx) "
					+ "where (mm.tm_code = ? or mg.applicant_code = ?) and mm.state = 1 order by mm.match_date desc, mm.match_time desc ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			pstm.setString(2, tmCode);
			rset = pstm.executeQuery();
			while(rset.next()) {
				results.add(convertRowToResult(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return results;
	}

	public int selectResultByMgIdx(String mgIdx, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT TH_IDX FROM RESULT WHERE MG_IDX = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, mgIdx);
			rset = pstm.executeQuery();
			if(rset.next()) {
				res += 1;
			}
			System.out.println("res : " +res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		
		return res;
	}
	
	public List<MatchMaster> selectTmBoards(String tmCode, Connection conn) {
		List<MatchMaster> tmBoards = new ArrayList<MatchMaster>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT * FROM MATCH_MASTER MM JOIN TEAM TM USING(TM_CODE) LEFT JOIN MATCH_GAME MG USING(MM_IDX) "
					+ "WHERE TM_CODE = ? AND MM.MATCH_NUM IS NULL ORDER BY MM.MATCH_DATE DESC, MM.MATCH_TIME DESC ";
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
	
	public List<MatchMaster> selectMcBoards(String tmCode, Connection conn) {
		List<MatchMaster> mcBoards = new ArrayList<MatchMaster>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT * FROM MATCH_MASTER MM JOIN TEAM TM USING(TM_CODE) LEFT JOIN MATCH_GAME MG USING(MM_IDX) "
					+ "WHERE TM_CODE = ? AND MM.MATCH_NUM IS NOT NULL ORDER BY MM.MATCH_DATE DESC, MM.MATCH_TIME DESC ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			while(rset.next()) {
				mcBoards.add(convertRowToMatchMaster(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return mcBoards;
	}
	
	public List<MatchMaster> selectTmApplications(String tmCode, Connection conn) {
		List<MatchMaster> tmApplications = new ArrayList<MatchMaster>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT * FROM MATCH_MASTER MM JOIN MATCH_GAME MG USING(MM_IDX) JOIN TEAM USING(TM_CODE) WHERE APPLICANT_CODE = ? ORDER BY MM.MATCH_DATE DESC, MM.MATCH_TIME DESC ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			while(rset.next()) {
				tmApplications.add(convertRowToMatchMaster(rset));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return tmApplications;
	}
	
	public Map<String,Integer> selectHostRating(String tmCode, Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Map<String, Integer> hostRating = new HashMap<>();
		try {
			String sql = "select NVL(sum(rs.host_rating),0) as SUM, count(*) as CNT "
					+ "from match_master mm "
					+ "join match_game mg using(mm_idx) "
					+ "left join result rs on (mg.mg_idx = rs.mg_idx) "
					+ "where mm.tm_code = ? and host_rating > 0 and host_rating is not null ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			if(rset.next()) {
				hostRating.put("sum", rset.getInt("SUM"));
				hostRating.put("cnt", rset.getInt("CNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return hostRating;
	}
	
	public Map<String,Integer> selectRivalRating(String tmCode, Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Map<String, Integer> rivalRating = new HashMap<>();
		try {
			String sql = "select NVL(sum(rs.rival_rating),0) as SUM, count(*) as CNT "
					+ "from match_master mm "
					+ "join match_game mg using(mm_idx) "
					+ "left join result rs on (mg.mg_idx = rs.mg_idx) "
					+ "where mg.applicant_code = ? and rival_rating > 0 and rival_rating is not null";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tmCode);
			rset = pstm.executeQuery();
			if(rset.next()) {
				rivalRating.put("sum", rset.getInt("SUM"));
				rivalRating.put("cnt", rset.getInt("CNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(rset,pstm);
		}
		return rivalRating;
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
		team.setTmWin(rset.getInt("TM_WIN"));
		team.setTmLose(rset.getInt("TM_LOSE"));
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
		matchMaster.setMatchNum(rset.getInt("MATCH_NUM"));
		matchMaster.setMatchTime(rset.getString("MATCH_TIME"));
		matchMaster.setMatchDate(rset.getString("MATCH_DATE"));
		matchMaster.setState(rset.getInt("STATE"));
		//팀 정보
		matchMaster.setTmName(rset.getString("TM_NAME"));
		matchMaster.setTmGrade(rset.getString("TM_GRADE"));
		matchMaster.setTmInfo(rset.getString("TM_INFO"));
		matchMaster.setGameCnt(rset.getInt("GAME_CNT"));
		matchMaster.setTmWin(rset.getInt("TM_WIN"));
		matchMaster.setTmLose(rset.getInt("TM_LOSE"));
		return matchMaster;
	}
	
	private ResultDTO convertRowToResult(ResultSet rset) throws SQLException {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setThIdx(rset.getString("TH_IDX"));
		resultDTO.setMgIdx(rset.getString("MG_IDX"));
		resultDTO.setMatchDate(rset.getString("MATCH_DATE"));
		resultDTO.setMatchTime(rset.getString("MATCH_TIME"));
		resultDTO.setHostCode(rset.getString("HOST_CODE"));
		resultDTO.setHostName(rset.getString("HOST_NAME"));
		resultDTO.setRivalCode(rset.getString("RIVAL_CODE"));
		resultDTO.setRivalName(rset.getString("RIVAL_NAME"));
		resultDTO.setWinner(rset.getString("WINNER"));
		resultDTO.setHostRating(rset.getInt("HOST_RATING"));
		resultDTO.setRivalRating(rset.getInt("RIVAL_RATING"));
		return resultDTO;
	}

}
