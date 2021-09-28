package com.kh.futsal.matching.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.matching.model.dao.MatchDao;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.team.model.dto.Team;

public class MatchingService {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	MatchDao matchDao = new MatchDao();
	
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

}
