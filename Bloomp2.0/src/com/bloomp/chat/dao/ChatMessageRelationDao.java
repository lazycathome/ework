package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.chat.entity.ChatMessageRelation;

public interface ChatMessageRelationDao {

	List<ChatMessageRelation> query(long chatId, long lastQueryTime) throws SQLException;
	
	long save(List<ChatMessageRelation> chatMessageRelations) throws SQLException;
}
