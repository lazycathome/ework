package cn.bigdb.gallery.resource.entity;

import java.util.ArrayList;
import java.util.List;

import cn.bigdb.gallery.action.entity.Action;

public class ResourceInfo {

	private long id;
	private String name;//
	private int type;// 资源类型 :0为文字，1为图片，2为视频
	private int duration;//展示时间，展示单元并列时有效，单位 秒
	private String position;//位置，相对于父级坐标
	private String region;//展示区域，空则默认继承父级展示区域
	private int beautify;//切换效果等
	private List<ResourceInfo> children = new ArrayList<ResourceInfo>(0);
	private List<Action> Maps = new ArrayList<Action>(0);
	private int state = 2;//默认值为2，目前默认是上线，1为下线，0为逻辑删除
	private long canvasId;
	private String content;
	private int sort;//一个画布里的排序
	
	private long createTime;
	
	private long updateTime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getBeautify() {
		return beautify;
	}

	public void setBeautify(int beautify) {
		this.beautify = beautify;
	}

	public List<Action> getMaps() {
		return Maps;
	}

	public void setMaps(List<Action> maps) {
		Maps = maps;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<ResourceInfo> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceInfo> children) {
		this.children = children;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public long getCanvasId() {
		return canvasId;
	}

	public void setCanvasId(long canvasId) {
		this.canvasId = canvasId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
