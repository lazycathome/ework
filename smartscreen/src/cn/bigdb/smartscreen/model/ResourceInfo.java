package cn.bigdb.smartscreen.model;

import java.io.Serializable;

public class ResourceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6425218618314013266L;

	private String id;
	private String category;//资源类型 1为图片，2为视频
	private String pic; //图片地址
	private String video;//视频地址
	private int size; //图片大小 单位k
	private int length; //视频大小，单位k
	private String videoTime;//视频播放时长
	private String description;
	private int time;
	private long lastTime;
	private int mode;//1 图片 2 视频 3 混合中 4 混合上 5 混合下
	private ContentResource contentResource;
	
	
	public ContentResource getContentResource() {
		return contentResource;
	}
	public void setContentResource(ContentResource contentResource) {
		this.contentResource = contentResource;
	}

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
}
