package com.bloomp.task.service;

import java.util.List;
import java.util.Map;

import com.bloomp.account.entity.Account;
import com.bloomp.task.entity.Task;

public interface TaskService {
	
	long save(Task task, long creator, long excetor, List<Long> observers);
	
	Map<String, List<Task>> query(long accountId, long lastQueryTime);
	
	Task query(long id);
	
	int update(long taskId, long accountId, int state);
	
	long addObserver(long id, List<Long> observers, long time);
	
	List<Account> queryTaskMembers(long id, long lastQueryTime);
	
	List<Account> queryTaskMembers(List<Long> taskIds, long lastQueryTime);
}
