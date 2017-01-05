package cn.bigdb.smartscreen.contorller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.bigdb.smartscreen.model.Heartbeat;
import cn.bigdb.smartscreen.service.IHeartbeatManager;
import cn.bigdb.smartscreen.utils.Utils;

@Controller
public class HeartbeatController {

	@Resource(name="heartbeatManager")  
    private IHeartbeatManager heartbeatManager; 

	@RequestMapping(value = "/heartbeat/add", method = RequestMethod.GET)
	public String addBeartbeat(Heartbeat heartbeat) {
		heartbeat.setId(Utils.getPriKeyId());
		heartbeat.setLastTime(System.currentTimeMillis());
		return heartbeatManager.addHeartbeat(heartbeat);
	}

	public IHeartbeatManager getHeartbeatManagerr() {
		return heartbeatManager;
	}

	public void setHeartbeatManagerr(IHeartbeatManager heartbeatManager) {
		this.heartbeatManager = heartbeatManager;
	}
	
}
