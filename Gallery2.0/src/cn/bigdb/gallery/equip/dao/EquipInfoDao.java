package cn.bigdb.gallery.equip.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bigdb.gallery.core.Page;
import cn.bigdb.gallery.equip.entity.EquipInfo;

public interface EquipInfoDao {

	void save(EquipInfo equip) throws SQLException;
	
	void update(EquipInfo equip) throws SQLException;
	
	List<EquipInfo> getList(Page<EquipInfo> page, String keywords);
	
	EquipInfo get(String id) throws SQLException;
}
