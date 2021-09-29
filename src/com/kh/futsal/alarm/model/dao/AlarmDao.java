package com.kh.futsal.alarm.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.futsal.alarm.model.dto.Alarm;
import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.exception.DataAccessException;
import com.kh.futsal.matching.model.dto.MatchMaster;

public class AlarmDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	
	public AlarmDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void updateAlarm(String ntIdx, Connection conn) {
		
		String sql = "update notice set state = 1 where nt_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, ntIdx);
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
	}
	
	public void updateAlarmIsStart(String ntIdx, Connection conn) {
		
		String sql = "update notice set IS_START = 1 where nt_idx = ? ";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, ntIdx);
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(pstm);
		}
		
	}
	
	public void insertAlarm(MatchMaster matchMaster,String userId, Connection conn) {
		
		PreparedStatement pstm = null;
		String query = "insert into notice values(sc_nt_idx.nextval ,0,?,?,?,?,0,?) ";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, matchMaster.getMatchDate());
			pstm.setString(2, "신청하신 매치가 4시간 후에 시작됩니다");
			pstm.setString(3, userId);
			pstm.setString(4, matchMaster.getMatchTime());
			pstm.setString(5, matchMaster.getMmIdx());

			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}
	
	public List<Alarm> selectAlarmList (String userId, Connection conn){
		
		String sql = "select * from notice where user_id = ?";
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		List<Alarm> alarms = new ArrayList<Alarm>();
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Alarm alarm = new Alarm();
				alarm.setContent(rset.getString("content"));
				alarm.setNtDate(rset.getString("nt_date"));
				alarm.setIsStart(rset.getInt("is_start"));
				alarm.setMatchTime(rset.getString("match_time"));
				alarm.setMmIdx(rset.getString("mm_idx"));
				alarm.setNtIdx(rset.getString("nt_idx"));
				alarm.setState(rset.getInt("state"));
				alarm.setUserId(rset.getString("user_id"));
				alarms.add(alarm);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}finally {
			template.close(rset, pstm);
		}
		return alarms;
	}
	
	
	
}
