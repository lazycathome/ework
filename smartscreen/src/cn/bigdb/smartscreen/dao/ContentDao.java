package cn.bigdb.smartscreen.dao;

import java.util.List;
import java.util.Map;

import cn.bigdb.smartscreen.model.Content;

public interface ContentDao {

	/**
	 * 
	 */
	public List<Content> getContentList();
	public String addContent(Content content);
	public String updateContent(Content content);
	public Content getContent(String id);
	public List<Content> queryList(String sql,Map<String,Object> param);
	public String delContent(String id);
	public String update(String hql,Map<String,Object> param);
}
