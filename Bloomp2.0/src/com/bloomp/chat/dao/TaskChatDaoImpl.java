package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.chat.entity.TaskChat;
import com.bloomp.chat.entity.TaskChatRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class TaskChatDaoImpl implements TaskChatDao{

	@Autowired
	private JDBCUtils<TaskChat> jdbc;
	
	@Override
	public long save(List<TaskChat> taskChats) throws SQLException {
		String sql = "insert into taskChat(taskId, accountId, createTime) values(?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>();
		for(TaskChat taskChat : taskChats){
			List<Object> list = new ArrayList<Object>();
			list.add(taskChat.getTaskId());
			list.add(taskChat.getAccountId());
			list.add(taskChat.getCreateTime());
			vList.add(list);
		}
		jdbc.batchSave(sql, vList);
		return jdbc.queryForLong(Constants.LAST_INSERT_ID);
	}

	@Override
	public List<TaskChat> query(long taskId, List<Long> accountIds)
			throws SQLException {
		StringBuilder sql = new StringBuilder("select * from TaskChat where taskId=? and accountId in(");
		List<Object> vList = new ArrayList<Object>();
		vList.add(taskId);
		for(Long id : accountIds){
			sql.append("?,");
			vList.add(id);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		
		List<TaskChat> taskChats = jdbc.executeSql(sql.toString(), vList.toArray(), new TaskChatRowMapper());
		if(taskChats != null && taskChats.size() > 0)
			return taskChats;
		return new ArrayList<TaskChat>();
	}

	@Override
	public List<TaskChat> query(long accountId) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from TaskChat where accountId=? ");
	
		List<TaskChat> taskChats = jdbc.executeSql(sql.toString(), new Object[]{accountId}, new TaskChatRowMapper());
		if(taskChats != null && taskChats.size() > 0)
			return taskChats;
		return new ArrayList<TaskChat>();
	}

	@Override
	public TaskChat query(long taskId, long accountId) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from TaskChat where taskId=? and accountId=? ");
		
		List<TaskChat> taskChats = jdbc.executeSql(sql.toString(), new Object[]{taskId, accountId}, new TaskChatRowMapper());
		if(taskChats != null && taskChats.size() > 0)
			return taskChats.get(0);
		return new TaskChat();
	}

}
