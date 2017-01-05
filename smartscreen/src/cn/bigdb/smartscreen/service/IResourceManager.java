package cn.bigdb.smartscreen.service;

import cn.bigdb.smartscreen.model.ResourceInfo;

public interface IResourceManager {
	
	public String addResourceInfo(ResourceInfo res);
	public ResourceInfo getResourceInfo(String id);
}
