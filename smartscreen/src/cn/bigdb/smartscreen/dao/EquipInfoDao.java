package cn.bigdb.smartscreen.dao;

import java.util.List;
import java.util.Map;

import cn.bigdb.smartscreen.model.EquipInfo;

public interface EquipInfoDao {
	public String addEquip(EquipInfo equip);
	public String delete(String id);
	public EquipInfo get(String id);
	public List<EquipInfo> getAllEquip();
	public String update(EquipInfo equip);
	public List<EquipInfo> query(String sql,Map params);
}
