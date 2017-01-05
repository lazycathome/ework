package cn.bigdb.gallery.content.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EquipContentRowMapper implements RowMapper<EquipContent> {

	@Override
	public EquipContent mapRow(ResultSet rs, int i) throws SQLException {
		EquipContent eContent = new EquipContent();
		eContent.setContentId(rs.getLong("content_id"));
		eContent.setEquipId(rs.getString("equip_id"));
		eContent.setId(rs.getLong("id"));
		eContent.setState(rs.getInt("state"));
		return eContent;
	}

}
