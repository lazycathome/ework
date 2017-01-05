package com.bloomp.task.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.core.JDBCUtils;
import com.bloomp.task.entity.TaskOwner;
import com.bloomp.task.entity.TaskOwnerRowMapper;

@Repository
public class TaskOwnerDaoImpl implements TaskOwnerDao {

	@Autowired
	private JDBCUtils<TaskOwner> jdbc;
	
	@Override
	public long save(List<TaskOwner> taskOwners) throws SQLException {
		String sql = "INSERT INTO TASKOWNER(ACCOUNTID, ROLE, TASKID, CREATETIME, UPDATETIME, state) VALUES(?, ?, ?, ?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>();
		for(TaskOwner taskOwner : taskOwners){
			List<Object> list = new ArrayList<Object>();
			list.add(taskOwner.getAccountId());
			list.add(taskOwner.getRole());
			list.add(taskOwner.getTaskId());
			list.add(taskOwner.getCreateTime());
			list.add(taskOwner.getUpdateTime());
			list.add(taskOwner.getState());
			vList.add(list);
		}
		jdbc.batchSave(sql, vList);
		List<Long> result = jdbc.queryForList(Constants.LAST_INSERT_ID, Long.class);
//		jdbc.queryForLong(Constants.LAST_INSERT_ID);
		
		return result.get(0);
	}

	@Override
	public List<TaskOwner> queryList(long accountId, long lastQueryTime) throws SQLException {
		String sql = "SELECT * FROM TASKOWNER WHERE ACCOUNTID=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(accountId);
//		list.add(lastQueryTime);
		List<TaskOwner> taskOwners = jdbc.executeSql(sql, list.toArray(), new TaskOwnerRowMapper());
		if(taskOwners != null && taskOwners.size() > 0){
			return taskOwners;
		}
		return new ArrayList<TaskOwner>(1);
	}
	
	@Override
	public List<TaskOwner> queryListForTaskId(long taskId, long lastQueryTime) throws SQLException {
		String sql = "SELECT * FROM TASKOWNER WHERE taskId=? AND UPDATETIME>=?";
		List<Object> list = new ArrayList<Object>();
		list.add(taskId);
		list.add(lastQueryTime);
		List<TaskOwner> taskOwners = jdbc.executeSql(sql, list.toArray(), new TaskOwnerRowMapper());
		if(taskOwners != null && taskOwners.size() > 0){
			return taskOwners;
		}
		return new ArrayList<TaskOwner>(1);
	}

	@Override
	public List<TaskOwner> queryListForTaskIds(List<Long> taskIds,
			long lastQueryTime) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM TASKOWNER WHERE taskId in(");
		List<Object> list = new ArrayList<Object>();
//		list.add(lastQueryTime);
		for(long taskId : taskIds){
			sql.append("?,");
			list.add(taskId);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		List<TaskOwner> taskOwners = jdbc.executeSql(sql.toString(), list.toArray(), new TaskOwnerRowMapper());
		if(taskOwners != null && taskOwners.size() > 0){
			return taskOwners;
		}
		return new ArrayList<TaskOwner>(1);
	}

	@Override
	public List<TaskOwner> queryCreatorAndExceutorListForTaskIds(List<Long> taskIds) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM TASKOWNER WHERE role<=? and taskId in(");
		List<Object> list = new ArrayList<Object>();
		list.add(3);
		for(long taskId : taskIds){
			sql.append("?,");
			list.add(taskId);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		List<TaskOwner> taskOwners = jdbc.executeSql(sql.toString(), list.toArray(), new TaskOwnerRowMapper());
		if(taskOwners != null && taskOwners.size() > 0){
			return taskOwners;
		}
		return new ArrayList<TaskOwner>(1);
	}
}
