package com.bloomp.chat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChatMessageRowMapper implements RowMapper<ChatMessage> {

	@Override
	public ChatMessage mapRow(ResultSet rs, int i) throws SQLException {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setContent(rs.getString("content"));
		chatMessage.setCreateTime(rs.getLong("createTime"));
		chatMessage.setCreator(rs.getLong("creator"));
		chatMessage.setId(rs.getLong("id"));
		return chatMessage;
	}

	
}
