package com.bloomp.group.service;

import java.util.List;

import com.bloomp.group.entity.Group;

public interface GroupService {

	long save(String name, long creator);
	
	int update(long id, String name);
	
	void delete(long id);
	
	List<Group> get(long creator);
}
