package com.bloomp.api.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.BodyParam;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.PUT;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.chat.entity.ChatMessage;
import com.bloomp.chat.service.TaskChatService;

@Path("/api/bloomp/chats/?$")
public class ChatMessageManager {

	@Autowired
	private TaskChatService taskChatService;
	
	@GET
	public ApiResult get(@QueryParam("taskId") String sTaskId, @QueryParam("accountId") String sAccountId, @QueryParam("lastQueryTime") String sLastQueryTime){
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sTaskId) || StringUtils.isBlank(sAccountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long taskId = Long.parseLong(sTaskId);
		long accountId = Long.parseLong(sAccountId);
		long lastQueryTime = StringUtils.isBlank(sLastQueryTime) ? 0 :Long.parseLong(sLastQueryTime);
		
		List<ChatMessage> chatMessages = taskChatService.queryTaskChatMessage(taskId, accountId, lastQueryTime);
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("lastQueryTime", System.currentTimeMillis());
		result.put("results", chatMessages);
		return new JsonResult(result);
	}
	
	@PUT
	public ApiResult create(@BodyParam("taskId") String sTaskId, @BodyParam("accountId") String sAccountId, 
			@BodyParam("type") String sType, @BodyParam("content") String content){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sTaskId) || StringUtils.isBlank(sAccountId)
				|| StringUtils.isBlank(content) || StringUtils.isBlank(sType)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long taskId = Long.parseLong(sTaskId);
		long accountId = Long.parseLong(sAccountId);
		int type = Integer.parseInt(sType);
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setContent(content);
		chatMessage.setCreator(accountId);
		chatMessage.setType(type);
		long chatMessageId = taskChatService.sendMessage(chatMessage, taskId);
		if(chatMessageId > 0 ){
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			result.put("id", chatMessageId);
		}else{
			result.put("code", Code.ERROR);
			result.put("message", Code.ERROR_MESSAGE);
		}
		
		return new JsonResult(result);
	}
}
