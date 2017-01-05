package com.bloomp.contact.dao;

import java.util.List;

import com.bloomp.contact.entity.Contact;

public interface ContactDao {

	long save(Contact contact);
	
	List<Contact> query();
}
