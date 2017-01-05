package cn.bigdb.gallery.resource.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.core.JDBCUtils;
import cn.bigdb.gallery.resource.entity.ResourceInfoCanvas;
import cn.bigdb.gallery.resource.entity.ResourceInfoCanvasRowMapper;
import cn.bigdb.gallery.utils.SqlUtils;

@Repository
public class ResourceInfoCanvasDaoImpl implements ResourceInfoCanvasDao {

	@Autowired
	private JDBCUtils<ResourceInfoCanvas> jdbc;
	
	@Override
	public void save(List<ResourceInfoCanvas> rCanvasList) throws SQLException {
		String sql = "insert into resource_info_canvas(canvas_id, resourceinfo_id, create_time) values(?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>(rCanvasList.size());
		for(int i = 0; i < rCanvasList.size(); i++){
			List<Object> list = new ArrayList<Object>(3);
			ResourceInfoCanvas rCanvas = rCanvasList.get(i);
			list.add(rCanvas.getCanvasId());
			list.add(rCanvas.getResourceInfoId());
			list.add(rCanvas.getCreateTime());
			vList.add(list);
		}
		jdbc.saveBatch(sql, vList);
	}

	@Override
	public List<ResourceInfoCanvas> get(List<Long> canvasIdList)
			throws SQLException {
		StringBuilder sql = new StringBuilder("select * from resourceinfo_canvas where canvas_id in (");
		sql.append(SqlUtils.sqlJoin(canvasIdList.size(), "?,"));
		sql.append(")");
		return jdbc.executeSql(sql.toString(), canvasIdList.toArray(new Object[canvasIdList.size()]), new ResourceInfoCanvasRowMapper());
	}

}
