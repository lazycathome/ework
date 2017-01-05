package cn.bigdb.gallery.model;

import java.util.ArrayList;
import java.util.List;

import cn.bigdb.gallery.equip.entity.EquipInfo;

public class PlazaArea{

	private long id;
	private String name;
	private String description;
	private String memo;
	private long plazaInfoId;
	private List<EquipInfo> equips = new ArrayList<EquipInfo>(0);
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
	public long getPlazaInfoId() {
		return plazaInfoId;
	}
	public void setPlazaInfoId(long plazaInfoId) {
		this.plazaInfoId = plazaInfoId;
	}
	
}
