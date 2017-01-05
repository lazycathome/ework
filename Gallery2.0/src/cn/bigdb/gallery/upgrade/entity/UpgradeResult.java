package cn.bigdb.gallery.upgrade.entity;

public class UpgradeResult {

	private int code;
	
	private String message;
	
	private String url;
	
	private long lastQueryTime;
	
	private String equipId;
	
	private String md5Code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getLastQueryTime() {
		return lastQueryTime;
	}

	public void setLastQueryTime(long lastQueryTime) {
		this.lastQueryTime = lastQueryTime;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}
	
}
