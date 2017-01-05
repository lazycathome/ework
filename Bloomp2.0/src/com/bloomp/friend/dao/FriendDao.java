package com.bloomp.friend.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.friend.entity.Friend;

public interface FriendDao {

	long save(List<Friend> friends) throws SQLException;
	
	List<Friend> query(long creator, long lastQueryTime) throws SQLException;
	
	List<Long> query(long creator, List<Long> accountIds) throws SQLException;
	
	boolean update(List<Object> ids, int state) throws SQLException;
	
}
