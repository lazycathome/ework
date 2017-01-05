package cn.bigdb.smartscreen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bigdb.smartscreen.dao.EquipInfoDao;
import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.model.PlazaArea;
import cn.bigdb.smartscreen.model.PlazaInfo;
import cn.bigdb.smartscreen.model.User;
import cn.bigdb.smartscreen.service.IEquipInfoManager;
import cn.bigdb.smartscreen.utils.Utils;

public class EquipInfoManagerImpl implements IEquipInfoManager {

	private EquipInfoDao dao;


	public EquipInfoDao getDao() {
		return dao;
	}


	public void setDao(EquipInfoDao dao) {
		this.dao = dao;
	}


	@Override
	public String addEquip(EquipInfo equip) {
		// TODO Auto-generated method stub
		return dao.addEquip(equip);
	}


	@Override
	public String deleteById(String id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}


	@Override
	public EquipInfo getEquip(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}


	@Override
	public List<EquipInfo> getAllEquip() {
		// TODO Auto-generated method stub
		return dao.getAllEquip();
	}

	
	
	@Override
	public String updateEquip(EquipInfo equip) {
		// TODO Auto-generated method stub
		equip.setUpdateTime(System.currentTimeMillis());
		return dao.update(equip);
	}


	@Override
	public String delEquip(String id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}


	@Override
	public List<EquipInfo> queryEquip(EquipInfo equip) {
		// TODO Auto-generated method stub
		if(equip == null)
			return dao.getAllEquip();
		String sql = " from EquipInfo e where 1=1 ";
		StringBuffer condition = new StringBuffer();
		Map<String,Object> params = new HashMap<String,Object>();
		PlazaArea a = equip.getArea();
		if(a != null){
			String aid = a.getId();
			condition.append(" and e.area.id =:area");
			params.put("area", aid);
		}
		String code = equip.getCode();
		if(Utils.isEmpty(code)){
			condition.append(" and e.code=:code");
			params.put("code", code);
		}
		String location = equip.getLocation();
		if(Utils.isEmpty(location)){
			condition.append(" and e.location=:location");
			params.put("location", location);
		}
		PlazaInfo p = equip.getPlaza();
		if(p != null){
			String pid = p.getId();
			condition.append(" and e.plaza.id =:plaza");
			params.put("plaza", pid);
		}
		
		int status = equip.getStatus();
		if(status != -1){
			condition.append(" and e.status =:status");
			params.put("status", status);
		}
		
		String name = equip.getName();
		if(Utils.isEmpty(name)){
			condition.append(" and e.name like :name");
			params.put("name", "%"+name+"%");
		}
		sql = sql+condition.toString();
		return dao.query(sql, params);
	}
	
	@Override
	public List<EquipInfo> getEquipByUser(User user) {
		// TODO Auto-generated method stub
		
		return null;
	}

	

}
