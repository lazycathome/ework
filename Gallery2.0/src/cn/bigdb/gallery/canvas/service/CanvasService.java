package cn.bigdb.gallery.canvas.service;

import java.util.List;

import cn.bigdb.gallery.canvas.entity.Canvas;

public interface CanvasService {

	long save(Canvas canvas);
	
	void update(Canvas canvas);
	
	long save(List<Canvas> canvasList);
	
	long init(long contentId);
	
	List<Canvas> getList();
	
	List<Canvas> getList(long contentId);
	
	List<Canvas> getList(List<Long> contentIdList);
}
