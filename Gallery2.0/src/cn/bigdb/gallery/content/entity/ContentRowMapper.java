package cn.bigdb.gallery.content.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContentRowMapper implements RowMapper<Content> {

	@Override
	public Content mapRow(ResultSet rs, int i) throws SQLException {
		Content content = new Content();
		content.setCreateTime(rs.getLong("create_time"));
		content.setCreator(rs.getLong("creator"));
		content.setDesc(rs.getString("desc"));
		content.setDuration(rs.getInt("duration"));
		content.setId(rs.getLong("id"));
		content.setsShowDays(rs.getString("showdays"));
		content.setState(rs.getInt("state"));
		content.setsWindow(rs.getString("window"));
		content.setTitle(rs.getString("title"));
		content.setUpdateTime(rs.getLong("update_time"));
		return content;
	}

}
