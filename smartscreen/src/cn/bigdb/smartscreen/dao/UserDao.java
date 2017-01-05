package cn.bigdb.smartscreen.dao;

import java.util.List;
import java.util.Map;

import cn.bigdb.smartscreen.model.User;

public interface UserDao {

	List<User> getUserList();
	
	List<User> queryList(Map<String, Object> map);
	
	User getUser(String id);
}
