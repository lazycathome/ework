package cn.bigdb.smartscreen.service;

import java.util.List;

import cn.bigdb.smartscreen.model.User;

public interface IUserManager {

	List<User> getUserList();
	
	List<User> queryList(User user);
	
	List<User> getUser(String id);

	boolean updateUser(User user);

	boolean delUser(String id);

	void addUser(User user);
	
	String login(String name, String password);
}
