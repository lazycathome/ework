package cn.bigdb.smartscreen.model;

import java.io.Serializable;

public class EquipContent implements Serializable {

	/**
	 * 设备内容关联表
	 */
	private static final long serialVersionUID = -4331777042081673786L;

	private String id;
	private EquipInfo equip; //设备
	private Content content; //内容
	private String description;
	private String memo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public EquipInfo getEquip() {
		return equip;
	}
	public void setEquip(EquipInfo equip) {
		this.equip = equip;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
