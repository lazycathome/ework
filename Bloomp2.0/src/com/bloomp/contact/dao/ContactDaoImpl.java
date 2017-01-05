package com.bloomp.contact.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bloomp.Constants;
import com.bloomp.contact.entity.Contact;
import com.bloomp.contact.entity.ContactRowMapper;
import com.bloomp.core.JDBCUtils;

@Repository
public class ContactDaoImpl implements ContactDao {

	@Autowired
	private JDBCUtils<Contact> jdbc;
	
	@Override
	public long save(Contact contact) {
		String sql = "insert into contact(name, email, description, createTime) values (?, ?, ?, ?)";
		List<Object> list = new ArrayList<Object>();
		list.add(contact.getName());
		list.add(contact.getEmail());
		list.add(contact.getDescription());
		list.add(System.currentTimeMillis());
		try {
			jdbc.save(sql, list);
			return jdbc.queryForLong(Constants.LAST_INSERT_ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Contact> query() {
		String sql = "select * from contact";
		List<Contact> friends = null;
		try {
			friends = jdbc.executeSql(sql, null, new ContactRowMapper());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(friends == null) 
			return new ArrayList<Contact>();;
		return friends;
	}

}
