package cn.bigdb.smartscreen.dao;

import java.util.List;

import cn.bigdb.smartscreen.model.Content;
import cn.bigdb.smartscreen.model.ResourceInfo;

public interface UpgradeDao extends BaseDao {

	List<ResourceInfo> getUpgradeResourceByContentId(String equipId);
	
	List<Content> getUpgradeContentByEquipId(String equipId);
}
