package com.bloomp.task.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.account.entity.Account;
import com.bloomp.account.service.AccountService;
import com.bloomp.task.dao.TaskDao;
import com.bloomp.task.dao.TaskOwnerDao;
import com.bloomp.task.entity.Task;
import com.bloomp.task.entity.Task.State;
import com.bloomp.task.entity.TaskOwner;
import com.bloomp.task.entity.TaskOwner.Rule;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private TaskOwnerDao taskOwnerDao;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public long save(Task task, long creator, long executor, List<Long> observers) {
		if(task == null || creator <= 0 || executor <= 0){
			return -1;
		}
		try {
			long id = taskDao.save(task);
			if(id > 0){
				List<TaskOwner> taskOwners = new ArrayList<TaskOwner>();
				TaskOwner cTask = new TaskOwner();
				cTask.setAccountId(creator);
				cTask.setCreateTime(task.getCreateTime());
				cTask.setRole(Rule.creaotr.getValue());
				cTask.setTaskId(id);
				cTask.setUpdateTime(task.getCreateTime());
				taskOwners.add(cTask);
				
				TaskOwner eTask = new TaskOwner();
				eTask.setAccountId(executor);
				eTask.setCreateTime(task.getCreateTime());
				eTask.setRole(Rule.exceutor.getValue());
				eTask.setTaskId(id);
				eTask.setUpdateTime(task.getCreateTime());
				taskOwners.add(eTask);
				taskOwnerDao.save(taskOwners);
				this.addObserver(id, observers, task.getCreateTime());
			}
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public Map<String, List<Task>> query(long accountId, long lastQueryTime) {
		Map<String, List<Task>> taskMap = new HashMap<String, List<Task>>();
		try {
			List<TaskOwner> taskOwners = taskOwnerDao.queryList(accountId, lastQueryTime);
			List<Task> myTasks = new ArrayList<Task>();
			List<Task> watchTasks = new ArrayList<Task>();
			List<Task> histriesTasks = new ArrayList<Task>();
			if(taskOwners.size() > 0){
				List<Long> ids = new ArrayList<Long>();
				for(TaskOwner taskOwner : taskOwners){
					ids.add(taskOwner.getTaskId());
				}
				
				
				List<Task> tasks = taskDao.queryList(ids, lastQueryTime);
				
				List<TaskOwner> tTaskOwners = taskOwnerDao.queryListForTaskIds(ids, 0);
				Map<Long, Long> creatorMap = new HashMap<Long, Long>();
				Map<Long, Long> exceutorMap = new HashMap<Long, Long>();
				Map<Long, List<String>> observersMap = new HashMap<Long, List<String>>();
				
				List<Long> accountIds = new ArrayList<Long>();
				List<String> observersList = null;
				for(TaskOwner taskOwner : tTaskOwners){
					accountIds.add(taskOwner.getAccountId());
					if(taskOwner.getRole() == Rule.exceutor.getValue()){
						exceutorMap.put(taskOwner.getTaskId(), taskOwner.getAccountId());
					}else if(taskOwner.getRole() == Rule.creaotr.getValue()){
						creatorMap.put(taskOwner.getTaskId(), taskOwner.getAccountId());
					}else if(taskOwner.getRole() == Rule.observer.getValue()){
						if(observersMap.get(taskOwner.getTaskId()) == null){
							observersList = new ArrayList<String>(); 
						}
						observersList.add(taskOwner.getAccountId()+"");
						observersMap.put(taskOwner.getTaskId(), observersList);
					}
				}
				
				List<Account> accounts = accountService.queryForIds(accountIds);
				Map<Long, Account> accountMap = new HashMap<Long, Account>();
				for(Account account : accounts){
					accountMap.put(account.getId(), account);
				}
				
				long time = System.currentTimeMillis();
				List<Long> histriesTaskIds = new ArrayList<Long>();
				for(Task task : tasks){
					List<String> tObservers = observersMap.get(task.getId());
					if(tObservers != null && tObservers.size() > 0 ){
						task.setObservers(StringUtils.join(tObservers.toArray(new String[tObservers.size()]), ","));
					}
					task.setCreator(creatorMap.get(task.getId()) == null ? 0 : creatorMap.get(task.getId()));
					task.setExceutor(exceutorMap.get(task.getId()) == null ? 0 : exceutorMap.get(task.getId()));
					if(task.getState() >= 2){
						histriesTasks.add(task);
						task.setAccount(accountMap.get(task.getExceutor()));
					}else if(task.getExpireTime() <= time){
						task.setState(State.expire.getValue());
						histriesTasks.add(task);
						histriesTaskIds.add(task.getId());
						task.setAccount(accountMap.get(task.getExceutor()));
					}else if(accountId == task.getExceutor()){
						task.setAccount(accountMap.get(task.getCreator()));
						myTasks.add(task);
					}else{
						task.setAccount(accountMap.get(task.getExceutor()));
						watchTasks.add(task);
					}
				}
				if(histriesTaskIds.size() > 0){
					taskDao.update(histriesTaskIds, State.expire.getValue());
				}
			}
			taskMap.put("myTasks", myTasks);
			taskMap.put("histriesTasks", histriesTasks);
			taskMap.put("watchTasks", watchTasks);
			return taskMap;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskMap;
	}

	@Override
	public long addObserver(long id, List<Long> observers, long time) {
		List<TaskOwner> taskOwners = new ArrayList<TaskOwner>();
		if(time == 0)
			time = System.currentTimeMillis(); 
		for(long observer : observers){
			TaskOwner oTask = new TaskOwner();
			oTask.setAccountId(observer);
			oTask.setCreateTime(time);
			oTask.setRole(Rule.observer.getValue());
			oTask.setTaskId(id);
			oTask.setUpdateTime(time);
			taskOwners.add(oTask);
		}
		try {
			return taskOwnerDao.save(taskOwners);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public Task query(long id) {
		try {
			return taskDao.query(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Task();
	}

	@Override
	public List<Account> queryTaskMembers(long id, long lastQueryTime) {
		try {
			List<TaskOwner> taskOwners = taskOwnerDao.queryList(id, lastQueryTime);
			if(taskOwners != null && taskOwners.size() > 0){
				List<Long> accountIds = new ArrayList<Long>();
				for(TaskOwner taskOwner : taskOwners){
					accountIds.add(taskOwner.getAccountId());
				}
				return accountService.queryForIds(accountIds);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Account>();
	}
	
	@Override
	public List<Account> queryTaskMembers(List<Long> taskIds, long lastQueryTime) {
		try {
			List<TaskOwner> taskOwners = taskOwnerDao.queryListForTaskIds(taskIds, lastQueryTime);
			if(taskOwners != null && taskOwners.size() > 0){
				List<Long> accountIds = new ArrayList<Long>();
				for(TaskOwner taskOwner : taskOwners){
					accountIds.add(taskOwner.getAccountId());
				}
				return accountService.queryForIds(accountIds);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Account>();
	}

	@Override
	public int update(long taskId, long accountId, int state) {
		List<Long> taskIds = new ArrayList<Long>();
		taskIds.add(taskId);
		try {
			return taskDao.update(taskIds, state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
