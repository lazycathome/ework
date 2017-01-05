package com.bloomp.contact.service;

import java.util.List;

import com.bloomp.contact.entity.Contact;

public interface ContactService {

	long save(Contact contact);
	
	List<Contact> query();
}
