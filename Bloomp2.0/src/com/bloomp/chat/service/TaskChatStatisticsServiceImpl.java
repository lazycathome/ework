package com.bloomp.chat.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.chat.dao.ChatStatisticsDao;
import com.bloomp.chat.dao.TaskChatDao;
import com.bloomp.chat.entity.ChatStatistics;
import com.bloomp.chat.entity.TaskChat;

@Service
@Transactional
public class TaskChatStatisticsServiceImpl implements TaskChatStatisticsService {

	@Autowired
	private TaskChatDao taskChatDao;
	
	@Autowired
	private ChatStatisticsDao chatStatisticsDao;
	
	@Override
	public List<ChatStatistics> query(long accountId) {
		try {
			List<TaskChat> taskChats = taskChatDao.query(accountId);
			if(taskChats.size() > 0){
				List<Long> chatIds = new ArrayList<Long>();
				Map<Long, Long> chatTaskIdMap = new HashMap<Long, Long>();
				for(TaskChat taskChat : taskChats){
					chatIds.add(taskChat.getId());
					chatTaskIdMap.put(taskChat.getId(), taskChat.getTaskId());
				}
				List<ChatStatistics> chatStatisticss = chatStatisticsDao.query(chatIds);
				for(ChatStatistics chatStatistics : chatStatisticss){
					chatStatistics.setTaskId(chatTaskIdMap.get(chatStatistics.getChatId()));
				}
				return chatStatisticss; 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<ChatStatistics>();
	}

	@Override
	public int clear(long taskId, long accountId) {
		try {
			TaskChat taskChat = taskChatDao.query(taskId, accountId);
			if(!taskChat.isEmpty()){
				List<Long> chatIds = new ArrayList<Long>();
				chatIds.add(taskChat.getId());
				chatStatisticsDao.update(chatIds, 0);
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public long queryCountForAccountId(long accountId) {
		try {
			List<TaskChat> taskChats = taskChatDao.query(accountId);
			List<Long> chatIds = new ArrayList<Long>();
			for(TaskChat taskChat : taskChats){
				chatIds.add(taskChat.getId());
			}
			return chatStatisticsDao.queryCount(chatIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
