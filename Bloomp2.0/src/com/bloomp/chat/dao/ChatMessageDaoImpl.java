package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.chat.entity.ChatMessage;
import com.bloomp.chat.entity.ChatMessageRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class ChatMessageDaoImpl implements ChatMessageDao {

	@Autowired
	private JDBCUtils<ChatMessage> jdbc;
	
	@Override
	public long save(ChatMessage chatMessages) throws SQLException {
		String sql = "INSERT INTO CHATMESSAGE(CONTENT, CREATOR, CREATETIME, TYPE) VALUES (?, ?, ?, ?)";
		List<Object> list = new ArrayList<Object>();
		list.add(chatMessages.getContent());
		list.add(chatMessages.getCreator());
		list.add(chatMessages.getCreateTime());
		list.add(chatMessages.getType());
		jdbc.save(sql, list);
		return jdbc.queryForLong(Constants.LAST_INSERT_ID);
	}

	@Override
	public List<ChatMessage> queryList(List<Long> ids) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from chatMessage where id in(");
		for(long id : ids){
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") order by createTime desc");
		List<ChatMessage> chatMessages = jdbc.executeSql(sql.toString(), ids.toArray(), new ChatMessageRowMapper());
		if(chatMessages != null && chatMessages.size() > 0)
			return chatMessages;
		return new ArrayList<ChatMessage>();
	}

}
