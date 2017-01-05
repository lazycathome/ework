package com.bloomp.api.task;

import java.util.ArrayList;
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
import com.bloomp.account.entity.Account;
import com.bloomp.task.service.TaskService;

@Path("/api/bloomp/tasks-members/?$")
public class TaskMemberManager {

	@Autowired
	private TaskService taskServcie;
	
	@GET
	public ApiResult get(@QueryParam("ids") String sIds, @QueryParam("lastQueryTime") String sLastQueryTime){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sIds)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long lastQueryTime = StringUtils.isBlank(sLastQueryTime) ? 0 : Long.parseLong(sLastQueryTime);
		String[] tIds = sIds.split(",");
		List<Long> taskIds = new ArrayList<Long>();
		for(String tId : tIds){
			taskIds.add(Long.parseLong(tId));
		}
		List<Account> accounts =  taskServcie.queryTaskMembers(taskIds, lastQueryTime);
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("results", accounts);
		return new JsonResult(result);
	}
	
	
}
