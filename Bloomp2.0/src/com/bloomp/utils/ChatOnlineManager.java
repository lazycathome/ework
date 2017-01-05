package com.bloomp.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;

import com.bloomp.account.entity.Login;

public class ChatOnlineManager {

	private static ConcurrentMap<Long, Login> chatOnlineMap = new ConcurrentHashMap<Long, Login>();
	
	public static final ChatOnlineManager getInstance() {  
	    return ChatOnlineManagerHanlder.INSTANCE;  
	}  
	
	public static class ChatOnlineManagerHanlder{
		private static final ChatOnlineManager INSTANCE = new ChatOnlineManager();
	} 
	
	public void put(long id, Login login){
		chatOnlineMap.put(id, login);
	}
	
	public Login get(long id){
		Login login = chatOnlineMap.get(id);
		if(login != null && StringUtils.isBlank(login.getDeviceToken())){
			this.remove(id);
			return null;
		}
		return login;
	}
	
	public void putAll(Map<Long, Login> map){
		chatOnlineMap.putAll(map);
	}
	
	public void remove(long id){
		chatOnlineMap.remove(id);
	}
}
