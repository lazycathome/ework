package cn.bigdb.smartscreen.vo;

import java.io.Serializable;

public class UpgradeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7493307680455696690L;

	private String equipId;//设备id
	
	private long updateTime;//更新时间
	
	private String code;//更新包状态码
	
	private String md5Code;//升级包的md5码
	
	private String zipUrl;//升级包下载地址

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

	public String getZipUrl() {
		return zipUrl;
	}

	public void setZipUrl(String zipUrl) {
		this.zipUrl = zipUrl;
	}
	
}
