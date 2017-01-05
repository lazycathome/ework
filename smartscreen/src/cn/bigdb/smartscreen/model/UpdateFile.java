package cn.bigdb.smartscreen.model;

import java.io.Serializable;

public class UpdateFile implements Serializable {

	/**
	 * 文件更新内容
	 */
	private static final long serialVersionUID = 3020052886627341585L;

	private String id;//主键ID
	private long createTime;//更新时间
	private String fileUrl;//更新文件的url
	private String md5code;//md5文件校验码
	private String description;//更新描述
	private EquipUpdate equipUpdate;
	
	public EquipUpdate getEquipUpdate() {
		return equipUpdate;
	}
	public void setEquipUpdate(EquipUpdate equipUpdate) {
		this.equipUpdate = equipUpdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getMd5code() {
		return md5code;
	}
	public void setMd5code(String md5code) {
		this.md5code = md5code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
