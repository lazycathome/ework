package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.chat.entity.ChatStatistics;

public interface ChatStatisticsDao {

	int save(List<ChatStatistics> chatStatisticss) throws SQLException;
	
	int update(List<Long> chatId, int num) throws SQLException;
	
	long queryCount(List<Long> chatIds) throws SQLException;
	
	List<ChatStatistics> query(List<Long> chatIds) throws SQLException;
}
