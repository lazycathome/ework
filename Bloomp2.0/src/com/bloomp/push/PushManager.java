package com.bloomp.push;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloomp.account.entity.Login;
import com.bloomp.chat.service.TaskChatStatisticsService;
import com.bloomp.utils.ChatOnlineManager;

@Component
public class PushManager {

	@Autowired
	private PushService xgPushService;
	
	@Autowired
	private PushService iosPushService;
	
	@Autowired
	private TaskChatStatisticsService taskChatStatisticsService;
	
	public static PushManager getInstance(){
		return PushManagerHanlder.INSTANCE;
	}
	
	private final static class PushManagerHanlder{
		private final static PushManager INSTANCE = new PushManager();
	}
	
	public void push(int num, long taskId, long accountId){
		Login login  = ChatOnlineManager.getInstance().get(accountId);
		final int type = login.getType();
		final Push push = new Push();
		push.setAccountId(accountId);
		push.setDeviceToken(login.getDeviceToken());
		push.setNum(num);
		push.setTaskId(taskId);
		new Runnable() {
			public void run() {
				if(type == 1){
					xgPushService.push(push);
				}else if(type == 2){
					iosPushService.push(push);
				}
			}
		}.run();
	}
	
	
	public void push(long executor, final String content, List<Long> observers){
		Login executorLogin  = ChatOnlineManager.getInstance().get(executor);
		final List<Push> iosPush = new ArrayList<Push>();
		final List<Push> anPush = new ArrayList<Push>();
		final List<String> anAccountList = new ArrayList<String>();
		final List<String> iosAccountList = new ArrayList<String>();
		if(executorLogin.getType() == 1){
			anAccountList.add(executorLogin.getDeviceToken());
		}else{
			iosAccountList.add(executorLogin.getDeviceToken());
		}
		
		for(Long accountId : observers){
			Login login  = ChatOnlineManager.getInstance().get(accountId);
			
			int type = login.getType();
			if(type == 2){
				iosAccountList.add(login.getDeviceToken());
			}else if (type == 1){
				anAccountList.add(login.getDeviceToken());
			}
			
		}
		
		if(anAccountList.size() >= 1){
			new Runnable() {
				public void run() {
					xgPushService.push(anAccountList, content);
				}
			}.run();
		}else if(iosAccountList.size() >= 1){
			new Runnable(){
				public void run() {
					iosPushService.push(iosPush);
				}
			}.run();
		}
		
	}
	
	public void push(List<Long> accountIds, final long taskId, String content){
		final List<Push> iosPush = new ArrayList<Push>();
		final List<Push> anPush = new ArrayList<Push>();
		final List<String> anAccountList = new ArrayList<String>();
		for(Long accountId : accountIds){
			Login login  = ChatOnlineManager.getInstance().get(accountId);
			if(login == null) continue;
			Push push = new Push();
			push.setAccountId(accountId);
			push.setDeviceToken(login.getDeviceToken());
			push.setTaskId(taskId);
			push.setContent(content);
			int type = login.getType();
			if(type == 2){
				long num = taskChatStatisticsService.queryCountForAccountId(accountId);
				push.setNum((int)num);
				iosPush.add(push);
			}else if (type == 1){
				anPush.add(push);
			}
		}
		
		if(anAccountList.size() >= 1){
			new Runnable() {
				public void run() {
					xgPushService.push(anPush);
				}
			}.run();
		}else if(iosPush.size() >= 1){
			new Runnable(){
				public void run() {
					iosPushService.push(iosPush);
				}
			}.run();
		}
	}

}
