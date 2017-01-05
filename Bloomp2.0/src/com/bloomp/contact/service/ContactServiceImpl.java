package com.bloomp.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.contact.dao.ContactDao;
import com.bloomp.contact.entity.Contact;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;
	
	@Override
	public long save(Contact contact) {
		return contactDao.save(contact);
	}

	@Override
	public List<Contact> query() {
		return contactDao.query();
	}

}
