package com.bloomp.account.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.account.dao.AccountDao;
import com.bloomp.account.entity.Account;
import com.bloomp.account.entity.Login;
import com.bloomp.utils.ChatOnlineManager;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public long save(Account account, int type) {
		if(account != null){
			try {
				Account tAccount = accountDao.queryForUserName(account.getUserName());
				if(tAccount.isEmpty()){
					long id = accountDao.save(account);
					Login login  = new Login();
					login.setId(id);
					login.setType(type);
					login.setLoginTime(account.getCreateTime());
					loginService.save(login);
					
	//				ChatOnlineManager.getInstance().put(id, login);
					return id;
				}else{
					return -1;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
		
	}

	@Override
	public long update(Account account) {
		if(!account.isEmpty()){
			long time = System.currentTimeMillis();
			account.setUpdateTime(time);
			try {
				return accountDao.update(account);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public Account queryForId(long id) {
		try {
			return accountDao.queryForId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Account();
	}

	@Override
	public List<Account> queryForIds(List<Long> ids) {
		try {
			List<Object> tIds = new ArrayList<Object>();
			for(long id : ids){
				tIds.add(id);
			}
			List<Account> accounts = accountDao.queryForIds(tIds, 0);
			for(Account account : accounts){
				account.setPassword(null);
			}
			return accounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Account>();
	}

	@Override
	public Account login(long userName, String password, int type) {
		try {
			Account account = accountDao.queryForUserName(userName);
			if(password.equals(account.getPassword())){
				return account;
			}else{
				return new Account();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Account();
	}

	@Override
	public List<Account> queryForUserName(long userName) {
		try {
			return accountDao.queryListForUserName(userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Account>();
	}

	@Override
	public List<Account> queryForUserNames(List<Long> userNames) {
		try {
			List<Object> tIds = new ArrayList<Object>();
			for(long id : userNames){
				tIds.add(id);
			}
			List<Account> accounts = accountDao.queryForUserNames(tIds);
			for(Account account : accounts){
				account.setPassword(null);
			}
			return accounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Account>();
	}

	@Override
	public List<Account> queryForIds(List<Long> ids, long lastQueryTime) {
		try {
			List<Object> tIds = new ArrayList<Object>();
			for(long id : ids){
				tIds.add(id);
			}
			List<Account> accounts = accountDao.queryForIds(tIds, lastQueryTime);
			for(Account account : accounts){
				account.setPassword(null);
			}
			return accounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Account>();
	}

}
