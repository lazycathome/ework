package cn.bigdb.gallery.resource.entity;

public class ResourceInfoCanvas {

	private long id;//主键id
	
	private long canvasId;//画布id
	
	private long resourceInfoId;//资源id
	
	private long createTime;//创建时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCanvasId() {
		return canvasId;
	}

	public void setCanvasId(long canvasId) {
		this.canvasId = canvasId;
	}

	public long getResourceInfoId() {
		return resourceInfoId;
	}

	public void setResourceInfoId(long resourceInfoId) {
		this.resourceInfoId = resourceInfoId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
}
