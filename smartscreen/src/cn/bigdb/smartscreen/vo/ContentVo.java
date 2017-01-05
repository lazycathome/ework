package cn.bigdb.smartscreen.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContentVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1273512647079142610L;

	private String id;//用来做页面切换定位的
	
	private String audio;
	
	private int duration;
	
	private List<ResourceInfoVo> items = new ArrayList<ResourceInfoVo>() ;//资源集合

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<ResourceInfoVo> getItems() {
		return items;
	}

	public void setItems(List<ResourceInfoVo> items) {
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
