package cn.bigdb.smartscreen.model;

import java.io.Serializable;

public class Heartbeat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7328781034316972873L;
	
	private String id;
	private long lastTime;
	private String description;
	private EquipInfo equip;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public EquipInfo getEquip() {
		return equip;
	}
	public void setEquip(EquipInfo equip) {
		this.equip = equip;
	}
	
	
}
