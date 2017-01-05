package com.bloomp.contact.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContactRowMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int i) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getLong("id"));
		contact.setCreateTime(rs.getLong("createTime"));
		contact.setDescription(rs.getString("description"));
		contact.setEmail(rs.getString("email"));
		contact.setName(rs.getString("name"));
		contact.setStrCreateTime(new Date(contact.getCreateTime()));
		return contact;
	}
	
}
