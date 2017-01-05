package com.bloomp.group.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GroupRowMapper implements RowMapper<Group> {

	@Override
	public Group mapRow(ResultSet rs, int i) throws SQLException {
		Group group = new Group();
		group.setCreateTime(rs.getLong("createTime"));
		group.setCreator(rs.getLong("creator"));
		group.setId(rs.getLong("id"));
		group.setName(rs.getString("name"));
		group.setState(rs.getInt("state"));
		group.setUpdateTime(rs.getLong("updateTime"));
		return group;
	}

}
