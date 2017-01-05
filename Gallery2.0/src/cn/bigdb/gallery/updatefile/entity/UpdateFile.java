package cn.bigdb.gallery.updatefile.entity;


public class UpdateFile {

	private long id;//主键ID
	private long createTime;//更新时间
	private String fileUrl;//更新文件的url
	private String md5code;//md5文件校验码

	public long getId() {
		return id;
	}
	public void setId(long id) {
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

}
