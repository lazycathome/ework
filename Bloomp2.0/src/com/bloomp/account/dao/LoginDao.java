package com.bloomp.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.bloomp.account.entity.Login;

public interface LoginDao {

	int save(Login login) throws SQLException;
	
	int update(Login login) throws SQLException;
	
	Login queryForId(long id) throws SQLException;
	
	List<Login> query(long lastLoginTime) throws SQLException;
}
