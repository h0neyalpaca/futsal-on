package com.kh.futsal.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.notice.model.dto.Notice;

public class NoticeDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	
	public int insertNotice(Notice notice, Connection conn) { //공지사항 작성
		
		//제목, 날짜, 조회수, 내용
		
		int res = 0;
		PreparedStatement pstm = null;
		
		String sql = "insert into news "
				+ "(nw_idx, nw_title, nw_content, views) "
				+ "values (sc_nw_idx.nextval, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, notice.getNwTitle());
			pstm.setString(2, notice.getNwContent());
			pstm.setInt(3, 0);
			
			res = pstm.executeUpdate();
	
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(conn);
		}
		
		return res;
	}
	
	public void noticeViewCnt(String nwIdx, Connection conn) { //조회수 증가 메서드
		

	
		PreparedStatement pstm = null;
		String sql = "update news" + 
					" set views = views + 1 " + 
					" where nw_idx = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nwIdx);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
		
	}
	
	
	public Notice selectBoardDetail(String nwIdx, Connection conn){ //공지사항 게시글
		
		Notice notice = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "select NW_IDX, NW_TITLE, NW_CONTENT, NW_MAIN, REG_DATE, IS_DEL, VIEWS from news where nw_idx =?";
				
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,nwIdx);
			rset = pstm.executeQuery();
	
			if(rset.next()) {
				notice = convertRowToNotice(rset);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return notice;
		
	}
	

	
	public List<Notice> selectMainNoticeList(Connection conn){
		
		List<Notice> mainNoticeList = new ArrayList<Notice>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		
		String sql = "select NW_IDX, NW_TITLE, NW_CONTENT, NW_MAIN, REG_DATE, IS_DEL, VIEWS" + 
					"  from news" + 
					"  where NW_MAIN = '0' and is_del ='0'";


		try {
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				mainNoticeList.add(convertRowToNotice(rset));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}

		return mainNoticeList;
		
	}
	
	public List<Notice> selectNoticeList(Connection conn, int startNo, int endNo){
		
		List<Notice> noticeList = new ArrayList<Notice>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		
		String sql = "select NW_IDX, NW_TITLE, NW_CONTENT, NW_MAIN, REG_DATE, IS_DEL, VIEWS" + 
				"  from(" + 
				"  select rownum rnum, news.*" + 
				"  from(" + 
				"  select * from news" + 
				"  order by NW_IDX desc) news)" + 
				"  where rnum BETWEEN ? and ? and is_del ='0'";


		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, startNo); 
			pstm.setInt(2, endNo); 
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				noticeList.add(convertRowToNotice(rset));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}

		return noticeList;
		
	}
	
	public int selectNoticeCnt(Connection conn) {
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		int res = 0;
		
		String sql = "select count(*) from news";
		
		try {
			pstm = conn.prepareStatement(sql);
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
	
	//검색해서 나오는 리스트
	public List<Notice> selectSearchList(Connection conn, int startNo, int endNo, String searchContent) {
		
		List<Notice> noticeList = new ArrayList<Notice>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		
		String sql = "select NW_IDX, NW_TITLE, NW_CONTENT, NW_MAIN, REG_DATE, IS_DEL, VIEWS" + 
				"  from(" + 
				"  select rownum rnum, news.*" + 
				"  from(" + 
				"  select * from news" + 
				"  where NW_TITLE like '%'||?||'%'" + 
				"  order by NW_IDX desc) news)" + 
				"  where rnum BETWEEN ? and ? and is_del ='0'";


		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, searchContent); 
			pstm.setInt(2, startNo); 
			pstm.setInt(3, endNo); 
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				noticeList.add(convertRowToNotice(rset));
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		
		return noticeList;
		
	}

	public int selectSearchCnt(String searchContent, Connection conn) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		int res = 0;
		
		String sql = "select count(*) from news" + 
					 " where NW_TITLE like '%'||?||'%'";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, searchContent);
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

	
	private Notice convertRowToNotice(ResultSet rset) throws SQLException {
		Notice notice = new Notice();
		notice.setNwIdx(rset.getString("nw_idx"));
		notice.setNwTitle(rset.getString("nw_title"));
		notice.setNwContent(rset.getString("nw_content"));
		notice.setNwMain(rset.getInt("nw_main"));
		notice.setIsDel(rset.getInt("is_del"));
		notice.setRegDate(rset.getDate("reg_date"));
		notice.setViews(rset.getInt("views"));
		
		return notice;
	}
	
	private Notice convertRowToNotice(String[] columns, ResultSet rset) throws SQLException {
		Notice notice = new Notice();
		
		for(int i = 0; i < columns.length; i++) {
			String column = columns[i].toLowerCase();
			column = column.trim();
			switch (column) {
			case "NW_IDX": notice.setNwIdx(rset.getString("NW_IDX")); break;
			case "NW_TITLE": notice.setNwTitle(rset.getString("NW_TITLE")); break;
			case "NW_CONTENT": notice.setNwContent(rset.getString("NW_CONTENT")); break;
			case "REG_DATE": notice.setRegDate(rset.getDate("REG_DATE")); break;
			case "IS_DEL": notice.setIsDel(rset.getInt("IS_DEL")); break;
			case "NW_MAIN": notice.setNwMain(rset.getInt("NW_MAIN")); break;
			case "VIEWS": notice.setViews(rset.getInt("VIEWS")); break;
			default:
				break;
			}
		}
	
		return notice;
	}

	
}
