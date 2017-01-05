package cn.bigdb.gallery.content.entity;


public class EquipContent {

	/**
	 * 设备内容关联
	 */
	private long id;
	private String equipId; //设备
	private long contentId; //内容
	
	private int state;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
