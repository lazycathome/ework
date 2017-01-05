package cn.bigdb.smartscreen.service.impl;

import cn.bigdb.smartscreen.dao.HeartbeatDao;
import cn.bigdb.smartscreen.model.Heartbeat;
import cn.bigdb.smartscreen.service.IHeartbeatManager;

public class HeartbeatManagerImpl implements IHeartbeatManager {

	private HeartbeatDao heartbeatDao;
	
	@Override
	public String addHeartbeat(Heartbeat heartbeat) {
		return heartbeatDao.addHeartbeat(heartbeat);
	}

	public HeartbeatDao getHeartbeatDao() {
		return heartbeatDao;
	}

	public void setHeartbeatDao(HeartbeatDao heartbeatDao) {
		this.heartbeatDao = heartbeatDao;
	}

}
