package cn.bigdb.smartscreen.dao.impl;

import java.util.List;

import cn.bigdb.smartscreen.model.ResourceInfo;

import java.util.Map;

import org.apache.log4j.Logger;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.dao.HibernateDAO;
import cn.bigdb.smartscreen.dao.ResourceInfoDao;

public class ResourceInfoDaoImpl implements ResourceInfoDao {

	private HibernateDAO hdao;
	private static Logger logger = Logger.getLogger(ResourceInfoDaoImpl.class);
	@Override
	public List<cn.bigdb.smartscreen.model.ResourceInfo> getResourceInfoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addResourceInfo(ResourceInfo res) {
		// TODO Auto-generated method stub
		try {
			hdao.save(res);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.error("添加资源失败");
			return Constants.OP_ERR;
		}
		return Constants.OP_SUCCESS;
	}

	@Override
	public String ResourceInfo(cn.bigdb.smartscreen.model.ResourceInfo res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public cn.bigdb.smartscreen.model.ResourceInfo getResourceInfo(String id) {
		// TODO Auto-generated method stub
		try {
			return (cn.bigdb.smartscreen.model.ResourceInfo) hdao.get(ResourceInfo.class, id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
			return null;
		}
	}

	@Override
	public List<cn.bigdb.smartscreen.model.ResourceInfo> queryList(String sql,
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delResourceInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public HibernateDAO getHdao() {
		return hdao;
	}

	public void setHdao(HibernateDAO hdao) {
		this.hdao = hdao;
	}

	
}
