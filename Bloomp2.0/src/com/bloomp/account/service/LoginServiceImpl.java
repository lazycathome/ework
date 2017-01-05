package com.bloomp.account.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.account.dao.LoginDao;
import com.bloomp.account.entity.Login;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public int save(Login login) {
		try {
			return loginDao.save(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int update(Login login) {
		try {
			return loginDao.update(login);
		} catch (SQLException e) {
			try {
				return loginDao.save(login);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public Login queryForId(long id) {
		try {
			return loginDao.queryForId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Login();
	}

	@Override
	public List<Login> query(long lastLoginTime) {
		try {
			return loginDao.query(lastLoginTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Login>();
	}

}
