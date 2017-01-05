package cn.bigdb.smartscreen.service.impl;

import cn.bigdb.smartscreen.dao.EquipUpdateDao;
import cn.bigdb.smartscreen.model.EquipUpdate;
import cn.bigdb.smartscreen.service.IEquipUpdateManager;

public class EquipUpdateManagerImpl implements IEquipUpdateManager {

	private EquipUpdateDao equipUpdateDao;
	
	
	@Override
	public String addEquipUpdate(EquipUpdate equipUpdate) {
		String result = equipUpdateDao.addEquipUpdate(equipUpdate);
		return result;
	}

	@Override
	public String updateEquipUpdate(long status, long updateTime, String equipUpdateId) {
		String result = equipUpdateDao.updateEquipUpdate(status, updateTime, equipUpdateId);
		return result;
	}

	public EquipUpdateDao getEquipUpdateDao() {
		return equipUpdateDao;
	}

	public void setEquipUpdateDao(EquipUpdateDao equipUpdateDao) {
		this.equipUpdateDao = equipUpdateDao;
	}

}
