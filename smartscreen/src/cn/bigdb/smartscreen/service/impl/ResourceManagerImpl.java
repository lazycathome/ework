package cn.bigdb.smartscreen.service.impl;

import cn.bigdb.smartscreen.dao.ResourceInfoDao;
import cn.bigdb.smartscreen.model.ResourceInfo;
import cn.bigdb.smartscreen.service.IResourceManager;

public class ResourceManagerImpl implements IResourceManager {

	ResourceInfoDao resourceInfoDao;
	@Override
	public String addResourceInfo(ResourceInfo resource) {
		// TODO Auto-generated method stub
		return resourceInfoDao.addResourceInfo(resource);
	}
	public ResourceInfoDao getResourceInfoDao() {
		return resourceInfoDao;
	}
	public void setResourceInfoDao(ResourceInfoDao resourceInfoDao) {
		this.resourceInfoDao = resourceInfoDao;
	}
	@Override
	public ResourceInfo getResourceInfo(String id) {
		// TODO Auto-generated method stub
		return resourceInfoDao.getResourceInfo(id);
	}
	

	
}
