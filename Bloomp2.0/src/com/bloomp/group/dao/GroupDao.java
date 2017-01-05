package com.bloomp.group.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.group.entity.Group;

public interface GroupDao {

	long save(Group group) throws SQLException;
	
	int update(long id, String name, long updateTime) throws SQLException;
	
	void delete(long id) throws SQLException;
	
	List<Group> get(long creator) throws SQLException;
}
