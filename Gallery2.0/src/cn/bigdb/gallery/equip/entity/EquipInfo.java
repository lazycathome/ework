package cn.bigdb.gallery.equip.entity;

import java.util.ArrayList;
import java.util.List;

import cn.bigdb.gallery.content.entity.Content;

public class EquipInfo{

	private String id;//主键ID
	private String code;//设备编码
	private String name;//设备名称
	private long plazaId = 0;//广场
	private long areaId = 0; //区域
	private String location; //设备位置
	private int width; //设备宽度
	private int height; //设备高度
	private int category; //设备类型：宽屏或者竖屏
	private String photo; //设备照片
	private int status = 0; //状态
	private long createTime; //设备添加时间
	private String startTime; //开机时间
	private String closeTime; //关机时间
	private long heartbeatTime; //心跳频率时间
	private long updateTime; //更新频率时间
	private String mobile; //手机
	private String ip; //当前IP地址
	private String description;//说明描述
	private String memo;//备注
	private List<Heartbeat> heartbeats = new ArrayList<Heartbeat>(0);
	private List<Content> data = new ArrayList<Content>(0);
	
	
 	public enum Category{
 		horizontal(1),
 		vertical(2);
 		
 		private int value;
 		
 		private Category(int value) {    //    必须是private的，否则编译错误
 	        this.value = value;
 	    }
 		
 		public static Category valueOf(int value) {    //    手写的从int到enum的转换函数
 	        switch (value) {
 	        case 1:
 	            return horizontal;
 	        case 2:
 	            return vertical;
 	        default:
 	            return null;
 	        }
 	    }

 	    public int value() {
 	        return this.value;
 	    }
 	}
	public List<Content> getData() {
		return data;
	}
	public void setData(List<Content> data) {
		this.data = data;
	}
	public List<Heartbeat> getHeartbeats() {
		return heartbeats;
	}
	public void setHeartbeats(List<Heartbeat> heartbeats) {
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
	
	public long getPlazaId() {
		return plazaId;
	}
	public void setPlazaId(long plazaId) {
		this.plazaId = plazaId;
	}
	public long getAreaId() {
		return areaId;
	}
	public void setAreaId(long areaId) {
		this.areaId = areaId;
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
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
	
	public boolean isEmpty(){
		return (id == null || "".equals(id.trim())) ? true : false;
	}
}
