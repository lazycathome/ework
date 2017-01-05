package com.bloomp.category.service;

import java.util.List;

import com.bloomp.category.entity.Category;

public interface CategoryService {

	Category get(long id) ;
	
	List<Category> get(int type);
}
