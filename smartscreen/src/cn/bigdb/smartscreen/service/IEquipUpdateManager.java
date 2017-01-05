package cn.bigdb.smartscreen.service;

import cn.bigdb.smartscreen.model.EquipUpdate;


public interface IEquipUpdateManager {

	String addEquipUpdate(EquipUpdate equipUpdate);
	
	String updateEquipUpdate(long status, long updateTime, String equipUpdateId);
	
}
