package com.bloomp.api.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.BodyParam;
import cn.jedisoft.framework.annotations.FormParam;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.PUT;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.task.entity.Task;
import com.bloomp.task.service.TaskService;

@Path("/api/bloomp/task/?$")
public class TaskManager {

	@Autowired
	private TaskService taskService;
	
	@PUT
	public ApiResult create(@BodyParam("logo") String logo, @BodyParam("subject") String subject,
			@BodyParam("description") String description, @BodyParam("creator") String sCreator,
			@BodyParam("executor") String sExecutor, @BodyParam("expireTime") String sExpireTime,
			@BodyParam("sClock") String sClock, @BodyParam("urgency") String sUrgency,
			@BodyParam("observers") String sObservers){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(subject) || StringUtils.isBlank(description) 
				|| StringUtils.isBlank(sCreator) || StringUtils.isBlank(sExecutor)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long time = System.currentTimeMillis();
		long creator = Long.parseLong(sCreator);
		long excetor = Long.parseLong(sExecutor);
		List<Long> observers = new ArrayList<Long>();
		if(StringUtils.isNotBlank(sObservers)){
			String[] tObservers = sObservers.split(",");
			if(tObservers != null && tObservers.length > 0){
				for(String tObServer : tObservers){
					if(StringUtils.isNotBlank(tObServer)){
						observers.add(Long.parseLong(tObServer));
					}
				}
			}
		}
		long clock = StringUtils.isBlank(sClock) ? 0 : time+Long.parseLong(sClock);
		long expireTime = StringUtils.isBlank(sExpireTime) ? 0 : time+Long.parseLong(sExpireTime);
		
		Task task = new Task();
		task.setClock(clock);
		task.setLogo(logo);
		task.setDescription(description);
		task.setSubject(subject);
		task.setExpireTime(expireTime);
		task.setCreateTime(time);
		task.setUpdateTime(time);
		
		long id = taskService.save(task, creator, excetor, observers);
		if(id > 0){
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			result.put("id", id);
			result.put("createTime", time);
			result.put("expireTime", expireTime);
		}else{
			result.put("code", Code.ERROR);
			result.put("message", Code.ERROR_MESSAGE);
		}
		return new JsonResult(result);
	}
	
	@POST
	public ApiResult updateState(@FormParam("taskId") String sTaskId, @FormParam("state") String sState, @FormParam("accountId") String sAccountId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sTaskId) || StringUtils.isBlank(sState) 
				|| StringUtils.isBlank(sAccountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}else{
			long taskId = Long.parseLong(sTaskId);
			int state = Integer.parseInt(sState);
			long accountId = Long.parseLong(sAccountId);
			int status = taskService.update(taskId, accountId, state);
			result.put("code", status > 0 ? Code.SUCCESS : Code.ERROR);			
		}
		return new JsonResult(result);
	}
	
	
	public ApiResult addObservers(@FormParam("id") String sId, @FormParam("excetor") String sExcetor, @FormParam("observers") String sObservers){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sId) || StringUtils.isBlank(sExcetor) 
				|| StringUtils.isBlank(sObservers)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long excetor = Long.parseLong(sExcetor);
		long id = Long.parseLong(sId);
		String[] tObservers = sObservers.split(",");
		List<Long> observers = new ArrayList<Long>();
		long time = System.currentTimeMillis();
		for(String tObServer : tObservers){
			observers.add(Long.parseLong(tObServer));
		}
		id = taskService.addObserver(id, observers, time);
		if(id > 0){
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			result.put("id", id);
			result.put("createTime", time);
		}else{
			result.put("code", Code.ERROR);
			result.put("message", Code.ERROR_MESSAGE);
		}
		return new JsonResult(result);
	}
	
	@GET
	public ApiResult get(@QueryParam("id") String sId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long id = Long.parseLong(sId);
		Task task = taskService.query(id);
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("result", task);
		return new JsonResult(result);
	}
}
