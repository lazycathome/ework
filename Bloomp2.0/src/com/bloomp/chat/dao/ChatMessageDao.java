package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.chat.entity.ChatMessage;

public interface ChatMessageDao {

	long save(ChatMessage chatMessages) throws SQLException;
	
	List<ChatMessage> queryList(List<Long> ids) throws SQLException;
	
}
