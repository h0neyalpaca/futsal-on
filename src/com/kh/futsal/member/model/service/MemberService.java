package com.kh.futsal.member.model.service;

import java.sql.Connection;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.http.HttpConnector;
import com.kh.futsal.common.http.RequestParams;
import com.kh.futsal.common.mail.MailSender;
import com.kh.futsal.member.model.dao.MemberDao;
import com.kh.futsal.member.model.dto.Member;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();

	
	public Member memberAuthenticate(String userId, String password) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.memberAuthenticate(userId, password, conn);		
		}finally {
			template.close(conn);
		}
		
		return member;
	}


	public void authenticateEmail(Member member, String persistToken) {
		HttpConnector conn = new HttpConnector();
		
		String queryString = conn.urlEncodedForm( RequestParams.builder()
				.param("mail-template", "join-auth-email")
				.param("persistToken", persistToken)
				.param("userId", member.getUserId())
				.build()); 

		String mailTemplate = conn.get("http://localhost:7070/mail?"+queryString);
		MailSender sender = new MailSender();
		sender.sendEmail(member.getEmail(), "환영합니다. " + member.getUserId() + "님", mailTemplate);
		
	}
	
	public void searchPassEmail(Member member, String randomPass) {
		HttpConnector conn = new HttpConnector();	
		
		String queryString = conn.urlEncodedForm( RequestParams.builder()
				.param("mail-template", "search-pass-email")
				.param("password", randomPass)
				.param("userId", member.getUserId())
				.build()); 

		String mailTemplate = conn.get("http://localhost:7070/mail?"+queryString);
		MailSender sender = new MailSender();
		sender.sendEmail(member.getEmail(),member.getUserId() + "님 비밀번호 안내메일입니다.", mailTemplate);
		
	}

	public int insertMember(Member member) {
		
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			//회원가입처리
			res = memberDao.insertMember(member, conn);
			//방금 가입한 회원의 아이디로 정보를 다시 조회			
			Member m = memberDao.selectMemberById(member.getUserId(), conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
		
	}


	public Member selectMemberById(String userId) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.selectMemberById(userId, conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}

	public Member selectMemberByNick(String userNick) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.selectMemberByNick(userNick, conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}
	
	public void leaveID(String userId) {
		Connection conn = template.getConnection();

		try {
			memberDao.leaveId(userId,conn);

			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	public void updateMember(Member member) {
		Connection conn = template.getConnection();
		
		try {
			memberDao.updateMember(member,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}

	public Member searchById(String userName, String email) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.searchById(userName,email,conn);
		} finally {
			template.close(conn);
		}

		return member;
	}


	public Member searchByPass(String userId, String email) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.searchByPass(userId,email,conn);
		} finally {
			template.close(conn);
		}

		return member;
	}


	public String changePass(String userId, String email) {
		Connection conn = template.getConnection();
		String randomPass = "";
		
		try {
			randomPass = memberDao.changePass(userId,email,conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			e.printStackTrace();
		}finally {
			template.close(conn);
		}
		
		return randomPass;	
	}
	
}
