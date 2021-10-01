package com.kh.futsal.support.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.common.pagination.PageInfo;
import com.kh.futsal.support.model.dto.Support;

public class SupportDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	
	public void insertBoard(Support support, Connection conn) {
		// TODO Auto-generated method stub
		String sql = "insert into board "
				+ "(bd_idx,user_id,title,content,type) "
				+ "values(sc_bd_idx.nextval,?,?,?,?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, support.getUserId());
			pstm.setString(2, support.getTitle());
			pstm.setString(3, support.getContent());
			pstm.setInt(4, support.getType());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
	}
	
	public Support selectBoardDetail(String bdIdx, Connection conn) {
		
		String sql = "select * from board where bd_idx = ? ";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Support support = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,bdIdx);
			rset = pstm.executeQuery();

			if(rset.next()) {
				support = new Support();
				support.setBdIdx(rset.getString("bd_idx"));
				support.setUserId(rset.getString("user_id"));
				support.setRegDate(rset.getDate("reg_date"));
				support.setTitle(rset.getString("title"));
				support.setContent(rset.getString("content"));
				support.setType(rset.getInt("type"));
				support.setIsAnswer(rset.getInt("is_answer"));
				support.setAnswer(rset.getString("answer"));

			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return support;
	}
	
	
	public List<Support> selectSupportList (String userId, PageInfo page, Connection conn){
		
		String sql = "select *" + 
				 	" from (select rownum rnum, board.* " + 
				 	" from (select * from board" + 
				 	" order by bd_idx desc) board" + 
				 	" where user_id = ?)" + 
				 	" where rnum between ? and ?";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		List<Support> supports = new ArrayList<Support>();
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setInt(2, page.getStartNo());
			pstm.setInt(3, page.getEndNo());
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Support  support = new Support();
				support.setBdIdx(rset.getString("bd_idx"));
				support.setUserId(rset.getString("user_id"));
				support.setRegDate(rset.getDate("reg_date"));
				support.setTitle(rset.getString("title"));
				support.setContent(rset.getString("content"));
				support.setType(rset.getInt("type"));
				support.setIsAnswer(rset.getInt("is_answer"));
				support.setAnswer(rset.getString("answer"));
				supports.add(support);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return supports;
	}
	
	public int selectBoardCnt(Connection conn, String userId) {
			
			PreparedStatement pstm = null;
			ResultSet rset = null;
			int res = 0;
			
			String sql = "select count(*) from board where user_id = ?";
			
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, userId);
				rset=pstm.executeQuery();
				if(rset.next()) {
					res = rset.getInt(1);
				}
				
				
			}  catch (SQLException e) {
				throw new DataAccessException(e);
			}finally {
				template.close(rset, pstm);
			}
			
			return res;
		}

	public void updateBoard(String bdIdx, String content,Connection conn) {
		
		String sql = "update board set content = ? where bd_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, content);
			pstm.setString(2, bdIdx);
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
	}

	public void deleteBoard(String bdIdx, Connection conn) {

		String sql = "delete from board where bd_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, bdIdx);
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
	}

	public List<Support> selectAllSupportList(Connection conn) {
		String sql = "select * from board ";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		List<Support> supports = new ArrayList<Support>();
		
		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Support  support = new Support();
				support.setBdIdx(rset.getString("bd_idx"));
				support.setUserId(rset.getString("user_id"));
				support.setRegDate(rset.getDate("reg_date"));
				support.setTitle(rset.getString("title"));
				support.setContent(rset.getString("content"));
				support.setType(rset.getInt("type"));
				support.setIsAnswer(rset.getInt("is_answer"));
				support.setAnswer(rset.getString("answer"));
				supports.add(support);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return supports;
	}

	public void updateAnswer(String bdIdx, String answer, Connection conn) {
		
		String sql = "update board set answer = ? , is_answer = 1 where bd_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, answer);
			pstm.setString(2, bdIdx);
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
	}
	
}
