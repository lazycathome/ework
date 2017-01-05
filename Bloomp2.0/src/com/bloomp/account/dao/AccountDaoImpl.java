package com.bloomp.account.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.account.entity.Account;
import com.bloomp.account.entity.AccountRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private JDBCUtils<Account> jdbc;
	
	@Override
	public long save(Account account)  throws SQLException{
		String sql = "insert into accounts(userName, password, logo, nickName, firstName, lastName, createTime, updateTime, sex, country, orgId) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object> list = new ArrayList<Object>();
		list.add(account.getUserName());
		list.add(account.getPassword());
		list.add(account.getLogo());
		list.add(account.getNickName());
		list.add(account.getFirstName());
		list.add(account.getLastName());
		list.add(account.getCreateTime());
		list.add(account.getUpdateTime());
		list.add(account.getCountry());
		list.add(account.getSex());
		list.add(account.getOrgId());
		jdbc.save(sql, list);
		long id = jdbc.queryForLong(Constants.LAST_INSERT_ID);
		return id;
	}

	@Override
	public long update(Account account) throws SQLException {
		String sql = "update accounts set logo=?, nickName=?, firstName=?, lastName=?, updateTime=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(account.getLogo());
		list.add(account.getNickName());
		list.add(account.getFirstName());
		list.add(account.getLastName());
		list.add(account.getUpdateTime());
		list.add(account.getId());
		jdbc.update(sql, list);
		return account.getId();
	}

	@Override
	public Account queryForId(long id) throws SQLException {
		String sql = "select * from accounts where id=?";
		List<Account> result = jdbc.executeSql(sql, new Object[]{id}, new AccountRowMapper());
		if(result == null || result.isEmpty())
			return new Account();
		return result.get(0);
	}

	@Override
	public List<Account> queryForIds(List<Object> ids, long lastQueryTime) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from accounts where id in(");
		for(Object id : ids){
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		if(lastQueryTime >= 1){
			sql.append(" and updateTime >= ?");
			ids.add(lastQueryTime);
		}
		List<Account> result = jdbc.executeSql(sql.toString(), ids.toArray(), new AccountRowMapper());
		if(result == null || result.isEmpty())
			return new ArrayList<Account>();
		return result;
	}

	@Override
	public Account queryForUserName(long userName) throws SQLException {
		String sql = "select * from accounts where userName=?";
		List<Account> result = jdbc.executeSql(sql, new Object[]{userName}, new AccountRowMapper());
		if(result == null || result.size() == 0)
			return new Account();
		return result.get(0);
	}

	@Override
	public List<Account> queryListForUserName(long userName) throws SQLException {
		String sql = "select * from accounts where userName like '"+userName+"%'";
		List<Account> result = jdbc.executeSql(sql, new AccountRowMapper());
		
		if(result == null || result.isEmpty())
			return new ArrayList<Account>();
		return result;
	}

	@Override
	public List<Account> queryForUserNames(List<Object> userNames)
			throws SQLException {
		StringBuilder sql = new StringBuilder("select * from accounts where userName in(");
		for(Object userName : userNames){
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		List<Account> result = jdbc.executeSql(sql.toString(), userNames.toArray(), new AccountRowMapper());
		if(result == null || result.isEmpty())
			return new ArrayList<Account>();
		return result;
	}

}
