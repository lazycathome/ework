package cn.bigdb.gallery.updatefile.entity;


public class EquipUpdate{

	private long id;
	private long updateFileId;//更新文件
	private String equipId;//设备
	private long updateTime;//更新时间
	private long successTime;//更新成功时间
	private int status;//状态：0未同步，1正在同步，2同步成功，默认为0
	private String description;
	private String memo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUpdateFileId() {
		return updateFileId;
	}
	public void setUpdateFileId(long updateFileId) {
		this.updateFileId = updateFileId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
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
