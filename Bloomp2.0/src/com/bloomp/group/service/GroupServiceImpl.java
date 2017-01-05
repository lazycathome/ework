package com.bloomp.group.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.Code;
import com.bloomp.group.dao.GroupDao;
import com.bloomp.group.entity.Group;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDao groupDao;
	
	@Override
	@Transactional
	public long save(String name, long creator) {
		Group group  = new Group();
		long time = System.currentTimeMillis();
		group.setCreateTime(time);
		group.setCreator(creator);
		group.setName(name);
		group.setState(1);
		group.setUpdateTime(time);
		try {
			return groupDao.save(group);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}

	@Override
	@Transactional
	public int update(long id, String name) {
		try {
			return groupDao.update(id, name, System.currentTimeMillis());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}

	@Override
	@Transactional
	public void delete(long id) {
		try {
			groupDao.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Group> get(long creator) {
		try {
			return groupDao.get(creator);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Group>(0);
	}

}
