package cn.bigdb.smartscreen.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EquipInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1200863728113717062L;

	private String id;//主键ID
	private String code;//设备编码
	private String name;//设备名称
	private PlazaInfo plaza;//广场
	private PlazaArea area; //区域
	private String location; //设备位置
	private int width; //宽
	private int height; //高
	private String category; //设备类型：宽屏、竖屏
	private String photo; //设备照片
	private int status = -1; //状态
	private long createTime; //设备添加时间
	private String startTime; //开机时间
	private String closeTime; //关机时间
	private long heartbeatTime; //心跳频率时间
	private long updateTime; //更新频率时间
	private String mobile; //手机
	private String ip; //当前IP地址
	private String description;//说明描述
	private String memo;//备注
	private Set<Heartbeat> heartbeats = new HashSet<Heartbeat>(0);
	private Set<EquipContent> equipContents = new HashSet<EquipContent>(0);
	private Set<EquipUpdate> equipUpdates = new HashSet<EquipUpdate>(0);
	
	public Set<EquipUpdate> getEquipUpdates() {
		return equipUpdates;
	}
	public void setEquipUpdates(Set<EquipUpdate> equipUpdates) {
		this.equipUpdates = equipUpdates;
	}
	public Set<EquipContent> getEquipContents() {
		return equipContents;
	}
	public void setEquipContents(Set<EquipContent> equipContents) {
		this.equipContents = equipContents;
	}
	public Set<Heartbeat> getHeartbeats() {
		return heartbeats;
	}
	public void setHeartbeats(Set<Heartbeat> heartbeats) {
		this.heartbeats = heartbeats;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PlazaInfo getPlaza() {
		return plaza;
	}
	public void setPlaza(PlazaInfo plaza) {
		this.plaza = plaza;
	}
	public PlazaArea getArea() {
		return area;
	}
	public void setArea(PlazaArea area) {
		this.area = area;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public long getHeartbeatTime() {
		return heartbeatTime;
	}
	public void setHeartbeatTime(long heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	
	
}
