package cn.bigdb.gallery.equip.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.equip.dao.EquipInfoDao;
import cn.bigdb.gallery.equip.entity.EquipInfo;

@Service
@Transactional
public class EquipInfoServiceImpl implements EquipInfoService {

	@Autowired
	private EquipInfoDao equipInfoDao;
	
	@Override
	public int save(String id, String code, int width, int height, EquipInfo.Category category) {
		EquipInfo equip = new EquipInfo();
		equip.setId(id);
		equip.setCode(code);
		equip.setWidth(width);
		equip.setHeight(height);
		equip.setCategory(category.value());
		try {
			equipInfoDao.save(equip);
			return Code.SUCCESS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}

	@Override
	public EquipInfo get(String equipId) {
		try {
			return equipInfoDao.get(equipId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new EquipInfo();
	}

}
