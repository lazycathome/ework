package com.bloomp.account.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements  RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int i) throws SQLException {
		Account account = new Account();
		account.setId(rs.getLong("id"));
		account.setLogo(rs.getString("logo"));
		account.setCreateTime(rs.getLong("createTime"));
		account.setNickName(rs.getString("nickName"));
		account.setFirstName(rs.getString("firstName"));
		account.setLastName(rs.getString("lastName"));
		account.setPassword(rs.getString("password"));
		account.setUserName(rs.getLong("userName"));
		account.setUpdateTime(rs.getLong("updateTime"));
		account.setCountry(rs.getInt("country"));
		account.setSex(rs.getInt("sex"));
		account.setOrgId(rs.getLong("orgId"));
		return account;
	}

}
