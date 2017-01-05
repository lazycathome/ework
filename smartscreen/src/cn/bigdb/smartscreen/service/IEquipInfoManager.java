package cn.bigdb.smartscreen.service;

import java.util.List;

import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.model.User;

public interface IEquipInfoManager {
	public String addEquip(EquipInfo equip);
	public String deleteById(String id);
	public EquipInfo getEquip(String id);
	public List<EquipInfo> getAllEquip();
	public String updateEquip(EquipInfo equip);
	public String delEquip(String id);
	public List<EquipInfo> queryEquip(EquipInfo equip);
	public List<EquipInfo> getEquipByUser(User user);
//	public List getList();
}
