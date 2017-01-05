package com.bloomp.account.service;

import java.util.List;

import com.bloomp.account.entity.Login;

public interface LoginService {

	int save(Login login);
	
	int update(Login login);
	
	Login queryForId(long id);
	
	List<Login> query(long lastQueryTime);
}
