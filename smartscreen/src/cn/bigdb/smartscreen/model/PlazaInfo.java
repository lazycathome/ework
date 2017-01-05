package cn.bigdb.smartscreen.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PlazaInfo implements Serializable {

	/**
	 * 广场信息表
	 */
	private static final long serialVersionUID = -5363943152794028647L;

	private String id; // 主键ID
	private String capital; // 省份
	private String name; // 城市
	private String city; // 广场名称
	private String address; // 广场地址
	private String phone; // 联系电话
	private String description; // 描述
	private String memo; //备注
	private Set<PlazaArea> plazaAreas;
	private Set<EquipInfo> equips;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
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
	public Set<EquipInfo> getEquips() {
		return equips;
	}
	public void setEquips(Set<EquipInfo> equips) {
		this.equips = equips;
	}
	
	public Set<PlazaArea> getPlazaAreas() {
		return plazaAreas;
	}
	public void setPlazaAreas(Set<PlazaArea> plazaAreas) {
		this.plazaAreas = plazaAreas;
	}
	
}
