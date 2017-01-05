package cn.bigdb.smartscreen.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import cn.bigdb.smartscreen.dao.UpgradeDao;
import cn.bigdb.smartscreen.model.Content;
import cn.bigdb.smartscreen.model.ContentResource;
import cn.bigdb.smartscreen.model.ResourceInfo;

public class UpgradeDaoImpl extends BaseDaoImpl implements UpgradeDao {

	private String QUERYRESOURCEBYEQUIPID = "select t.* from sm_content_resource cr, sm_resource_info t where cr.contentid=? and cr.resid=t.id";
	private String QUERYCONTENTBYEQUIPID = "select c.* from sm_content c, sm_equip_content ec where ec.equipId=? and ec.status=0 and ec.deleted='false'";
	
	
	private static Logger logger = Logger.getLogger(UpgradeDaoImpl.class);
	 
	@Override
	public List<ResourceInfo> getUpgradeResourceByContentId(String equipId) {
		List<Object> vList = new ArrayList<Object>();
		vList.add(equipId);
		try {
			return map2ResourceInfo(this.queryRes(QUERYRESOURCEBYEQUIPID, vList));
		} catch (SQLException e) {
			logger.error("根据设备id查询需要更新的资源文件失败");
		}
		return null;
	}

	private List<ResourceInfo> map2ResourceInfo(List<?> list){
		Iterator<?> it = list.iterator();
		List<ResourceInfo> result = new ArrayList<ResourceInfo>();
		ResourceInfo resource = null;
		while(it.hasNext()){
			resource = new ResourceInfo();
			Map<String,Object> map = (Map<String,Object>)it.next();
			resource.setId(this.getString(map, "id"));
			resource.setCategory(this.getString(map, "category"));
			resource.setDescription(this.getString(map, "description"));
			resource.setLength(this.getInt(map, "length"));
			resource.setPic(this.getString(map, "pic"));
			resource.setVideo(this.getString(map, "video"));
			resource.setVideoTime(this.getString(map, "videoTime"));
			resource.setMode(this.getInt(map, "mode"));
			resource.setSize(this.getInt(map, "size"));
			resource.setTime(this.getInt(map, "time"));
			result.add(resource);
		}
		return result;
	}
	
	private List<Content> map2Content(List<?> list){
		Iterator<?> it = list.iterator();
		List<Content> result = new ArrayList<Content>();
		Content content = null;
		while(it.hasNext()){
			content = new Content();
			Map<String,Object> map = (Map<String,Object>)it.next();
			content.setAudioUrl(this.getString(map, "audioUrl"));
			content.setAuthor(this.getString(map, "author"));
			content.setCategory(this.getString(map, "category"));
			content.setCreateTime(this.getLong(map, "createTime"));
			content.setDeleted(this.getString(map, "deleted"));
			content.setDescription(this.getString(map, "description"));
			content.setId(this.getString(map, "id"));
			content.setLabel(this.getString(map, "label"));
			content.setLastTime(this.getLong(map, "lastTime"));
			content.setMemo(this.getString(map, "memo"));
			content.setName(this.getString(map, "name"));
			content.setPlayCount(this.getInt(map, "playCount"));
			content.setPlayType(this.getInt(map, "playType"));
			content.setShowlevel(this.getInt(map, "showlevel"));
			content.setStatus(this.getInt(map, "status"));
			content.setTemplateInfo(this.getString(map, "templateInfo"));
			content.setTime(this.getInt(map, "time"));
			content.setType(this.getString(map, "type"));
			List<ResourceInfo> resources = getUpgradeResourceByContentId(content.getId());
			Set<ContentResource> contentResources = new HashSet<ContentResource>(resources.size());
			for(ResourceInfo res : resources){
				ContentResource contentResource = new ContentResource();
				contentResource.setContent(content);
				contentResource.setResourceInfo(res);
				contentResources.add(contentResource);
			}
			content.setContentResources(contentResources);
		}
		return result;
	}

	@Override
	public List<Content> getUpgradeContentByEquipId(String equipId) {
		List<Object> vList = new ArrayList<Object>();
		vList.add(equipId);
		try {
			return map2Content(this.queryRes(QUERYCONTENTBYEQUIPID, vList));
		} catch (SQLException e) {
			logger.error("根据设备id查询需要更新的资源文件失败");
		}
		return null;
	}
	
	
}
