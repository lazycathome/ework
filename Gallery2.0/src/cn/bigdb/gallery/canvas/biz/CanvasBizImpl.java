package cn.bigdb.gallery.canvas.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bigdb.gallery.canvas.entity.Canvas;
import cn.bigdb.gallery.canvas.service.CanvasService;
import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.bigdb.gallery.resource.service.ResourceInfoService;

@Service
public class CanvasBizImpl implements CanvasBiz {

	@Autowired
	private CanvasService canvasService;
	
	@Autowired
	private ResourceInfoService resourceInfoService;
	
	@Override
	public List<ResourceInfo> getResourceInfo(long contentId) {
		List<Canvas> canvasList = canvasService.getList(contentId);
		List<Long> canvasIdList = new ArrayList<Long>(canvasList.size());
		for(int i = 0; i < canvasList.size(); i++){
			Canvas canvas = canvasList.get(i);
			canvasIdList.add(canvas.getId());
		}
		return resourceInfoService.getList(canvasIdList);
	}

}
