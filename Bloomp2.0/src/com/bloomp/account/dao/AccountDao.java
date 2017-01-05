package com.bloomp.account.dao;


import java.sql.SQLException;
import java.util.List;

import com.bloomp.account.entity.Account;

public interface AccountDao {

	long save(Account account) throws SQLException;
	
	long update(Account account) throws SQLException;
	
	Account queryForId(long id) throws SQLException;
	
	List<Account> queryForIds(List<Object> ids, long lastQueryTime) throws SQLException;
	
	public List<Account> queryForUserNames(List<Object> userNames) throws SQLException;
	
	Account queryForUserName(long userName) throws SQLException;
	
	List<Account> queryListForUserName(long userName) throws SQLException;
}
