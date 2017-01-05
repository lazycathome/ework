package cn.bigdb.smartscreen.dao;

import cn.bigdb.smartscreen.model.EquipUpdate;

public interface EquipUpdateDao extends BaseDao {

	String addEquipUpdate(EquipUpdate equipUpdate);
	
	String updateEquipUpdate(long status, long updateTime, String equipUpdateId);
	
}
