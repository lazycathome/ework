package cn.bigdb.gallery.canvas.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bigdb.gallery.canvas.entity.Canvas;

public interface CanvasDao {
	
	void update(Canvas canvas) throws SQLException;
	
	long save(List<Canvas> canvasList) throws SQLException;
	
	List<Canvas> getList();
	
	List<Canvas> getList(long contentId) throws SQLException;
	
	List<Canvas> getList(List<Long> contentIdList) throws SQLException;
	
}
