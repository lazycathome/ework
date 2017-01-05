package cn.bigdb.smartscreen.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Content implements Serializable {
	
	private String id; //内容ID
	private String category; //类型：宽屏、竖屏
	private String type; //内容类型：单屏、画中画
	private String author; //作者
	private int showlevel; //展示等级
	private String name; //内容标题
	private String label; //内容标签
	private String audioUrl; //背景音乐地址
	private int status; //状态：0暂停和1开启
	private int time; //播放时长 单位s
	private int playCount; //播放总数
	private int playType; //播放类型：1为随机，2为顺序
	private long createTime; //添加时间
	private long lastTime; //最后更新时间
	private String deleted; //逻辑删除
	private String description; //描述
	private String memo; //备注
	private String templateInfo;
	private Set<ContentResource> contentResources;
	private List<ContentResource> crs;
	private Set<EquipContent> equipContents = new HashSet<EquipContent>(0);
	
	public Set<EquipContent> getEquipContents() {
		return equipContents;
	}
	public void setEquipContents(Set<EquipContent> equipContents) {
		this.equipContents = equipContents;
	}
	public Set<ContentResource> getContentResources() {
		return contentResources;
	}
	public void setContentResources(Set<ContentResource> contentResources) {
		this.contentResources = contentResources;
	}
	
	public String getTemplateInfo() {
		return templateInfo;
	}
	public void setTemplateInfo(String templateInfo) {
		this.templateInfo = templateInfo;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getShowlevel() {
		return showlevel;
	}
	public void setShowlevel(int showlevel) {
		this.showlevel = showlevel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	public int getPlayType() {
		return playType;
	}
	public void setPlayType(int playType) {
		this.playType = playType;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<ContentResource> getCrs() {
		return crs;
	}
	public void setCrs(List<ContentResource> crs) {
		this.crs = crs;
	}
	
}
