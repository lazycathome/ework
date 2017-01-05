package com.bloomp.task.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet rs, int i) throws SQLException {
		Task task = new Task();
		task.setClock(rs.getLong("clock"));
		task.setDescription(rs.getString("description"));
		task.setExpireTime(rs.getLong("expireTime"));
		task.setState(rs.getInt("state"));
		task.setSubject(rs.getString("subject"));
		task.setCreateTime(rs.getLong("createTime"));
		task.setId(rs.getLong("id"));
		task.setCreator(rs.getLong("creator"));
		task.setUpdateTime(rs.getLong("updatetime"));
		task.setCategoryId(rs.getLong("categoryId"));
		return task;
	}	
}
