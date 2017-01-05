package com.bloomp.org.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.core.JDBCUtils;
import com.bloomp.org.entity.Org;
import com.bloomp.org.entity.OrgRowMapper;

@Repository
public class OrgDaoImpl implements OrgDao {

	@Autowired
	private JDBCUtils<Org> jdbc;
	
	@Override
	public List<Org> get() throws SQLException {
		String sql = "select id, name, description, creator, createTime from org";
		return jdbc.executeSql(sql, new OrgRowMapper());
	}

}
