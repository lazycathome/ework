package cn.bigdb.gallery.resource.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bigdb.gallery.resource.entity.ResourceInfoCanvas;

public interface ResourceInfoCanvasDao {

	void save(List<ResourceInfoCanvas> rCanvasList) throws SQLException;
	
	List<ResourceInfoCanvas> get(List<Long> canvasIdList) throws SQLException;
}
