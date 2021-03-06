package com.kh.futsal.alarm.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.futsal.alarm.model.dao.AlarmDao;
import com.kh.futsal.alarm.model.dto.Alarm;
import com.kh.futsal.common.db.JDBCTemplate;
import com.kh.futsal.common.pagination.PageInfo;
import com.kh.futsal.matching.model.dto.MatchMaster;

public class AlarmService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private AlarmDao alarmDao = new AlarmDao();
	
	//알람 리스트 가져오기
	public List<Alarm> selectNoticetList(String userId) {
			
			Connection conn = template.getConnection();
			List<Alarm> alarms = null;
			try {
				alarms = alarmDao.selectAlarmList(userId, conn);
			} finally {
				template.close(conn);
			}
			return alarms;
		}
	
	//페이징용 알람 리스트
	public List<Alarm> selectAlarmListPage(String userId,  PageInfo page) {
		
		Connection conn = template.getConnection();
		List<Alarm> alarms = null;
		try {
			alarms = alarmDao.selectAlarmListPage(userId, page, conn);
		} finally {
			template.close(conn);
		}
		return alarms;
	}
	
	//매치 신청시 알람 데이터 추가용(팀 매치 신청이면 userId에 팀장 아이디 )
	//매치글 작성시도 알람 추가하여 팀장에게 알려주기
	public void insertAlarm(MatchMaster matchMaster,String userId) {
		
		Connection conn = template.getConnection();
		try {
			alarmDao.insertAlarm(matchMaster,userId,conn);
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	//매치글 삭제시 알람 삭제용 
	public void deleteAlarm(String mmIdx) {
		
		Connection conn = template.getConnection();
		try {
			alarmDao.deleteAlarm(mmIdx,conn);
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	//매치 취소시 알람 삭제용
	public void deleteAlarm(String userId,String mmIdx) {
		
		Connection conn = template.getConnection();
		try {
			alarmDao.deleteAlarm(userId,mmIdx,conn);
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	//알람을 읽으면 상태 업데이트
	public void updateAlarm(String ntIdx) {
		
		Connection conn = template.getConnection();
		try {
			alarmDao.updateAlarm(ntIdx,conn);
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	//매치시작이 4시간 남았을 때 알람을 띄워주기 위한 기능
	public void updateAlarmIsStart(String ntIdx,Alarm alarm) {
		
		Connection conn = template.getConnection();
		try {
			int res = alarmDao.updateAlarmIsStart(ntIdx,conn);
			if(res == 1) {
				alarmDao.insertAlarmIsEnd(alarm,conn);
			}
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}
	
	public void updateAlarmIsEnd(MatchMaster match,String userId) {
		
		Connection conn = template.getConnection();
		try {
			alarmDao.updateAlarmIsEnd(match,userId,conn);
			
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}

	public int selectBoardCnt(String userId) {
		int res = 0;
		Connection conn = template.getConnection();
		
		try {
			res = alarmDao.selectBoardCnt(conn, userId);
		} finally {
			template.close(conn);
		}
		
		return res;
	}

	public void regAlarm(MatchMaster matchMaster, String userId) {
		Connection conn = template.getConnection();
		try {
			alarmDao.regAlarm(matchMaster,userId,conn);
			template.commit(conn);
		}finally {
			template.close(conn);
		}
	}

	

}
