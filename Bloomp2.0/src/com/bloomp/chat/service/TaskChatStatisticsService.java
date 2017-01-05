package com.bloomp.chat.service;

import java.util.List;

import com.bloomp.chat.entity.ChatStatistics;

public interface TaskChatStatisticsService {

	List<ChatStatistics> query(long accountId);
	
	long queryCountForAccountId(long accountId);
	
	int clear(long taskId, long accountId);
}
