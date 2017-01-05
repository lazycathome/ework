package cn.bigdb.gallery.equip.service;

import cn.bigdb.gallery.equip.entity.EquipInfo;

public interface EquipInfoService {

	int save(String id, String code, int width, int height, EquipInfo.Category category);
	
	EquipInfo get(String equipId);
}
