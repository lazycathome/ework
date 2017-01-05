package cn.bigdb.gallery.equip.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EquipInfoRowMapper implements RowMapper<EquipInfo> {

	@Override
	public EquipInfo mapRow(ResultSet rs, int i) throws SQLException {
		EquipInfo equipInfo = new EquipInfo();
		equipInfo.setAreaId(rs.getLong("area_id"));
		equipInfo.setCategory(rs.getInt("category"));
		equipInfo.setCloseTime(rs.getString("close_time"));
		equipInfo.setCode(rs.getString("code"));
		equipInfo.setCreateTime(rs.getLong("create_time"));
		equipInfo.setDescription(rs.getString("description"));
		equipInfo.setHeartbeatTime(rs.getLong("heartbeat_time"));
		equipInfo.setHeight(rs.getInt("height"));
		equipInfo.setId(rs.getString("id"));
		equipInfo.setIp(rs.getString("ip"));
		equipInfo.setLocation(rs.getString("location"));
		equipInfo.setMemo("memo");
		equipInfo.setMobile(rs.getString("mobile"));
		equipInfo.setPhoto(rs.getString("photo"));
		equipInfo.setPlazaId(rs.getLong("plaza_id"));
		equipInfo.setStartTime(rs.getString("start_time"));
		equipInfo.setStatus(rs.getInt("status"));
		equipInfo.setUpdateTime(rs.getLong("update_time"));
		equipInfo.setWidth(rs.getInt("width"));
		return equipInfo;
	}

}
