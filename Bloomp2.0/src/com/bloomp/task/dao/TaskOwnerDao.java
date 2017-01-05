package com.bloomp.task.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.task.entity.TaskOwner;

public interface TaskOwnerDao {

	long save(List<TaskOwner> taskOwners) throws SQLException;
	
	List<TaskOwner> queryList(long accountId, long lastQueryTime) throws SQLException;
	
	List<TaskOwner> queryListForTaskId(long taskId, long lastQueryTime) throws SQLException;
	
	List<TaskOwner> queryListForTaskIds(List<Long> taskId, long lastQueryTime) throws SQLException;
	
	List<TaskOwner> queryCreatorAndExceutorListForTaskIds(List<Long> taskId) throws SQLException;
	
	/**
	 * 支持任务接收者，拒绝和接受、申请任务；支持任务发布者，拒绝和接受
	 * @param id
	 * @param exceutor
	 * @param state
	 * @return
	 */
	int update(long id, long exceutor, int state);
}
