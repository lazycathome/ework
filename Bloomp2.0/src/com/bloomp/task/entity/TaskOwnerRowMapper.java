package com.bloomp.task.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskOwnerRowMapper implements RowMapper<TaskOwner> {

	@Override
	public TaskOwner mapRow(ResultSet rs, int i) throws SQLException {
		TaskOwner taskOwner = new TaskOwner();
		taskOwner.setAccountId(rs.getLong("accountId"));
		taskOwner.setCreateTime(rs.getLong("createTime"));
		taskOwner.setTaskId(rs.getLong("taskId"));
		taskOwner.setRole(rs.getInt("role"));
		taskOwner.setUpdateTime(rs.getLong("updateTime"));
		taskOwner.setId(rs.getLong("id"));
		taskOwner.setState(rs.getInt("state"));
		return taskOwner;
	}

	
}
