package com.kh.futsal.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.alarm.model.dao.AlarmDao;
import com.kh.futsal.common.db.JDBCTemplate;
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

	public List<MatchMaster> matchListSearch(String localCode, String date, String level) {
		List res = null;
		Connection conn = template.getConnection();
		
		
		try {
			//매치글 리스트 받아오기
			res = matchDao.matchListSearch(conn,localCode,date,level);
			
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
	
	public List<MatchGame> matchMgList(String userId) {
		
		List<MatchGame> mgList = null;
		Connection conn = template.getConnection();
		
		try {
			mgList = matchDao.matchGameList(userId, conn);
			
		} finally {
			template.close(conn);
		}
		
		return mgList;
	}

	public void deleteMyApplicant(String mgIdx,MatchGame match) {
		
		Connection conn = template.getConnection();
		
		try {
			matchDao.deleteMatchGame(mgIdx,conn);
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

	public int matchRequset(String matchIdx) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = matchDao.matchRequset(matchIdx,conn);
			
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

	public int matchGameRegister(MatchGame matchGame) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			//매치글쓰기
			res = matchDao.matchGameRegister(matchGame, conn);
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


}
