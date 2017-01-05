package cn.bigdb.smartscreen.model;

import java.io.Serializable;

public class EquipUpdate implements Serializable {

	/**
	 * 设备内容更新记录
	 */
	private static final long serialVersionUID = -2397182244126965414L;

	private String id;
	private  UpdateFile updateFile;//更新文件
	private EquipInfo equip;//设备
	private long updateTime;//更新时间
	private long successTime;//更新成功时间
	private int status;//状态，-1未同步，0正在同步，1同步成功，默认-1
	private String description;
	private String memo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UpdateFile getUpdateFile() {
		return updateFile;
	}
	public void setUpdateFile(UpdateFile updateFile) {
		this.updateFile = updateFile;
	}
	public EquipInfo getEquip() {
		return equip;
	}
	public void setEquip(EquipInfo equip) {
		this.equip = equip;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public long getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(long successTime) {
		this.successTime = successTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
