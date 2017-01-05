package cn.bigdb.smartscreen.service.impl;

import java.util.List;

import cn.bigdb.smartscreen.dao.PlazaAreaDao;
import cn.bigdb.smartscreen.model.PlazaArea;
import cn.bigdb.smartscreen.service.IAreaManager;

public class AreaManagerImpl implements IAreaManager {
	
	private PlazaAreaDao dao;
	
	
	
	public PlazaAreaDao getDao() {
		return dao;
	}



	public void setDao(PlazaAreaDao dao) {
		this.dao = dao;
	}



	@Override
	public List<PlazaArea> getList() {
		// TODO Auto-generated method stub
		return dao.getArea();
	}

}
