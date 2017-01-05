package com.bloomp.friend.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.core.JDBCUtils;
import com.bloomp.friend.entity.Friend;
import com.bloomp.friend.entity.FriendRowMapper;

@Repository
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private JDBCUtils<Friend> jdbc;
	
	@Override
	public long save(List<Friend> friends) throws SQLException {
		String sql = "INSERT INTO FRIENDS(accountId, creator, state, createTime, updateTime) VALUES(?, ?, ?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>();
		for(Friend friend : friends){
			List<Object> list = new ArrayList<Object>();
			list.add(friend.getAccountId());
			list.add(friend.getCreator());
			list.add(friend.getState());
			list.add(friend.getCreateTime());
			list.add(friend.getUpdateTime());
			vList.add(list);
		}
		jdbc.batchSave(sql, vList);
		return jdbc.queryForLong(Constants.LAST_INSERT_ID);
	}

	@Override
	public List<Friend> query(long creator, long lastQueryTime) throws SQLException {
		String sql = "select * from Friends where creator=? ";//and updateTime >= ?";
		List<Friend> friends = jdbc.executeSql(sql, new Object[]{creator}, new FriendRowMapper());

		if(friends == null) 
			return new ArrayList<Friend>();;
		return friends;
	}

	@Override
	public List<Long> query(long creator, List<Long> accountIds)
			throws SQLException {
		StringBuilder sql = new StringBuilder("select id from Friends where creator=? and state=? and accountId in(");
		List<Object> vList = new ArrayList<Object>();
		vList.add(creator);
		vList.add(1);
		for(Long id : accountIds){
			sql.append("?,");
			vList.add(id);
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		
		List<Friend> friends = jdbc.executeSql(sql.toString(), vList.toArray(), new FriendRowMapper());
		List<Long> results = new ArrayList<Long>();
		if(friends == null) 
			return results;
		for(Friend friend : friends ){
			results.add(friend.getId());
		}
		return results;
	}

	@Override
	public boolean update(List<Object> ids, int state) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
