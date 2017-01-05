package cn.bigdb.gallery.content.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bigdb.gallery.content.entity.Content;
import cn.bigdb.gallery.core.Page;

public interface ContentDao {

	long save(List<Content> content) throws SQLException;
	
	List<Content> getList();
	
	List<Content> getList(List<Long> idList) throws SQLException;
	
	Page<Content> getList(long creator, Page<Content> page) throws SQLException;
	
	void update(Content content) throws SQLException;
	
	Content get(long id);
	
	
}
