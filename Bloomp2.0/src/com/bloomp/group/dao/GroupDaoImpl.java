package com.bloomp.group.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Code;
import com.bloomp.Constants;
import com.bloomp.core.JDBCUtils;
import com.bloomp.group.entity.Group;
import com.bloomp.group.entity.GroupRowMapper;

@Repository
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private JDBCUtils<Group> jdbc;
	
	@Override
	public long save(Group group) throws SQLException {
		String sql = "insert into group(name, creator, state, createTime, updateTime) values(?, ?, ?, ?, ?)";
		List<Object> list = new ArrayList<Object>(5);
		list.add(group.getName());
		list.add(group.getCreator());
		list.add(group.getState());
		list.add(group.getCreateTime());
		list.add(group.getUpdateTime());
		jdbc.save(sql, list);
		return jdbc.queryForObject(Constants.LAST_INSERT_ID, null, Long.class);
	}

	@Override
	public int update(long id, String name, long updateTime)
			throws SQLException {
		String sql = "update group set name=?, updateTime=? where id=?";
		List<Object> list = new ArrayList<Object>(3);
		list.add(name);
		list.add(updateTime);
		list.add(id);
		jdbc.update(sql, list);
		return Code.SUCCESS;
	}

	@Override
	public void delete(long id) throws SQLException {
		String sql = "delete from group where id=?";
		List<Object> list = new ArrayList<Object>(1);
		list.add(id);
		jdbc.delete(sql, list);
	}

	@Override
	public List<Group> get(long creator) throws SQLException {
		String sql = "select id, name, creator, state, createTime, updateTime where creator = ?";
		return jdbc.executeSql(sql, new Object[]{creator}, new GroupRowMapper());
	}

}
