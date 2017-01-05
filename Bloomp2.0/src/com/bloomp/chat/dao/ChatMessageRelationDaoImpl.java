package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.chat.entity.ChatMessageRelation;
import com.bloomp.chat.entity.ChatMessageRelationRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class ChatMessageRelationDaoImpl implements ChatMessageRelationDao {

	@Autowired
	private JDBCUtils<ChatMessageRelation> jdbc;
	
	@Override
	public List<ChatMessageRelation> query(long chatId, long lastQueryTime) throws SQLException {
		String sql  = "select * from chatMessageRelation where chatId=? and createTime >= ?";
		List<ChatMessageRelation> chatMessageRelations = jdbc.executeSql(sql, new Object[]{chatId, lastQueryTime}, new ChatMessageRelationRowMapper());
		if(chatMessageRelations != null && chatMessageRelations.size() > 0)
			return chatMessageRelations;
		return new ArrayList<ChatMessageRelation>();
	}

	@Override
	public long save(List<ChatMessageRelation> chatMessageRelations)
			throws SQLException {
		String sql = "insert into chatMessageRelation(chatId, chatMessageId, createTime) values(?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>();
		for(ChatMessageRelation chatMessageRelation: chatMessageRelations){
			List<Object> list = new ArrayList<Object>();
			list.add(chatMessageRelation.getChatId());
			list.add(chatMessageRelation.getChatMessageId());
			list.add(chatMessageRelation.getCreateTime());
			vList.add(list);
		}
		jdbc.batchSave(sql, vList);
		return jdbc.queryForLong(Constants.LAST_INSERT_ID);
	}

}
