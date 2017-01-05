package com.bloomp.api.chat;

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
import com.bloomp.chat.entity.ChatStatistics;
import com.bloomp.chat.service.TaskChatStatisticsService;

@Path("/api/bloomp/chats/red-point/?$")
public class ChatStatisticsManager {

	@Autowired
	private TaskChatStatisticsService taskChatStatisticsService;
	
	@GET
	public ApiResult get(@QueryParam("accountId") String sAccountId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sAccountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long accountId = Long.parseLong(sAccountId);
		List<ChatStatistics> chatStatisticss = taskChatStatisticsService.query(accountId);
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("results", chatStatisticss);
		return new JsonResult(result);
	}
}
