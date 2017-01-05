package com.bloomp.chat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.chat.entity.ChatStatistics;
import com.bloomp.chat.entity.ChatStatisticsRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class ChatStatisticsDaoImpl implements ChatStatisticsDao {

	@Autowired
	private JDBCUtils<ChatStatistics> jdbc;
	
	@Override
	public int save(List<ChatStatistics> chatStatisticss) throws SQLException {
		String sql = "insert into chatStatistics(chatId, createTime, updateTime) values(?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>();
		for(ChatStatistics chatStatistics : chatStatisticss){
			List<Object> list = new ArrayList<Object>();
			list.add(chatStatistics.getChatId());
			list.add(chatStatistics.getCreateTime());
			list.add(chatStatistics.getUpdateTime());
			vList.add(list);
		}
		jdbc.batchSave(sql, vList);
		return 1;
	}

	@Override
	public int update(List<Long> chatIds, int num) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		
		if(num == 1){
			sql.append("update chatStatistics set num = num+1, count = count+1 where chatId in (");
			for(long chatId : chatIds){
				sql.append("?,");
				list.add(chatId);
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
		}else{
			sql.append("update chatStatistics set num = 0 where chatId=?");
			list.add(chatIds.get(0));
		}
		
		
		jdbc.update(sql.toString(), list);
		return 1;
	}
	
	@Override
	public List<ChatStatistics> query(List<Long> chatIds) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from chatStatistics where chatId in(");
		for(long chatId : chatIds){
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		List<ChatStatistics> chatStatisticss = jdbc.executeSql(sql.toString(), chatIds.toArray(), new ChatStatisticsRowMapper());
		if(chatStatisticss != null && chatStatisticss.size() > 0)
			return chatStatisticss;
		return new ArrayList<ChatStatistics>();
	}

	@Override
	public long queryCount(List<Long> chatIds) throws SQLException {
		StringBuilder sql = new StringBuilder("select sum(num) from chatStatistics where chatId in(");
		List<Object> list = new ArrayList<Object>();
		for(long chatId : chatIds){
			sql.append("?,");
			list.add(chatId);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		return jdbc.queryCount(sql.toString(),list);
	}

}
