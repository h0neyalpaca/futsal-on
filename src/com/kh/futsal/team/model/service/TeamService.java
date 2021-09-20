package com.kh.futsal.team.model.service;

import java.sql.Connection;
import java.util.Random;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.team.model.dao.TeamDAO;
import com.kh.futsal.team.model.dto.Team;

public class TeamService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	private TeamDAO td = new TeamDAO();
	
	//팀 생성하기 *****해야됨
	public void createTeam(Team team) {
		
		Connection conn = template.getConnection();

		try {
			td.createTeam(team,conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	//팀 가입하기
	public void updateByTmCode(Member member, String tmCode) {
		Connection conn = template.getConnection();
		Team team = null;
		try {
			team = selectTeamByTmCode(tmCode);
			if(team != null) {
				td.updateByTmCode(member, tmCode, conn);
				template.commit(conn);
			} else {
				//팀코드로 검색한 팀이 없을 경우
				System.out.println("팀코드로 가입할 수 있는 팀이 없다.");
				template.rollback(conn);
			}
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	//유저 ID로 가입한 팀이 있는지 찾기
	public Team selectTeamByUserId(String userId) {
		Connection conn = template.getConnection();
		Team team = null;
		try {
			team = td.selectTeamByUserId(userId, conn);
		} finally {
			template.close(conn);
		}
		return team;
	}
	
	//팀 코드로 팀 찾기
	public Team selectTeamByTmCode(String tmCode) {
		Connection conn = template.getConnection();
		
		Team team = null;
		try {
			team = td.selectTeamByTmCode(tmCode, conn);
		} finally {
			template.close(conn);
		}
		return team;
	}
	
	//팀코드 랜덤 생성
	public String createTmCode (){
		StringBuffer tmCode = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			boolean rndIdx = rnd.nextBoolean();
			if (rndIdx) {
				tmCode.append(rnd.nextInt(10));
			}else {
				tmCode.append((char)((int)rnd.nextInt(26)+65));
			}
		}
		return tmCode.toString();
	}

}