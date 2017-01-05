package com.bloomp.push;

import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("iosPushService")
public class IosPushServiceImpl implements PushService {

	private final static String certificatePath = "E:/bloomp.p12";
	private final static String certificatePassword = "123456";//此处注意导出的证书密码不能为空因为空密码会报错
	private final static String device_Token = "8bc7f526a122cff6a39cee082dead59849f26c46aabf487b2f60c6dfda4b508b";
	@Override
	public int push(Push push) {
		String deviceToken = push.getDeviceToken() == null ? device_Token : push.getDeviceToken();
        String alert = push.getContent();//push的内容
        int badge = push.getNum();//图标小红圈的数值
        String sound = "default";//铃音

        List<String> tokens = new ArrayList<String>();
        tokens.add(deviceToken);
        boolean sendCount = true;
        try{
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(alert); // 消息内容
            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
            if (!StringUtils.isBlank(sound))
            {
                payLoad.addSound(sound);//铃音
            }
            PushNotificationManager pushManager = new PushNotificationManager();
            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
            // 发送push消息
            if (sendCount)
            {
                Device device = new BasicDevice();
                device.setToken(tokens.get(0));
                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
                notifications.add(notification);
            }
            else
            {
                List<Device> device = new ArrayList<Device>();
                for (String token : tokens)
                {
                    device.add(new BasicDevice(token));
                }
                notifications = pushManager.sendNotifications(payLoad, device);
            }
            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
            int failed = failedNotifications.size();
            int successful = successfulNotifications.size();
            pushManager.stopConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

		return 0;
	}
	
	public static void main(String[] args){
		IosPushServiceImpl ios = new IosPushServiceImpl();
		Push push = new Push();
		ios.push(push);
	}

	@Override
	public int push(List<Push> pushs) {
		try{
			for(Push push : pushs){
				PushNotificationPayload payLoad = new PushNotificationPayload();
				payLoad.addAlert(push.getContent()); // 消息内容
				payLoad.addBadge(push.getNum()); // iphone应用图标上小红圈上的数值
				PushNotificationManager pushManager = new PushNotificationManager();
				//true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
				pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
				List<PushedNotification> notifications = new ArrayList<PushedNotification>();
				
				List<Device> devices = new ArrayList<Device>();
				devices.add(new BasicDevice(push.getDeviceToken() == null ? device_Token : push.getDeviceToken()));
				notifications = pushManager.sendNotifications(payLoad, devices);
				
				List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
				List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
				int failed = failedNotifications.size();
				int successful = successfulNotifications.size();
				 
				pushManager.stopConnection();
			}
	         return 1;
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
		return 0;
	}

	@Override
	public int push(List<String> accountList, String content) {
	     
	     try{
		     PushNotificationPayload payLoad = new PushNotificationPayload();
	         payLoad.addAlert(content); // 消息内容
	         payLoad.addBadge(10); // iphone应用图标上小红圈上的数值
	         PushNotificationManager pushManager = new PushNotificationManager();
	         //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
	         pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
	         List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	         
	         List<Device> devices = new ArrayList<Device>();
	         for (String token : accountList){
	        	 devices.add(new BasicDevice(token));
	         }
	         notifications = pushManager.sendNotifications(payLoad, devices);
	         
	         List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	         List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	         int failed = failedNotifications.size();
	         int successful = successfulNotifications.size();
	         
	         pushManager.stopConnection();
	         return 1;
	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
		return 0;
	}

}
