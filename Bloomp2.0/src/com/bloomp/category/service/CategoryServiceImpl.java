package com.bloomp.category.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloomp.category.dao.CategoryDao;
import com.bloomp.category.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public Category get(long id) {
		if(id >= 1){ 
			try {
				return categoryDao.get(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Category();
	}

	@Override
	public List<Category> get(int type) {
		if(type >= 1){ 
			try {
				return categoryDao.get(type);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ArrayList<Category>(0);
	}

}
