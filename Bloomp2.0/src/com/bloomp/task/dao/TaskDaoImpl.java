package com.bloomp.task.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.core.JDBCUtils;
import com.bloomp.task.entity.Task;
import com.bloomp.task.entity.TaskRowMapper;

@Repository
public class TaskDaoImpl implements TaskDao {

	@Autowired
	private JDBCUtils<Task> jdbc;
	
	@Override
	public long save(Task task) throws SQLException{
		String sql = "INSERT INTO TASKS(CREATOR, SUBJECT, DESCRIPTION, LOGO, EXPIRETIME, CLOCK, CREATETIME, UPDATETIME, categoryId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object> list = new ArrayList<Object>();
		list.add(task.getCreator());
		list.add(task.getSubject());
		list.add(task.getDescription());
		list.add(task.getLogo());
		list.add(task.getExpireTime());
		list.add(task.getClock());
		list.add(task.getCreateTime());
		list.add(task.getUpdateTime());
		list.add(task.getCategoryId());
		jdbc.save(sql, list);
		long id = jdbc.queryForLong(Constants.LAST_INSERT_ID);
		return id;
	}

	@Override
	public Task query(long id) throws SQLException {
		String sql = "SELECT * FROM TASKS WHERE ID=?";
		List<Task> tasks = jdbc.executeSql(sql, new Object[]{id}, new TaskRowMapper());
		if(tasks != null && tasks.size() > 0 ){
			return tasks.get(0);
		}
		return new Task();
	}

	@Override
	public List<Task> queryList(List<Long> ids, long lastQueryTime) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM TASKS WHERE ID in (");
		for(long id : ids){
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") and UPDATETIME >=? or (expireTime<=? and state=1) order by updateTime desc");
		ids.add(lastQueryTime);
		ids.add(lastQueryTime);
		List<Task> tasks = jdbc.executeSql(sql.toString(), ids.toArray(), new TaskRowMapper());
		return tasks;
	}

	@Override
	public int update(List<Long> taskIds, int state) throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE TASKS SET STATE = ?, UPDATETIME=? WHERE ID in (");
		List<Object> list = new ArrayList<Object>();
		list.add(state);
		list.add(System.currentTimeMillis());
		for(long id : taskIds){
			sql.append("?,");
			list.add(id);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		jdbc.update(sql.toString(), list);
		return 1;
	}

}
