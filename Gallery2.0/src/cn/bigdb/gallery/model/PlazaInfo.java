package cn.bigdb.gallery.model;

import java.util.List;

import cn.bigdb.gallery.equip.entity.EquipInfo;

public class PlazaInfo {

	private long id; // 主键ID
	private String proviance; // 省份
	private String name; // 城市
	private String city; // 广场名称
	private String address; // 广场地址
	private String phone; // 联系电话
	private String description; // 描述
	private String memo; //备注
	private List<PlazaArea> plazaAreas;
	private List<EquipInfo> equips;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getProviance() {
		return proviance;
	}
	public void setProviance(String proviance) {
		this.proviance = proviance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public List<EquipInfo> getEquips() {
		return equips;
	}
	public void setEquips(List<EquipInfo> equips) {
		this.equips = equips;
	}
	
	public List<PlazaArea> getPlazaAreas() {
		return plazaAreas;
	}
	public void setPlazaAreas(List<PlazaArea> plazaAreas) {
		this.plazaAreas = plazaAreas;
	}
	
}
