package cn.bigdb.gallery.canvas.biz;

import java.util.List;

import cn.bigdb.gallery.resource.entity.ResourceInfo;

public interface CanvasBiz {

	List<ResourceInfo> getResourceInfo(long contentId);
	
}
