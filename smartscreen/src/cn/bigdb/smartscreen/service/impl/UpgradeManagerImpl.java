package cn.bigdb.smartscreen.service.impl;

import java.util.List;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.dao.UpgradeDao;
import cn.bigdb.smartscreen.model.Content;
import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.model.EquipUpdate;
import cn.bigdb.smartscreen.model.UpdateFile;
import cn.bigdb.smartscreen.service.IEquipUpdateManager;
import cn.bigdb.smartscreen.service.IUpgradeManager;
import cn.bigdb.smartscreen.utils.FileUtils;
import cn.bigdb.smartscreen.utils.MD5Utils;
import cn.bigdb.smartscreen.utils.Utils;
import cn.bigdb.smartscreen.utils.ZIPUtils;
import cn.bigdb.smartscreen.vo.UpgradeVo;

public class UpgradeManagerImpl implements IUpgradeManager {

	private UpgradeDao upgradeDao;
	private IEquipUpdateManager equipUpdateManager;
	
	@Override
	public String getUpgradeInfo(String equipId, long lastTime, int status, String type) {
		long time = System.currentTimeMillis();
		List<Content> jsonData = this.upgradeDao.getUpgradeContentByEquipId(equipId);
		
		UpgradeVo upgrade = new UpgradeVo();
		String result = Utils.object2Json(jsonData);
		FileUtils fileUtils = new FileUtils();
		String resourceDataFileName = "";
		if(!result.isEmpty()){
			//使用jsonData数据生成json文件
			resourceDataFileName = fileUtils.createJsonFile(equipId, result);
			if(Utils.isEmpty(resourceDataFileName)) return result;
		}else{
			return result;
		}
		
		List<String> files = fileUtils.getUpgradeFiles(lastTime);
		files.add(resourceDataFileName);
		String zipFile = lastTime+".zip";
		boolean flag = ZIPUtils.zip(files, zipFile);
		
		if(flag){
			String md5Code = MD5Utils.getMD5String(zipFile);
			String addUpdateResult = addEquipUpdate(equipId, zipFile, time, md5Code);
			if(Constants.OP_SUCCESS.equals(addUpdateResult)){
				upgrade.setEquipId(equipId);
				upgrade.setMd5Code(md5Code);
				upgrade.setUpdateTime(time);
				upgrade.setZipUrl(zipFile);
				result = Utils.object2Json(upgrade);
			}
		}
		return result;
	}

	private String addEquipUpdate(String equipId, String zipFile, long time, String md5Code){
		EquipUpdate equipUpdate = new EquipUpdate();
		EquipInfo equip = new EquipInfo();
		UpdateFile updateFile = new UpdateFile();
		equip.setId(equipId);
		equipUpdate.setId(Utils.getPriKeyId());
		equipUpdate.setEquip(equip);
		equipUpdate.setStatus(-1);
		equipUpdate.setUpdateTime(time);
		equipUpdate.setUpdateFile(updateFile);
		updateFile.setCreateTime(time);
		updateFile.setEquipUpdate(equipUpdate);
		updateFile.setFileUrl(zipFile);
		updateFile.setId(Utils.getPriKeyId());
		updateFile.setMd5code(md5Code);
		return equipUpdateManager.addEquipUpdate(equipUpdate);
	}
	
	public UpgradeDao getUpgradeDao() {
		return upgradeDao;
	}

	public void setUpgradeDao(UpgradeDao upgradeDao) {
		this.upgradeDao = upgradeDao;
	}

	public IEquipUpdateManager getEquipUpdateManager() {
		return equipUpdateManager;
	}

	public void setEquipUpdateManager(IEquipUpdateManager equipUpdateManager) {
		this.equipUpdateManager = equipUpdateManager;
	}
	
}
