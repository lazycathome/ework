package cn.bigdb.gallery.resource.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResourceInfoRowMapper implements RowMapper<ResourceInfo> {

	@Override
	public ResourceInfo mapRow(ResultSet rs, int i) throws SQLException {
		ResourceInfo resourceInfo = new ResourceInfo();
		resourceInfo.setBeautify(rs.getInt("beautify"));
		resourceInfo.setDuration(rs.getInt("duration"));
		resourceInfo.setId(rs.getLong("id"));
		resourceInfo.setPosition(rs.getString("position"));
		resourceInfo.setRegion(rs.getString("region"));
		resourceInfo.setState(rs.getInt("state"));
		resourceInfo.setName(rs.getString("title"));
		resourceInfo.setType(rs.getInt("type"));
		resourceInfo.setCreateTime(rs.getLong("create_time"));
		resourceInfo.setUpdateTime(rs.getLong("update_time"));
		resourceInfo.setContent(rs.getString("content"));
		return resourceInfo;
	}

}
