package com.kh.futsal.matching.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.matching.model.dao.MatchDao;
import com.kh.futsal.matching.model.dto.MatchGame;
import com.kh.futsal.matching.model.dto.MatchMaster;

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
	
	public List<MatchMaster> matchGameList(String userId) {
		
		List<MatchGame> mmIdxList = new ArrayList<MatchGame>();
		List<MatchMaster> res = new ArrayList<MatchMaster>();
		
		Connection conn = template.getConnection();
		
		try {
			
			mmIdxList = matchDao.matchGameList(userId, conn);
			
			for (int i = 0; i < mmIdxList.size(); i++) {
				res.add(matchDao.matchGame(mmIdxList.get(i).getMmIdx(), conn));
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

	public void deleteMyApplicant(String mgIdx) {
		
		Connection conn = template.getConnection();
		
		try {
			matchDao.deleteMatchGame(mgIdx,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
		
		
	}
}
