package com.bloomp.task.entity;

import java.util.List;

public class TaskBusiness {

	private long taskId;
	
	private int pMin;
	
	private int pMax;
	
	private List<String> pics;
	
	private String pic;
	
	private int money;
	
	private long createTime;
	
	private long updateTime;
	
	private int range;
	
	private int rangeType;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public int getpMin() {
		return pMin;
	}

	public void setpMin(int pMin) {
		this.pMin = pMin;
	}

	public int getpMax() {
		return pMax;
	}

	public void setpMax(int pMax) {
		this.pMax = pMax;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getRangeType() {
		return rangeType;
	}

	public void setRangeType(int rangeType) {
		this.rangeType = rangeType;
	}
	
}
