package com.bloomp.account.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoginRowMapper implements  RowMapper<Login> {

	@Override
	public Login mapRow(ResultSet rs, int i) throws SQLException {
		Login login = new Login();
		login.setId(rs.getLong("id"));
		login.setType(rs.getInt("type"));
		login.setLoginTime(rs.getLong("loginTime"));
		return login;
	}

}
