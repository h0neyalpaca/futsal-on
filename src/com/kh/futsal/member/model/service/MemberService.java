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


	public void leaveID(String userId) {
		Connection conn = template.getConnection();
		
		try {
			memberDao.leaveId(userId,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}


	public Member selectMemberBynickName(String nickName) {
		Connection conn = template.getConnection();
	    Member member = null;
	      
	      try {
	         member = memberDao.selectMemberBynickName(nickName, conn);
	      } finally {
	         template.close(conn);
	      }
	      
	      return member;
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
	
	public void updateMember(Member member) {
		Connection conn = template.getConnection();
		
		try {
			memberDao.updateMember(member,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
}
