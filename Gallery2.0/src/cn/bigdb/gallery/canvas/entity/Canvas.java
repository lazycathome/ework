package cn.bigdb.gallery.canvas.entity;

import java.util.ArrayList;
import java.util.List;

import cn.bigdb.gallery.resource.entity.ResourceInfo;

public class Canvas {

	private long id;
	
	private String pageName="";
	
	private int duration;
	
	private String background="";
	
	private String repeat="";
	
	private String backsound="";
	
	private String backcolor="";
	
	private int state;
	
	private long contentId;//内容id

	private List<ResourceInfo> props = new ArrayList<ResourceInfo>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getBacksound() {
		return backsound;
	}

	public void setBacksound(String backsound) {
		this.backsound = backsound;
	}

	public String getBackcolor() {
		return backcolor;
	}

	public void setBackcolor(String backcolor) {
		this.backcolor = backcolor;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public List<ResourceInfo> getProps() {
		return props;
	}

	public void setProps(List<ResourceInfo> props) {
		this.props = props;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	
}
