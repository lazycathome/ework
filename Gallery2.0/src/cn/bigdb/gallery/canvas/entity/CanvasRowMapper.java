package cn.bigdb.gallery.canvas.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CanvasRowMapper implements RowMapper<Canvas> {

	@Override
	public Canvas mapRow(ResultSet rs, int i) throws SQLException {
		Canvas canvas = new Canvas();
		canvas.setBackcolor(rs.getString("backcolor"));
		canvas.setBackground(rs.getString("background"));
		canvas.setBacksound(rs.getString("backsound"));
		canvas.setContentId(rs.getLong("content_id"));
		canvas.setDuration(rs.getInt("duration"));
		canvas.setId(rs.getLong("id"));
		canvas.setRepeat(rs.getString("repeat"));
		canvas.setPageName(rs.getString("title"));
		canvas.setState(rs.getInt("state"));
		return canvas;
	}

}
