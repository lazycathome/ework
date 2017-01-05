package com.bloomp.api.contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bloomp.contact.entity.Contact;
import com.bloomp.contact.service.ContactService;

import cn.jedisoft.framework.annotations.FormParam;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

@Path("/api/bloomp/contact/?$")
public class ContactManager {

	@Autowired
	private ContactService contactService;
	
	@POST
	public ApiResult create(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("description") String description){
		Map<String, Object> result = new HashMap<String, Object>();
		Contact contact = new Contact();
		contact.setEmail(email);
		contact.setName(name);
		contact.setDescription(description);
		long code = contactService.save(contact);
		code = code >=1 ? 0 : -1;
		result.put("code", code);
		return new JsonResult(result);
	}
	
	@GET
	public ApiResult query(){
		List<Contact> datas = contactService.query();
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("code", 0);
		results.put("results", datas);
		return new JsonResult(results);
	}
	
}
