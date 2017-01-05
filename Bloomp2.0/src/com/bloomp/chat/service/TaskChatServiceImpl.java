package com.bloomp.chat.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.chat.dao.ChatMessageDao;
import com.bloomp.chat.dao.ChatMessageRelationDao;
import com.bloomp.chat.dao.ChatStatisticsDao;
import com.bloomp.chat.dao.TaskChatDao;
import com.bloomp.chat.entity.ChatMessage;
import com.bloomp.chat.entity.ChatMessageRelation;
import com.bloomp.chat.entity.ChatStatistics;
import com.bloomp.chat.entity.TaskChat;
import com.bloomp.push.PushManager;
import com.bloomp.task.dao.TaskOwnerDao;
import com.bloomp.task.entity.TaskOwner;

@Service
@Transactional
public class TaskChatServiceImpl implements TaskChatService {

	@Autowired
	private TaskChatDao taskChatDao;

	@Autowired
	private ChatMessageDao chatMessageDao;

	@Autowired
	private ChatMessageRelationDao chatMessageRelationDao;

	@Autowired
	private ChatStatisticsDao chatStatisticsDao;
	
	@Autowired
	private TaskOwnerDao taskOwnerDao;
	
	@Autowired
	private PushManager pushManager;

	@Override
	public List<ChatMessage> queryTaskChatMessage(long taskId, long accountId, long lastQueryTime) {
		try {
			TaskChat taskChat = taskChatDao.query(taskId, accountId);
			if(!taskChat.isEmpty()){
				List<ChatMessageRelation> chatMessageRelations = chatMessageRelationDao.query(taskChat.getId(), lastQueryTime);
				if(chatMessageRelations.size() > 0 ){
					List<Long> ids = new ArrayList<Long>();
					for(ChatMessageRelation chatMessageRelation : chatMessageRelations){
						ids.add(chatMessageRelation.getChatMessageId());
					}
					List<ChatMessage> result = chatMessageDao.queryList(ids);
					if(result.size() > 0){
						List<Long> chatIds = new ArrayList<Long>();;
						chatIds.add(taskChat.getId());
						chatStatisticsDao.update(chatIds, 0);
					}
					return result;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ChatMessage>();
	}

	@Override
	public long sendMessage(ChatMessage chatMessage, long taskId) {
		try {
			long chatMessageId = 0;
			List<TaskOwner> taskOwners = taskOwnerDao.queryListForTaskId(taskId, 0);
			List<Long> accountIds = new ArrayList<Long>();
			Map<Long, Long> accountMap = new HashMap<Long, Long>();
			for(TaskOwner taskOwner : taskOwners){
				accountMap.put(taskOwner.getAccountId(), taskOwner.getAccountId());
				accountIds.add(taskOwner.getAccountId());
			}
			long time = System.currentTimeMillis();
			List<TaskChat> taskChats = taskChatDao.query(taskId, accountIds);
			List<Long> chatIds = new ArrayList<Long>();
			
			for(TaskChat taskChat : taskChats){
				accountMap.remove(taskChat.getAccountId());
				chatIds.add(taskChat.getId());
			}
			if(accountMap.size() > 0){
				Iterator<Entry<Long, Long>> it = accountMap.entrySet().iterator();
				List<Long> tAccountIds = new ArrayList<Long>();
				List<TaskChat> tTaskChats = new ArrayList<TaskChat>();
				
				while(it.hasNext()){
					Entry<Long, Long> entry = it.next();
					tAccountIds.add(entry.getKey());
					TaskChat tTaskChat = new TaskChat();
					tTaskChat.setAccountId(entry.getKey());
					tTaskChat.setCreateTime(time);
					tTaskChat.setTaskId(taskId);
					tTaskChats.add(tTaskChat);
				}
				long maxId = taskChatDao.save(tTaskChats);
				for(int i = (accountMap.size()-1); i >= 0; i--){
					chatIds.add(maxId-i);
				}
				
			}
			
			chatMessage.setCreateTime(time);
			chatMessageId = chatMessageDao.save(chatMessage);
			
			List<ChatMessageRelation> chatMessageRelations = new ArrayList<ChatMessageRelation>();
			List<ChatStatistics> chatStatisticss = chatStatisticsDao.query(chatIds);
			Map<Long, Long> tChatIdMap = new HashMap<Long,Long>();
			for(ChatStatistics chatStatistics : chatStatisticss){
				tChatIdMap.put(chatStatistics.getChatId(), chatStatistics.getChatId());
			}
			List<ChatStatistics> aChatStatisticss = new ArrayList<ChatStatistics>();
			for(long chatId : chatIds){
				ChatMessageRelation chatMessageRelation = new ChatMessageRelation();
				chatMessageRelation.setChatId(chatId);
				chatMessageRelation.setChatMessageId(chatMessageId);
				chatMessageRelation.setCreateTime(time);
				chatMessageRelations.add(chatMessageRelation);
				if(tChatIdMap.get(chatId) == null){
					ChatStatistics temp = new ChatStatistics();
					temp.setChatId(chatId);
					temp.setNum(1);
					temp.setCount(1);
					temp.setCreateTime(time);
					temp.setUpdateTime(time);
					aChatStatisticss.add(temp);
				}
			}
			chatMessageRelationDao.save(chatMessageRelations);
			
			chatStatisticsDao.save(aChatStatisticss);
			chatStatisticsDao.update(chatIds, 1);
			pushManager.push(accountIds, taskId, chatMessage.getContent());
			return chatMessageId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

}
