package com.bloomp.push;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;

@Service("xgPushService")
public class XinGePushServiceImpl implements PushService {
	
	private final static int secret_id = 2100078985;
	private final static String secret_key = "82d7e35f1c1a2fbe2a3a6a39427a8705";
	
	//单个设备下发透传消息
	protected JSONObject demoPushSingleDeviceMessage(Push push)
	{
		String deviceToken = push.getDeviceToken();
		push.setDeviceToken(null);
		Gson gson = new Gson();
		push.setDeviceToken(null);
		String content = gson.toJson(push);
		XingeApp xinge = new XingeApp(secret_id, secret_key);
		Message message = new Message();
		message.setTitle("title");
		message.setContent(content);
		message.setType(Message.TYPE_MESSAGE);
		message.setExpireTime(86400);
		JSONObject ret = xinge.pushSingleDevice(deviceToken, message);
		return ret;
	}
	
	@Override
	public int push(Push push) {
		JSONObject ret;
		ret = this.demoPushSingleDeviceMessage(push);
		System.out.println(ret);
		return 1;
	}
	
	public boolean ddd(){
		String a = null;
		if(a.equals("eee")){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		String token = "fe1615f22a8e48214f31a75b086bd58b20d3e58b";
		
		XinGePushServiceImpl xg = new XinGePushServiceImpl();
		try{
			xg.ddd();
		}catch(Exception e){
			
		}
		/*for(int i = 0; i < 100; i++){
			
			Push push = new Push();
			push.setAccountId(122);
			push.setDeviceToken(token);
			
			push.setTaskId(2332);
			push.setNum(i);
			System.out.println(i);
			
			xg.demoPushSingleDeviceMessage(push);
		}*/
	}
	
	
	//下发多个账号
	protected JSONObject demoPushAccountList(List<String> accountList, String content) {
		XingeApp xinge = new XingeApp(secret_id, secret_key);
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle("title");
		message.setContent(content);
		message.setType(Message.TYPE_MESSAGE);
		JSONObject ret = xinge.pushAccountList(0, accountList, message);
		return (ret);
	}

	@Override
	public int push(List<Push> pushs) {
		
		return 0;
	}

	@Override
	public int push(List<String> accountList, String content) {
		try{
			this.demoPushAccountList(accountList, content);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
}
