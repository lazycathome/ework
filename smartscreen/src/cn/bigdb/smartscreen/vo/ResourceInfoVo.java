package cn.bigdb.smartscreen.vo;

import java.io.Serializable;


public class ResourceInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8631946670278401661L;

	private String id;//资源id
	
	private String pic;//图片地址
	
	private int duration;//该资源播放时间
	
	private String video;//视频地址
	
	private int mode;//1 图片 2 视频 3 混合 4 上 5 下

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	
	
}
