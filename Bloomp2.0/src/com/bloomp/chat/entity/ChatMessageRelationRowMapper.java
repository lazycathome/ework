package com.bloomp.chat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChatMessageRelationRowMapper implements RowMapper<ChatMessageRelation> {

	@Override
	public ChatMessageRelation mapRow(ResultSet rs, int i) throws SQLException {
		ChatMessageRelation chatMessageRelation = new ChatMessageRelation();
		chatMessageRelation.setChatId(rs.getLong("chatId"));
		chatMessageRelation.setChatMessageId(rs.getLong("chatMessageId"));
		chatMessageRelation.setId(rs.getLong("id"));
		return chatMessageRelation;
	}

	
}
