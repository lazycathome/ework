package com.bloomp.account.service;

import java.util.List;

import com.bloomp.account.entity.Account;

public interface AccountService {

	long save(Account account, int type);
	
	long update(Account account);
	
	Account queryForId(long id);
	
	List<Account> queryForUserName(long userName);
	
	List<Account> queryForIds(List<Long> ids);
	
	List<Account> queryForIds(List<Long> ids, long lastQueryTime);
	
	List<Account> queryForUserNames(List<Long> userNames);
	
	Account login(long userName, String password, int type);
}
