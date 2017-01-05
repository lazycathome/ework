package cn.bigdb.smartscreen.dao.impl;

import java.util.List;
import java.util.Map;

import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.dao.HibernateDAO;
import cn.bigdb.smartscreen.dao.UserDao;
import cn.bigdb.smartscreen.model.User;

public class UserDaoImpl implements UserDao {

	private HibernateDAO hdao;
	
	private String QYERTYUSERANDPASSWORD = " from User u where u.username=:username and u.password=:password";

	public HibernateDAO getHdao() {
		return hdao;
	}
	
	public void setHdao(HibernateDAO hdao) {
		this.hdao = hdao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList() {
		hdao.clearCatch();
		String hql = "from User user";
		try {
			return (List<User>) hdao.getList(hql);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> queryList(Map<String, Object> map) {
		
		try {
			return (List<User>) hdao.getList(QYERTYUSERANDPASSWORD, map);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUser(String id) {
		hdao.clearCatch();
		try {
			User user =  (User) hdao.load(User.class, id);
			return user;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
