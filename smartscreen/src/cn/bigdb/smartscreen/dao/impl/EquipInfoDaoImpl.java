package cn.bigdb.smartscreen.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.dao.EquipInfoDao;
import cn.bigdb.smartscreen.dao.HibernateDAO;
import cn.bigdb.smartscreen.model.EquipInfo;

public class EquipInfoDaoImpl implements EquipInfoDao {
	
	private HibernateDAO hdao;
	private static Logger logger = Logger.getLogger(EquipInfoDaoImpl.class);
	public HibernateDAO getHdao() {
		return hdao;
	}

	public void setHdao(HibernateDAO hdao) {
		this.hdao = hdao;
	}

	@Override
	public String addEquip(EquipInfo equip) {
		// TODO Auto-generated method stub
		try {
			hdao.save(equip);
			return Constants.OP_SUCCESS;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.info("添加设备信息失败");
			logger.error(e.getMessage(), e);
			return Constants.OP_ERR;
		}
		
	}

	@Override
	public String delete(String id) {
		// TODO Auto-generated method stub
		try {
			hdao.delete(EquipInfo.class, id);
			return Constants.OP_SUCCESS;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.info("删除设备信息失败！");
			logger.error(e.getMessage(),e);
			return Constants.OP_ERR;
		}
		
	}
	
	@Override
	public EquipInfo get(String id){
		try {
			return (EquipInfo) hdao.get(EquipInfo.class, id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.info("获取设备信息失败！");
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipInfo> getAllEquip() {
		// TODO Auto-generated method stub
		String sql = "from EquipInfo e";
		List<EquipInfo> equips = null;
		try {
			equips = (List<EquipInfo>) hdao.getList(sql);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		return equips;
	}

	@Override
	public String update(EquipInfo equip) {
		// TODO Auto-generated method stub
		 try {
			hdao.saveOrUpdate(equip);
			return Constants.OP_SUCCESS;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.info("更新设备信息失败----"+equip.getId()+"----"+equip.getName());
			logger.error(e.getMessage(), e);
			return Constants.OP_ERR;
		}
	}

	@Override
	public List<EquipInfo> query(String sql, Map params) {
		// TODO Auto-generated method stub
		try {
			return (List<EquipInfo>) hdao.getList(sql, params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
