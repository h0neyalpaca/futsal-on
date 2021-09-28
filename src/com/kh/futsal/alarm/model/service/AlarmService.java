package com.kh.futsal.alarm.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.futsal.alarm.model.dao.AlarmDao;
import com.kh.futsal.alarm.model.dto.Alarm;
import com.kh.futsal.common.db.JDBCTemplate;

public class AlarmService {

	private JDBCTemplate template = JDBCTemplate.getInstance();
	private AlarmDao alarmDao = new AlarmDao();
	

	public List<Alarm> selectNoticetList(String userId) {
		
		 Connection conn = template.getConnection();
		 List<Alarm> alarms = null;

		try {
			alarms = alarmDao.selectSupportList(userId, conn);
		} finally {
			template.close(conn);
		}
		return alarms;
	}
	
}