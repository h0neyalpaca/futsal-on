package com.kh.futsal.member.model.service;

import java.sql.Connection;

import com.kh.futsal.common.db.JDBCTemplate;
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
	
}
