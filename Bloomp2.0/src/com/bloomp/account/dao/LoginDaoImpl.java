package com.bloomp.account.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.account.entity.Login;
import com.bloomp.account.entity.LoginRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private JDBCUtils<Login> jdbc;
	
	@Override
	public int save(Login login) throws SQLException {
		String sql  = "insert into login(id, loginTime, type, deviceToken) values (?, ?, ?, ?)";
		List<Object> list = new ArrayList<Object>();
		list.add(login.getId());
		list.add(login.getLoginTime());
		list.add(login.getType());
		list.add(login.getDeviceToken());
		jdbc.save(sql, list);
		return 1;
	}

	@Override
	public Login queryForId(long id) throws SQLException {
		String sql = "select * from login where id=?";
		List<Login> logins = jdbc.executeSql(sql, new Object[]{id}, new LoginRowMapper());
		if(logins != null && logins.size() > 0){
			return logins.get(0);
		}
		return new Login();
	}

	@Override
	public List<Login> query(long lastLoginTime) throws SQLException {
		String sql = "select * from login where loginTime >=?";
		List<Login> logins = jdbc.executeSql(sql, new Object[]{lastLoginTime}, new LoginRowMapper());
		if(logins != null ){
			return logins;
		}
		return new ArrayList<Login>();
	}

	@Override
	public int update(Login login) throws SQLException {
		String sql = "update login set loginTime = ?, deviceToken = ?, type = ? where id = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(login.getLoginTime());
		list.add(login.getDeviceToken());
		list.add(login.getType());
		list.add(login.getId());
		jdbc.update(sql, list);
		return 1;
	}

}
