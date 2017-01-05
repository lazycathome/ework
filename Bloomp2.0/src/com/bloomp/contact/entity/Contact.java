package com.bloomp.contact.entity;

import java.sql.Date;

public class Contact {

	private long id;
	
	private String name;
	
	private String email;
	
	private String description;
	
	private long createTime;
	
	private Date strCreateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public Date getStrCreateTime() {
		return strCreateTime;
	}

	public void setStrCreateTime(Date strCreateTime) {
		this.strCreateTime = strCreateTime;
	}
	
}
