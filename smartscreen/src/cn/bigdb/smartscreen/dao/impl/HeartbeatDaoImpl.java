package cn.bigdb.smartscreen.dao.impl;

import org.apache.log4j.Logger;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.dao.HeartbeatDao;
import cn.bigdb.smartscreen.dao.HibernateDAO;
import cn.bigdb.smartscreen.model.Heartbeat;

public class HeartbeatDaoImpl implements HeartbeatDao{

	private static Logger logger =  Logger.getLogger(HeartbeatDaoImpl.class);
	
	private HibernateDAO hdao;
	
	@Override
	public String addHeartbeat(Heartbeat heartbeat) {
		try {
			hdao.save(heartbeat);
		} catch (DAOException e) {
			logger.error("保存心跳数据失败");
			return Constants.OP_ERR;
		}
		return Constants.OP_SUCCESS;
	}

	public HibernateDAO getHdao() {
		return hdao;
	}

	public void setHdao(HibernateDAO hdao) {
		this.hdao = hdao;
	}

	
}
