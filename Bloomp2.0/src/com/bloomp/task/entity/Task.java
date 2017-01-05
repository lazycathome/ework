package com.bloomp.task.entity;

import com.bloomp.account.entity.Account;

public class Task {

	private long id;
	
	private String subject;
	
	private String description;
	
	private String logo;
	
	private long expireTime;
	
	private long clock;
	
	private long creator;
	
	private int state;
	
	private long createTime;
	
	private long updateTime;
	
	private long exceutor;
	
	private String observers;
	
	private Account account;
	
	private long categoryId;
	
	public enum State{
		refuse("4"),
		expire("3"),
		done("2"),
		def("1");
		
		private String value;
		public int getValue(){
			return Integer.parseInt(value);
		}
		
		State(String value){
			this.value = value;
		}
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public long getClock() {
		return clock;
	}

	public void setClock(long clock) {
		this.clock = clock;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	public boolean isEmpty(){
		return id <= 0;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public long getExceutor() {
		return exceutor;
	}

	public void setExceutor(long exceutor) {
		this.exceutor = exceutor;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getObservers() {
		return observers;
	}

	public void setObservers(String observers) {
		this.observers = observers;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

}
