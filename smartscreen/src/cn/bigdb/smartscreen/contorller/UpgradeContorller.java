package cn.bigdb.smartscreen.contorller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.service.IUpgradeManager;

@Controller
public class UpgradeContorller {

	@Resource(name="upgradeManager")
	private IUpgradeManager upgradeManager;
	
	@RequestMapping("/upgrade/{type}")
	@ResponseBody
	public String addUpgradeInfo(@PathVariable("type") String type, EquipInfo equipInfo){
		return upgradeManager.getUpgradeInfo(equipInfo.getId(), equipInfo.getHeartbeatTime(), equipInfo.getStatus(), type);
	}

	@RequestMapping("/upgrade/ack")
	@ResponseBody
	public String upgradeAckInfo(EquipInfo equipInfo){
		return Constants.OP_SUCCESS;
		//return upgradeManager;
	}
	
	public IUpgradeManager getUpgradeManager() {
		return upgradeManager;
	}

	public void setUpgradeManager(IUpgradeManager upgradeManager) {
		this.upgradeManager = upgradeManager;
	}
	
}
