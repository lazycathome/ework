package com.bloomp.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.category.entity.Category;
import com.bloomp.category.entity.CategoryRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private JDBCUtils<Category> jdbc;
	
	@Override
	public Category get(long id) throws SQLException {
		String sql = "select id, name, type, createTime, updateTime, creaotr from id=?";
		List<Category> results = jdbc.executeSql(sql, new CategoryRowMapper());
		return (results == null || results.size() <= 0)  ? new Category() : results.get(0);
	}

	@Override
	public List<Category> get(int type) throws SQLException {
		String sql = "select id, name, type, createTime, updateTime, creaotr from type=?";
		return  jdbc.executeSql(sql, new CategoryRowMapper());
	}

}
