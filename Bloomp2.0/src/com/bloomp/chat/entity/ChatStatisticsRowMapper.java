package com.bloomp.chat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChatStatisticsRowMapper implements RowMapper<ChatStatistics> {

	@Override
	public ChatStatistics mapRow(ResultSet rs, int i) throws SQLException {
		ChatStatistics chatStatistics = new ChatStatistics();
		chatStatistics.setCount(rs.getInt("count"));
		chatStatistics.setCreateTime(rs.getLong("createTime"));
		chatStatistics.setNum(rs.getInt("num"));
		chatStatistics.setChatId(rs.getLong("chatId"));
		chatStatistics.setUpdateTime(rs.getLong("updateTime"));
		return chatStatistics;
	}

	
}
