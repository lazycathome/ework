package com.bloomp.org.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrgRowMapper implements RowMapper<Org> {

	@Override
	public Org mapRow(ResultSet rs, int i) throws SQLException {
		Org org = new Org();
		org.setCreaotr(rs.getLong("creaotr"));
		org.setCreateTime(rs.getLong("createTime"));
		org.setId(rs.getLong("id"));
		org.setName(rs.getString("name"));
		return org;
	}

}
