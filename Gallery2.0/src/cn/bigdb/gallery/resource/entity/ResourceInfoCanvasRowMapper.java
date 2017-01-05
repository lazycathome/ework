package cn.bigdb.gallery.resource.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResourceInfoCanvasRowMapper implements RowMapper<ResourceInfoCanvas> {

	@Override
	public ResourceInfoCanvas mapRow(ResultSet rs, int i) throws SQLException {
		ResourceInfoCanvas  rCanvas = new ResourceInfoCanvas();
		rCanvas.setCanvasId(rs.getLong("canvas_id"));
		rCanvas.setCreateTime(rs.getLong("create_time"));
		rCanvas.setId(rs.getLong("id"));
		rCanvas.setResourceInfoId(rs.getLong("resourceinfo_id"));
		return rCanvas;
	}

}
