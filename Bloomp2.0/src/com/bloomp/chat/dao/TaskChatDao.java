package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.chat.entity.TaskChat;

public interface TaskChatDao {

	long save(List<TaskChat> taskChats) throws SQLException;
	
	TaskChat query(long taskId, long accountId) throws SQLException;
	
	List<TaskChat> query(long taskId, List<Long> accountIds) throws SQLException;
	
	List<TaskChat> query(long accountId) throws SQLException;
}
