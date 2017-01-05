package cn.bigdb.gallery.content.biz;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bigdb.gallery.canvas.service.CanvasService;
import cn.bigdb.gallery.content.entity.EquipContent;
import cn.bigdb.gallery.content.service.ContentService;
import cn.bigdb.gallery.resource.service.ResourceInfoService;

@Service
public class ContentBizImpl implements ContentBiz {

	@Autowired
	private ContentService contentService;
	
	@Autowired
	private ResourceInfoService resourceInfoService;
	
	@Autowired
	private CanvasService canvasService;
	
	@Override
	@Transactional
	public long init(String equipId, String title, String desc, int duration, int height,
			int width, long creator, List<String> picList, String... showdays) throws SQLException {
		//保存内容
		long contentId = contentService.save(title, desc, duration, height, width, creator, showdays);
		
		EquipContent eContent = new EquipContent();
		eContent.setContentId(contentId);
		eContent.setEquipId(equipId);
		
		//保存画布
		long canvasId = canvasService.init(contentId);
		
		//保存展示单元
		long rId = resourceInfoService.init(picList, canvasId);
		
		if(contentId <= 0 || canvasId <= 0 || rId <= 0){
			throw new SQLException();
		}
		
		return contentId;
	}

}
