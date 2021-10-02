package com.kh.futsal.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.alarm.model.dao.AlarmDao;
import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.pagination.PageInfo;
import com.kh.futsal.matching.model.dao.MatchDao;
import com.kh.futsal.matching.model.dto.MatchGame;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.team.model.dto.Team;

public class MatchingService {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	MatchDao matchDao = new MatchDao();
	AlarmDao alarmDao = new AlarmDao();
	
	public int matchRegister(MatchMaster matchMaster) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			//매치글쓰기
			res = matchDao.matchRegister(matchMaster, conn);
			//방금 가입한 회원의 아이디로 정보를 다시 조회			
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
		
	}

	public List<MatchMaster> matchListView() {
		List res = null;
		Connection conn = template.getConnection();
		
		
		try {
			//매치글 리스트 받아오기
			res = matchDao.matchListView(conn);
			
		} finally {
			template.close(conn);
		}
		
		return res;
	}

	public List<MatchMaster> matchListSearch(String localCode, String date, String level, String match) {
		List res = null;
		Connection conn = template.getConnection();
		
		
		try {
			//매치글 리스트 받아오기
			res = matchDao.matchListSearch(conn,localCode,date,level,match);
			
		} finally {
			template.close(conn);
		}
		
		return res;
	}
	public List<Team> matchTeamView() {
		List res = null;
		Connection conn = template.getConnection();
		
		
		try {
			//매치글 리스트 받아오기
			res = matchDao.matchListView(conn);
			
		} finally {
			template.close(conn);
		}
		
		return res;
	}
	
	public List<MatchMaster> matchGameList(List<MatchGame> mgList) {
		
		List<MatchMaster> res = new ArrayList<MatchMaster>();
		Connection conn = template.getConnection();
		try {
			for (int i = 0; i < mgList.size(); i++) {
				res.add(matchDao.matchGame(mgList.get(i).getMmIdx(), conn));
			}
		} finally {
			template.close(conn);
		}
		return res;
	}
	
	public List<MatchGame> matchMgList(String userId, PageInfo page) {
		
		List<MatchGame> mgList = null;
		Connection conn = template.getConnection();
		
		try {
			mgList = matchDao.matchGameList(userId, page, conn);
			
		} finally {
			template.close(conn);
		}
		
		return mgList;
	}
	
	public int selectBoardCnt(String userId) {
		int res = 0;
		Connection conn = template.getConnection();
		
		try {
			res = matchDao.selectBoardCnt(conn, userId);
		} finally {
			template.close(conn);
		}
		
		return res;
	}

	public void deleteMyApplicant(String mgIdx,MatchGame match) {
		Connection conn = template.getConnection();
		
		try {
			matchDao.deleteMatchGame(mgIdx,conn);
			MatchGame matchGame = selectMatch(mgIdx); 
			matchDao.upDateMatchNum(matchGame.getMmIdx(),conn);
			alarmDao.deleteAlarm(match.getUserId(), match.getMmIdx(),conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	public MatchGame selectMatch(String mgIdx) {
		Connection conn = template.getConnection();
		MatchGame match = null;
		try {
			match = matchDao.selectMatch(mgIdx,conn);
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		return match;
	}

	public int matchRequset(String matchIdx, String match) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			res = matchDao.matchRequset(matchIdx,match,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		return res;
	}

	public String checkRequset(String userId) {
		Connection conn = template.getConnection();
		String id = null;
		try {
			id = matchDao.checkRequset(userId,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		return id;
	}

	public int matchGameRegister(MatchGame matchGame, String match) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			//매치글쓰기
			res = matchDao.matchGameRegister(matchGame,match, conn);
			//방금 가입한 회원의 아이디로 정보를 다시 조회			
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
	}

	public int matchUpdate(String teamCode) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			//전적 추가
			res = matchDao.matchUpdate( teamCode,conn);
			//방금 가입한 회원의 아이디로 정보를 다시 조회			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		return res;
	}

	public MatchMaster getMatchModify(String matchIdx) {
		Connection conn = template.getConnection();
		MatchMaster res = null;
		try {
			//매치글쓰기
			res = matchDao.getMatchModify(matchIdx, conn);
			//방금 가입한 회원의 아이디로 정보를 다시 조회			
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
	}

	public int matchModify(MatchMaster matchMaster) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			//매치글쓰기
			res = matchDao.matchModify(matchMaster, conn);
			//방금 가입한 회원의 아이디로 정보를 다시 조회			
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
	}

	public int matchDel(String matchIdx) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			//매치글 삭제
			res = matchDao.matchDel(matchIdx, conn);
					
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
	}

	public List<MatchMaster> RecentView(String match) {
		List<MatchMaster> RecentMatchList = new ArrayList<MatchMaster>();
		
		Connection conn = template.getConnection();
		
		try {
			
			RecentMatchList = matchDao.RecentMatch(conn,match);
					
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		return RecentMatchList;
	}

	public List<MatchMaster> mercenaryListView() {
		List res = null;
		Connection conn = template.getConnection();
		
		
		try {
			//매치글 리스트 받아오기
			res = matchDao.mercenaryListView(conn);
			
		} finally {
			template.close(conn);
		}
		
		return res;
	}

	public String checkMatchIdx(String matchDate, String userId) {
		Connection conn = template.getConnection();
		String id = null;
		try {
			id = matchDao.checkMatchIdx(userId,matchDate,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		return id;
	}

	


}
