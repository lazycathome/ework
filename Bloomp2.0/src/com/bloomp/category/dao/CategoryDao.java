package com.bloomp.category.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.category.entity.Category;

public interface CategoryDao {

	Category get(long id) throws SQLException;
	
	List<Category> get(int type) throws SQLException;
	
}
