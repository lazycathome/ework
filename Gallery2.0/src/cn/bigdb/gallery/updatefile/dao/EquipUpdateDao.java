package cn.bigdb.gallery.updatefile.dao;

import java.sql.SQLException;

import cn.bigdb.gallery.updatefile.entity.EquipUpdate;

public interface EquipUpdateDao{

	long addEquipUpdate(EquipUpdate equipUpdate) throws SQLException;
	
	int updateEquipUpdate(long status, long updateTime, String equipUpdateId) throws SQLException;
	
}
