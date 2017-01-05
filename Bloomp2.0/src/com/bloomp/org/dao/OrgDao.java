package com.bloomp.org.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.org.entity.Org;

public interface OrgDao {

	List<Org> get() throws SQLException;
	
}
