package com.bloomp.task.entity;

public class TaskOwner {

	private long id;
	
	private long accountId;
	
	private long taskId;
	
	private int role;
	
	private long createTime;
	
	private long updateTime;
	
	private int state = 1;//任务的状态，0不可用，1参与者未确认，2参与者接受，3参与者拒绝，4任务发布者未确认，5任务发布者接受，6任务发布者拒绝，默认为1
	
	public enum Rule{
		observer("3"),
		creaotr("2"),
		exceutor("1");
		
		private String value;
		public int getValue(){
			return Integer.parseInt(value);
		}
		
		Rule(String value){
			this.value = value;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	
	public boolean isEmpty(){
		return id <= 0;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
