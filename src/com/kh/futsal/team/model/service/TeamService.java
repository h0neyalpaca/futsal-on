package com.kh.futsal.team.model.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	
	//--------------------------윗줄완료
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
	
	//팀 코드로 팀 멤버 찾기
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
		keyword += new SimpleDateFormat("yyyyMMdd").format(new Date());
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
				case "-":s="_";break;
				case "_":s="-";break;
				default:s="!";break;
			}
			result += s;
		}
		return result;
	}
}
