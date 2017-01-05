package com.bloomp.category.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CategoryRowMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int i) throws SQLException {
		Category category = new Category();
		category.setCaretor(rs.getLong("caretor"));
		category.setCreateTime(rs.getLong("createTime"));
		category.setId(rs.getLong("id"));
		category.setName(rs.getString("name"));
		category.setType(rs.getInt("type"));
		category.setUpdateTime(rs.getLong("updateTime"));
		return category;
	}

}
