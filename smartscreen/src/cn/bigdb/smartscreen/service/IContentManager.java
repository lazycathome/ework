package cn.bigdb.smartscreen.service;

import java.util.List;

import cn.bigdb.smartscreen.model.Content;

public interface IContentManager {

	public String addContent(Content content);
	public String updateContent(Content content);
	public String delContent(String id);
	public List<Content> getAllContent();
	
	/**
	 * 条件查询内容，根据作者、关键字、创建时间、查询创建时间以后的内容列表
	 * @param content
	 * @return
	 */
	public List<Content> queryConent(Content content,String equipId);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Content getContent(String id);
	
	/**
	 * 根据设备ID查询该设备播放的内容
	 * @param equipId
	 * @return
	 */
	public List<Content> getContentByEquip(String equipId);
	
	/**
	 * 功能：根据更新操作，更新内容信息
	 * 描述：适用的更新操作有：编辑、暂停、启用、升级、降级。<br>
	 * 当是升级降级操作时，content对象的id属性存放主动被修改的内容ID，<br>
	 * description属性存放的是需要协同修改级别的内容ID
	 * @param content 更新内容对象
	 * @param operate 操作名：暂停、开始、更新内容
	 * @return 更新成功返回 success, 失败返回err 如果操作不存在，返回null
	 */
	public String updateContent(Content content, String operate);
}
