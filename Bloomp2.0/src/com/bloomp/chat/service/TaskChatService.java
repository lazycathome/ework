package com.bloomp.chat.service;

import java.util.List;

import com.bloomp.chat.entity.ChatMessage;

public interface TaskChatService {

	List<ChatMessage> queryTaskChatMessage(long taskId, long accountId, long lastQueryTime);
	
	long sendMessage(ChatMessage chatMessage, long taskId);
	
}
