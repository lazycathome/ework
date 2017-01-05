package cn.bigdb.smartscreen.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PlazaArea implements Serializable {

	/**
	 * 广场区域
	 */
	private static final long serialVersionUID = -7478038396408117367L;

	private String id;
	private String name;
	private String description;
	private String memo;
	private PlazaInfo plazaInfo;
	private Set<EquipInfo> equips = new HashSet<EquipInfo>(0);
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public Set<EquipInfo> getEquips() {
		return equips;
	}
	public void setEquips(Set<EquipInfo> equips) {
		this.equips = equips;
	}
	public PlazaInfo getPlazaInfo() {
		return plazaInfo;
	}
	public void setPlazaInfo(PlazaInfo plazaInfo) {
		this.plazaInfo = plazaInfo;
	}
	
}
