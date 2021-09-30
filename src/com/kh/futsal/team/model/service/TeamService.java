package com.kh.futsal.team.model.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.file.FileDTO;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.member.model.dto.Member;
import com.kh.futsal.team.model.dao.TeamDAO;
import com.kh.futsal.team.model.dto.ResultDTO;
import com.kh.futsal.team.model.dto.Team;

public class TeamService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	private TeamDAO td = new TeamDAO();
	
//	팀 생성
	public void insertForCreating(Team team, Member member, List<FileDTO> fileDTOs) {
		Connection conn = template.getConnection();
		try {
			td.insertTeam(team,conn); //팀테이블
			for (FileDTO fileDTO : fileDTOs) {
				td.insertFile(fileDTO,team.getTmCode(),conn); //파일테이블
			}
			td.updateMemberIntoTeam(member, team, conn); //멤버테이블
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
//	팀 생성,가입
	public void updateMemberIntoTeam(Member member, Team team) {
		Connection conn = template.getConnection();
		try {
			td.updateMemberIntoTeam(member, team, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
//	팀 수정
	public void updateTeam(Team team, List<FileDTO> fileDTOs) {
		Connection conn = template.getConnection();
		try {
			td.updateTeam(team,conn);
			for (FileDTO fileDTO : fileDTOs) {
				td.deleteFile(team.getTmCode(),conn);
				td.insertFile(fileDTO,team.getTmCode(),conn);
			}
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
//	팀원 등급 변경
	public int updateGrade(String userId, String grade) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
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
	
//	팀장 위임
	public int updateGrades(String userId, Team team) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			res = td.updateGrades(userId,team,conn);
			if(res > 0) {
				res = td.updateTmManager(userId,team,conn);
				if(res > 0) {
					template.commit(conn);
				}
			}
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	
//	경기결과 업데이트
	public int updateWinner(String mgIdx, String wnrCode, String lsrCode) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			if(td.selectResultByMgIdx(mgIdx,conn) > 0) {
				res = td.updateWinner(mgIdx,lsrCode,conn);
			} else {
				res = td.insertWinner(mgIdx,wnrCode,conn);
			}
			if(res < 1) {
				return res;
			}
			td.updateTeamScore(wnrCode,lsrCode, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	
//	상대팀 평가 업데이트
	public int updateRslt(String mgIdx, String target, int rating) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			if(td.selectResultByMgIdx(mgIdx,conn) > 0) {
				res = td.updateRslt(mgIdx,target,rating,conn);
			} else {
				res = td.insertRslt(mgIdx,target,rating,conn);
			}
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	
//	회원정보에서 팀 정보를 삭제(팀추방,팀탈퇴,팀해체)
	public int updateMemberForLeaveTeam(String userId) {
		int res = 0;
		Connection conn = template.getConnection();
		try {
			res = td.updateMemberForLeaveTeam(userId, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
	
//	팀 해체
	public int updateDelDate(String tmCode, List<Member> tmMembers) {
		Connection conn = template.getConnection();
		int res = 0;
		try {
			res = td.updateDelDateForLeaveTeam(tmCode,conn);
			if(res > 0) {
				for (Member member : tmMembers) {
					res = td.updateMemberForLeaveTeam(member.getUserId(),conn);
				}
			}
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return res;
	}
		
//	팀 검색 (tmCode)
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
	
//	팀 검색 (tmName)
	public Team selectTeamByTmName(String tmName) {
		Connection conn = template.getConnection();
		Team team = null;
		try {
			team = td.selectTeamByTmName(tmName, conn);
		} finally {
			template.close(conn);
		}
		return team;
	}
	
//	팀 검색 (UserId)
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
	
//	파일 검색
	public FileDTO selectFileByTmCode(String tmCode) {
		Connection conn = template.getConnection();
		FileDTO file = null;
		try {
			file = td.selectFileByTmCode(tmCode, conn);
		} finally {
			template.close(conn);
		}
		return file;
	}
	
//	팀원 리스트 검색
	public List<Member> selectTmMembers(String tmCode) {
		Connection conn = template.getConnection();
		List<Member> tmMembers = null;
		try {
			tmMembers = td.selectTmMembers(tmCode, conn);
		} finally {
			template.close(conn);
		}
		return tmMembers;
	}

//	경기 결과 리스트 검색
	public List<ResultDTO> selectMatchGame(String tmCode) {
		Connection conn = template.getConnection();
		List<ResultDTO> results = null;
		try {
			results = td.selectMatchGame(tmCode, conn);
		} finally {
			template.close(conn);
		}
		return results;
	}
	
//	팀이 올린 게시글 검색
	public List<MatchMaster> selectTmBoards(String tmCode) {
		Connection conn = template.getConnection();
		List<MatchMaster> tmBoards = null;
		try {
			tmBoards = td.selectTmBoards(tmCode, conn);
		} finally {
			template.close(conn);
		}
		return tmBoards;
	}
	
//	팀이 신청한 게시글 검색
	public List<MatchMaster> selectTmApplications(String tmCode) {
		Connection conn = template.getConnection();
		List<MatchMaster> tmApplications = null;
		try {
			tmApplications = td.selectTmApplications(tmCode, conn);
		} finally {
			template.close(conn);
		}
		return tmApplications;
	}
	
//	팀의 평균 평점 계산
	public double selectTmAvgRating(String tmCode) {
		Connection conn = template.getConnection();
		double tmAvgRating = 0;
		try {
			Map<String, Integer> hostRating = td.selectHostRating(tmCode, conn);
			Map<String, Integer> rivalRating = td.selectRivalRating(tmCode, conn);
			tmAvgRating = (double)(hostRating.get("sum")+rivalRating.get("sum"))/(hostRating.get("cnt")+rivalRating.get("cnt"));
			if(Double.isNaN(tmAvgRating)) {
				tmAvgRating = 0;
			}
		} finally {
			template.close(conn);
		}
		return tmAvgRating;
	}

//	팀코드 랜덤 생성
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
