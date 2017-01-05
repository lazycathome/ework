package cn.bigdb.smartscreen.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.dao.EquipUpdateDao;
import cn.bigdb.smartscreen.dao.HibernateDAO;
import cn.bigdb.smartscreen.model.EquipUpdate;

public class EquipUpdateDaoImpl extends BaseDaoImpl implements EquipUpdateDao {

	private HibernateDAO hdao;
	
	private String UPDATEEQUIPUPDATE = "update sm_equip_update set status=? where updateTime=? and equipId=?";
	
	@Override
	public String addEquipUpdate(EquipUpdate equipUpdate) {
		String result = Constants.OP_SUCCESS;
		try {
			hdao.save(equipUpdate);
		} catch (DAOException e) {
			result = Constants.OP_ERR;
		}
		return result;
	}

	@Override
	public String updateEquipUpdate(long status, long updateTime, String equipUpdateId) {
		String result = Constants.OP_SUCCESS;
		List<List<Object>> vList = new ArrayList<List<Object>>();  
		List<Object> list = new ArrayList<Object>();
		list.add(status);
		list.add(updateTime);
		list.add(equipUpdateId);
		vList.add(list);
		try {
			this.update(UPDATEEQUIPUPDATE, vList);
		} catch (SQLException e) {
			result = Constants.OP_ERR;
		}
		return result;
	}

	public HibernateDAO getHdao() {
		return hdao;
	}

	public void setHdao(HibernateDAO hdao) {
		this.hdao = hdao;
	}

	
}
