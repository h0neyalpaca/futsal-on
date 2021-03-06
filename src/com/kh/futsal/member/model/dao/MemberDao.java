package com.kh.futsal.member.model.dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.member.model.dto.Member;

public class MemberDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public MemberDao() {
		// TODO Auto-generated constructor stub
	}

	//사용자의 아이디와 password를 전달 받아서
	//아이디와 password가 일치하는 회원을 조회하는 메서드
	public Member memberAuthenticate(String userId, String password, Connection conn) {
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
			
		try {
			String query = "select * from member where user_id = ? and password = ? ";			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			rset = pstm.executeQuery();
				
			String[] fieldArr = {"user_id","tm_code","password","user_name","grade","user_nick","email"
								,"tell","capacity","reg_date","is_leave"};
			
			if(rset.next()) {
				member =  convertRowToMember(rset,fieldArr);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset,pstm);
		}
			
		  return member;
	}
	
	public int insertMember(Member member, Connection conn) {
		int res = 0;		
		PreparedStatement pstm = null;
		
		String query = "insert into member(user_id, password,user_name,user_nick,email,tell,capacity)"
					 + " values(?,?,?,?,?,?,?) ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getUserName());
			pstm.setString(4, member.getUserNick());
			pstm.setString(5, member.getEmail());
			pstm.setString(6, member.getTell());
			pstm.setString(7, member.getCapacity());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
		return res;
	}
	
	public Member selectMemberById(String userId, Connection conn) {
		Member member = null;			
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_Id = ?";
		
		try {			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}

	
	public Member selectMemberByNick(String userNick, Connection conn) {
		Member member = null;			
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_nick = ?";
		
		try {			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userNick);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}
	
	
	public void leaveId(String userId, Connection conn) {

		String sql = "update member set is_leave = 1 where user_id = ? ";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, userId);

			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}		
	}
	

	public void updateMember(Member member, Connection conn) {
		
		String sql = "update member set password = ?, user_nick = ?,"
				+ "tell = ?, capacity = ?  where user_id = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, member.getPassword());
			pstm.setString(2, member.getUserNick());
			pstm.setString(3, member.getTell());
			pstm.setString(4, member.getCapacity());
			pstm.setString(5, member.getUserId());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}		
		
	}
	
	public Member searchById(String userName, String email, Connection conn) {
		
		Member member = null;			
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_name = ? and email = ?";
		
		try {			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userName);
			pstm.setString(2, email);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}
	
	public Member searchByPass(String userId, String email, Connection conn) {
		
		Member member = null;			
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_id = ? and email = ?";
		
		try {			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, email);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = convertRowToMember(rset);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
	}
	
	public String changePass(String userId, String email, Connection conn) {
		PreparedStatement pstm = null;
		String randompass = getRamdomPassword(10);
		String query = "update member set password = ? where user_id = ? and email = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, randompass);
			pstm.setString(2, userId);
			pstm.setString(3, email);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
		return randompass;
	}
	
	public String getRamdomPassword(int size) { 
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'
									, 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x'
									, 'y', 'z', '!', '@', '#', '$', '%', '^', '&' }; 
		StringBuffer sb = new StringBuffer(); 
		SecureRandom sr = new SecureRandom(); 
		
		sr.setSeed(new Date().getTime()); 
		int idx = 0; int len = charSet.length; 
		for (int i=0; i<size; i++) 
		{ 
			idx = sr.nextInt(len);
			sb.append(charSet[idx]); 
		} 
		
		return sb.toString(); 
	}

//github.com/h0neyalpaca/futsal-on.git
	// 데이타베이스에서 받아온 resultset을 member에 담는 method
	private Member convertRowToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setUserId(rset.getString("user_id")); 
		member.setTmCode(rset.getString("tm_code")); 
		member.setPassword(rset.getString("password"));
		member.setUserName(rset.getString("user_name"));
		member.setGrade(rset.getString("grade"));
		member.setUserNick(rset.getString("user_nick"));
		member.setEmail(rset.getString("email"));
		member.setTell(rset.getString("tell"));
		member.setCapacity(rset.getString("capacity"));
		member.setRegDate(rset.getDate("reg_date"));
		member.setIsLeave(rset.getInt("is_leave"));
		return member;
	}
	
	private Member convertRowToMember(ResultSet rset, String[] fieldArr) throws SQLException {
		Member member = new Member();
		
		for (int i = 0; i < fieldArr.length; i++) {
			String field = fieldArr[i].toLowerCase();
			switch (field) {
			case "user_id":member.setUserId(rset.getString("user_id")); break;
			case "tm_Code":member.setTmCode(rset.getString("tm_code")); break;
			case "password":member.setPassword(rset.getString("password"));break;
			case "user_name":member.setUserName(rset.getString("user_name")); break;
			case "grade":member.setGrade(rset.getString("grade")); break;
			case "user_nick":member.setUserNick(rset.getString("user_nick"));break;
			case "email":member.setEmail(rset.getString("email"));break;
			case "tell":member.setTell(rset.getString("tell"));break;
			case "capacity":member.setCapacity(rset.getString("capacity"));break;
			case "reg_date":member.setRegDate(rset.getDate("reg_date"));break;
			case "is_leave":member.setIsLeave(rset.getInt("is_leave"));break;
			}
		}
		
		return member;
	}
	
	private Map<String,Object> convertRowToMap(ResultSet rset, String[] fieldArr) throws SQLException {
		
		Map<String,Object> res = new HashMap<String, Object>();
		
		for (int i = 0; i < fieldArr.length; i++) {
			String field = fieldArr[i].toLowerCase();
			res.put(field, rset.getObject(field));
		}
		
		return res;
	}


	

	

}
