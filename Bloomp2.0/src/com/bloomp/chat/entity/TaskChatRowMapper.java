package com.bloomp.chat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskChatRowMapper implements RowMapper<TaskChat> {

	@Override
	public TaskChat mapRow(ResultSet rs, int i) throws SQLException {
		TaskChat taskChat = new TaskChat();
		taskChat.setAccountId(rs.getLong("accountId"));
		taskChat.setCreateTime(rs.getLong("createTime"));
		taskChat.setTaskId(rs.getLong("taskId"));
		taskChat.setId(rs.getLong("id"));
		return taskChat;
	}

	
}
