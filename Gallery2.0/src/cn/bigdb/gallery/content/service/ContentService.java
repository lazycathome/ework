package cn.bigdb.gallery.content.service;

import java.sql.SQLException;
import java.util.List;

import cn.bigdb.gallery.content.entity.Content;
import cn.bigdb.gallery.core.Page;

public interface ContentService {

	long save(String title, String desc, int duration, int height, int width, long creator, String... showdays);
	
	Page<Content> getList(long creator, String equipId, int pageSize, int pageNo);
	
	List<Content> getList(String equipId);
	
	void update(String title, String desc, int duration, int height, int width, long creator, long id, String... showdays) throws SQLException;
	
	Content get(long id);
}
