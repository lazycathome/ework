package com.bloomp.api.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.task.entity.Task;
import com.bloomp.task.service.TaskService;

@Path("/api/bloomp/tasks/?$")
public class TasksManager {

	@Autowired
	private TaskService taskService;
	
	@GET
	public ApiResult get(@QueryParam("accountId") String sAccountId, @QueryParam("lastQueryTime") String sLastQueryTime){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sAccountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long accountId = Long.parseLong(sAccountId);
		long lastQueryTime = StringUtils.isBlank(sLastQueryTime) ? 0l : Long.parseLong(sLastQueryTime);
		long time = System.currentTimeMillis();
		Map<String, List<Task>> taskMap = taskService.query(accountId, lastQueryTime);
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("results", taskMap);
		result.put("lastQueryTime", time);
		return new JsonResult(result);
	}
}
