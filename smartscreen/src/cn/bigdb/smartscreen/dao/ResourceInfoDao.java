package cn.bigdb.smartscreen.dao;

import java.util.List;
import java.util.Map;

import cn.bigdb.smartscreen.model.ResourceInfo;

public interface ResourceInfoDao {
	
	public List<ResourceInfo> getResourceInfoList();
	public String addResourceInfo(ResourceInfo res);
	public String ResourceInfo(ResourceInfo res);
	public ResourceInfo getResourceInfo(String id);
	public List<ResourceInfo> queryList(String sql,Map<String,Object> param);
	public String delResourceInfo(String id);
}
