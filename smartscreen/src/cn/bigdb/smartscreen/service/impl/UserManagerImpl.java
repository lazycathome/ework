package cn.bigdb.smartscreen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.dao.UserDao;
import cn.bigdb.smartscreen.model.User;
import cn.bigdb.smartscreen.service.IUserManager;

public class UserManagerImpl implements IUserManager {

	private UserDao userDao;
	
	@Override
	public List<User> getUserList() {
		userDao.getUserList();
		return null;
	}

	@Override
	public List<User> queryList(User user) {
		userDao.queryList(null);
		return null;
	}

	@Override
	public List<User> getUser(String id) {
		userDao.getUserList();
		return null;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String login(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		List<User> users = this.userDao.queryList(map);
		if(users != null && users.size() > 0 ){
			return Constants.OP_SUCCESS;
		}
		return Constants.OP_ERR;
	}
	
}
