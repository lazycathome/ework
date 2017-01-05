package cn.bigdb.gallery.content.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bigdb.gallery.content.dao.ContentDao;
import cn.bigdb.gallery.content.dao.EquipContentDao;
import cn.bigdb.gallery.content.entity.Content;
import cn.bigdb.gallery.content.entity.EquipContent;
import cn.bigdb.gallery.content.entity.Window;
import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.core.Page;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentDao contentDao;

	@Autowired
	private EquipContentDao equipContentDao;
	
	@Override
	@Transactional
	public long save(String title, String desc, int duration, int height,
			int width, long creator, String... showdays) {
		Content content = new Content();
		Window window = new Window();
		window.setHeight(height);
		window.setWidth(width);
		content.setCreateTime(System.currentTimeMillis());
		content.setCreator(creator);
		content.setDesc(desc);
		content.setDuration(duration);
		content.setWindow(window);
		content.setTitle(title);
		List<Content> contentList = new ArrayList<Content>(1);
		contentList.add(content);
		try {
			long contentId = contentDao.save(contentList);
			return contentId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}

	@Override
	@Transactional
	public void update(String title, String desc, int duration, int height,
			int width, long creator, long id, String... showdays) throws SQLException {
		Content content = new Content();
		Window window = new Window();
		window.setHeight(height);
		window.setWidth(width);
		content.setCreateTime(System.currentTimeMillis());
		content.setCreator(creator);
		content.setDesc(desc);
		content.setDuration(duration);
		content.setWindow(window);
		content.setTitle(title);
		content.setId(id);
		try {
			contentDao.update(content);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Content get(long id) {
		if(id <= 0) return new Content();
		return contentDao.get(id);
	}

	@Override
	public Page<Content> getList(long creator, String equipId, int pageSize, int pageNo){
		Page<Content> page = new Page<Content>(pageSize);
		
		try {
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page = contentDao.getList(creator, page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public List<Content> getList(String equipId) {
		try {
			List<EquipContent> eContentList = equipContentDao.get(equipId);
			List<Long> contentIdList = new ArrayList<Long>(eContentList.size());
			for(int i = 0; i < eContentList.size(); i++){
				EquipContent eContent = eContentList.get(i);
				contentIdList.add(eContent.getContentId());
			}
			return contentDao.getList(contentIdList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Content>(0);
	}
}
