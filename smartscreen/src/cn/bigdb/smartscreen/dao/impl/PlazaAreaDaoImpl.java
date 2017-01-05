package cn.bigdb.smartscreen.dao.impl;

import java.util.List;

import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.dao.HibernateDAO;
import cn.bigdb.smartscreen.dao.PlazaAreaDao;
import cn.bigdb.smartscreen.model.PlazaArea;

public class PlazaAreaDaoImpl implements PlazaAreaDao {
	
	public HibernateDAO getHdao() {
		return hdao;
	}
	public void setHdao(HibernateDAO hdao) {
		this.hdao = hdao;
	}
	private HibernateDAO hdao;
	@SuppressWarnings("unchecked")
	@Override
	public List<PlazaArea> getArea() {
		// TODO Auto-generated method stub
		try {
			return (List<PlazaArea>) hdao.getList("from PlazaArea a");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
