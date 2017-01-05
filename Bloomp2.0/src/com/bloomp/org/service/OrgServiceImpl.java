package com.bloomp.org.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloomp.org.dao.OrgDao;
import com.bloomp.org.entity.Org;

@Service
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgDao orgDao;
	
	@Override
	public List<Org> get() {
		try {
			return orgDao.get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Org>(0);
	}

}
