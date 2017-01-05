package cn.bigdb.gallery.resource.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bigdb.gallery.resource.entity.ResourceInfo;

public interface ResourceInfoDao {

	long save(List<ResourceInfo> resourceInfoList) throws SQLException;
	
	List<ResourceInfo> getList(List<Long> id) throws SQLException;
	
	void update(ResourceInfo resourceInfo) throws SQLException;
	
	ResourceInfo get(long id) throws SQLException;
	
	void delete(long id, int state) throws SQLException;
	
	int updateSort(List<ResourceInfo> sortList) throws SQLException;
	
	List<ResourceInfo> getResourceFiles(List<Long> resIdList, long lastQueryTime) throws SQLException;
}
