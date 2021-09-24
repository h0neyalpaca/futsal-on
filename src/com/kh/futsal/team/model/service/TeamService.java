package com.kh.futsal.team.model.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.team.model.dao.TeamDAO;
import com.kh.futsal.team.model.dto.Team;

public class TeamService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	private TeamDAO td = new TeamDAO();
	
	public void insertTeam(Team team) {
		Connection conn = template.getConnection();
		try {
			td.insertTeam(team,conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public void updateMember(Member member, Team team) {
		Connection conn = template.getConnection();
		try {
			td.updateMember(member, team, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public int updateGrade(String userId, String grade) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			System.out.println(userId);
			System.out.println(grade);
			res = td.updateGrade(userId, grade, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	public int updateGrades(String userId, String managerId) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			res = td.updateGrades(userId,managerId,conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	
	public int updateTmManager(String userId, String tmCode) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			res = td.updateTmManager(userId,tmCode,conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}

	public int updateExpulsion(String userId) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			res = td.updateExpulsion(userId, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	
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
	
	public List<Member> selectTmMembers(String tmCode) {
		Connection conn = template.getConnection();
		List<Member> tmMembers = null;
		try {
			tmMembers = td.selectTmMembersByTeamCode(tmCode, conn);
		} finally {
			template.close(conn);
		}
		return tmMembers;
	}

	
	//팀코드 랜덤 생성
	public String createRandomCode(String keyword) {
		String result = "";
		keyword += new SimpleDateFormat("yyMMdd").format(new Date());
		for(int i=0; i<keyword.length(); i++) {
			String s = keyword.substring(i,i+1);
			switch(s) {
				case "0":s="A";break;
				case "1":s="B";break;
				case "2":s="C";break;
				case "3":s="D";break;
				case "4":s="E";break;
				case "5":s="F";break;
				case "6":s="G";break;
				case "7":s="H";break;
				case "8":s="I";break;
				case "9":s="J";break;
				case "a":s="K";break;
				case "b":s="L";break;
				case "c":s="M";break;
				case "d":s="N";break;
				case "e":s="O";break;
				case "f":s="P";break;
				case "g":s="Q";break;
				case "h":s="R";break;
				case "i":s="S";break;
				case "j":s="T";break;
				case "k":s="U";break;
				case "l":s="V";break;
				case "m":s="W";break;
				case "n":s="X";break;
				case "o":s="Y";break;
				case "p":s="Z";break;
				case "q":s="0";break;
				case "r":s="1";break;
				case "s":s="2";break;
				case "t":s="3";break;
				case "u":s="4";break;
				case "v":s="5";break;
				case "w":s="6";break;
				case "x":s="7";break;
				case "y":s="8";break;
				case "z":s="9";break;
				default:s="!";break;
			}
			result += s;
		}
		return result;
	}


}
