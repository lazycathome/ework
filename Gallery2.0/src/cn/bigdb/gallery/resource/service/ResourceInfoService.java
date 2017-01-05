package cn.bigdb.gallery.resource.service;

import java.util.List;

import cn.bigdb.gallery.resource.entity.ResourceInfo;

public interface ResourceInfoService {

	long save(List<ResourceInfo> resourceInfoList, long canvasId);
	
	long init(List<String> picList, long canvasId);
	
	List<ResourceInfo> getList(List<Long> canvasIdList);
	
	void update(ResourceInfo resourceInfo);
	
	List<String> getResourceFiles(List<Long> resIdList, long lastQueryTime);
	
	int update(List<ResourceInfo> sortList);
	
	ResourceInfo get(long id);
	
	void delete(long id, int state);
}
