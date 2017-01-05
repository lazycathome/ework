package com.bloomp.task.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.task.entity.Task;

public interface TaskDao {

	long save(Task task) throws SQLException;
	
	Task query(long id) throws SQLException;

	List<Task> queryList(List<Long> ids, long lastQueryTime) throws SQLException;
	
	int update(List<Long> taskIds, int state) throws SQLException;
}
