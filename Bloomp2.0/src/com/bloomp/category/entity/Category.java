package com.bloomp.category.entity;

public class Category {

	private long id;	//分类id
	
	private String name;//分类名称
	
	private int type;//分类的业务类型

	private long createTime;//分类创建时间
	
	private long updateTime;//分类更新时间
	
	private long caretor;//分类创建人

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public long getCaretor() {
		return caretor;
	}

	public void setCaretor(long caretor) {
		this.caretor = caretor;
	}
	
}
