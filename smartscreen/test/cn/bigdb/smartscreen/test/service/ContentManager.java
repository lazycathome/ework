package cn.bigdb.smartscreen.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.bigdb.smartscreen.model.Content;
import cn.bigdb.smartscreen.model.ContentResource;
import cn.bigdb.smartscreen.model.EquipContent;
import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.model.PlazaArea;
import cn.bigdb.smartscreen.model.ResourceInfo;
import cn.bigdb.smartscreen.service.IContentManager;
import cn.bigdb.smartscreen.service.IEquipInfoManager;
import cn.bigdb.smartscreen.service.IResourceManager;
import cn.bigdb.smartscreen.test.common.LoadXmlFile;
import cn.bigdb.smartscreen.utils.Utils;

public class ContentManager {
	
	/**
	 * 想要存储content，就能把相关的resourceInfo，以及equipInfo和中间表的信息插入，
	 * 则需要配置这四张表中，关联colum的cascade="save-update"
	 */
	
	public void add(){
		ApplicationContext app = LoadXmlFile.load();
		IContentManager service = (IContentManager)app.getBean("contentManager");
		Content content = new Content();
		content.setId(Utils.getPriKeyId());
		content.setCreateTime(System.currentTimeMillis());
		content.setLastTime(System.currentTimeMillis());
		content.setTime(0);
		content.setPlayCount(0);
		content.setStatus(0);
		content.setPlayType(0);
		content.setCategory("1");
		content.setAuthor("gl");
		content.setName("联想专卖");
		content.setType("电脑");
		content.setLabel("联想；电脑");
		content.setAudioUrl("test.mp4");
		Set<EquipContent> equipContents = new HashSet<EquipContent>();
		EquipContent equipContent = new EquipContent();
		EquipInfo equip = new EquipInfo();
		equip.setId(Utils.getPriKeyId());
		PlazaArea area = new PlazaArea();
		area.setId("2");
		equip.setArea(area);
		equip.setCategory("1");
		
		equip.setWidth(1920);
		equip.setHeight(520);
		equipContent.setContent(content);
		equipContent.setEquip(equip);
		equipContent.setId(Utils.getPriKeyId());
		equipContents.add(equipContent);
		content.setEquipContents(equipContents);
		
		Set<ContentResource> contentResources = new HashSet<ContentResource>();
		ContentResource contentResource = new ContentResource();
		ContentResource contentResource2 = new ContentResource();
		contentResources.add(contentResource);
		contentResource.setId(Utils.getPriKeyId());
		contentResource.setContent(content);
		contentResources.add(contentResource2);
		contentResource2.setId(Utils.getPriKeyId());
		contentResource2.setContent(content);
		ResourceInfo resourceInfo = new ResourceInfo();
		ResourceInfo resourceInfo2 = new ResourceInfo();
		resourceInfo.setId(Utils.getPriKeyId());
		resourceInfo.setCategory("1");
		resourceInfo.setPic("gl1.jpg");
		resourceInfo.setSize(100);
		resourceInfo.setContentResource(contentResource);
		contentResource.setResourceInfo(resourceInfo);
		
		resourceInfo2.setId(Utils.getPriKeyId());
		resourceInfo2.setCategory("1");
		resourceInfo2.setPic("gl2.jpg");
		resourceInfo2.setSize(100);
		resourceInfo2.setContentResource(contentResource2);
		contentResource2.setResourceInfo(resourceInfo2);
		
		content.setContentResources(contentResources);
		
		
		service.addContent(content);
	}
	
	/**
	 * 如果删除content
	 * 也想删除
	 * */
	
	public void delete(){
		ApplicationContext app = LoadXmlFile.load();
		IContentManager service = (IContentManager)app.getBean("contentManager");
		service.delContent("2e918898960541eb8976ca56bfcd58b5");
	}
	
	@Test
	public void update(){
		ApplicationContext app = LoadXmlFile.load();
		IContentManager service = (IContentManager)app.getBean("contentManager");
//		IResourceManager rservice = (IResourceManager)app.getBean("resourceManager");
		Content c = service.getContent("4f98a3986a9a4ca98288e4e86a42fbd8");
		Content content = new Content();
		content.setId("4f98a3986a9a4ca98288e4e86a42fbd8");
		content.setAuthor("gaolin");
		content.setLabel("ceshi");
		content.setCreateTime(System.currentTimeMillis());
		content.setPlayCount(3);
		Content newC = (Content) Utils.extendObject( c, content);
		
//		Set<ContentResource> crs = c.getContentResources();
//		Set<ContentResource> newcrs = c.getContentResources();
//		for (ContentResource cr : crs) {
//			/**
//			 * 重新设置resourceInfo的信息,通过cr.getResourceInfo()得到的资源对象，
//			 * 除了ID，是不能获取或者设置其他字段的值的。
//			 * */
//			ResourceInfo  rc = rservice.getResourceInfo(cr.getResourceInfo().getId());
//		
//			rc.setDescription("this is a pen !");
//			cr.setResourceInfo(rc);
//			newcrs.add(cr);
//		}
//		
//		/**更新一下  与此content关联的 resourceInfo */
//		c.setContentResources(newcrs);
		
		service.updateContent(c);
	}
	
	
	public void getContent(){
		ApplicationContext app = LoadXmlFile.load();
		IContentManager service = (IContentManager)app.getBean("contentManager");
		IResourceManager rservice = (IResourceManager)app.getBean("resourceManager");
		IEquipInfoManager eservice = (IEquipInfoManager) app.getBean("equipInfoManager");
		Content c = service.getContent("754230d10f194a9399a2b3e477c0a36b");
		Set<ContentResource> crs = c.getContentResources();
		List<ResourceInfo> rs = new ArrayList<ResourceInfo>();
		for (ContentResource cr : crs) {
			ResourceInfo  rc = rservice.getResourceInfo(cr.getResourceInfo().getId());
			rs.add(rc);
		}
		
		Set<EquipContent> ecs = c.getEquipContents();
		List<EquipInfo> es = new ArrayList<EquipInfo>();
		for (EquipContent ec : ecs) {
			es.add(eservice.getEquip(ec.getEquip().getId()));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("content", c);
		result.put("resources", rs);
		result.put("equips", es);
		/**
		 * 把需要懒加载的字段放到这个数组中就可以了! 转成的json字符串是不包含这些属性的
		 * */
//		JsonConfig config = new JsonConfig();
//		config.setExcludes( new String[]{ "contentResources" , "equipContents","contentResource","heartbeats","equipUpdates" } ) ;
//		JSONObject jobj1=JSONObject.fromObject(result,config);
//		System.out.println(jobj1.toString());

	}
	
	
//	public void getList(){
//		ApplicationContext app = LoadXmlFile.load();
//		IContentManager service = (IContentManager)app.getBean("contentManager");
//		
//		List<Content> list = service.getAllContent();
//		if(list != null){
//			JSONArray arrayContent = new JSONArray(); 
//			for (Content c : list) {
//				JSONObject jo = new JSONObject();
//				jo.put("id", c.getId());
//				jo.put("author", c.getAuthor());
//				jo.put("category", c.getCategory());
//				jo.put("createTime", c.getCreateTime());
//				jo.put("description", c.getDescription());
//				arrayContent.add(jo);
//			}
//			System.out.println(arrayContent.toString());
//
//		}
//	}
//	
//	
//	
//	public void getContentByEuipId(){
//		ApplicationContext app = LoadXmlFile.load();
//		IContentManager service = (IContentManager)app.getBean("contentManager");
////		Content c = new Content();
////		service.queryConent(c);
//		 List<Content> result = service.getContentByEquip("57eff6df027c4c7d8253e005bfda8a45");
//		JsonConfig config = new JsonConfig();
//		config.setExcludes( new String[]{ "contentResources" , "equipContents" } ) ;
//		JSONArray jobj1=JSONArray.fromObject(result,config);
//		System.out.println(jobj1.toString());
//	}
//	
//	
//	public void queryList(){
//		ApplicationContext app = LoadXmlFile.load();
//		IContentManager service = (IContentManager)app.getBean("contentManager");
//		Content c = new Content();
//		c.setAuthor("gl");
//		c.setLable("%22%");
//		c.setCreateTime(1395036092003L);
//		List<Content> list = service.queryConent(c);
//		String[] strs = new String[]{ "contentResources" , "equipContents" };
//		
//		System.out.println(toJSONArray(list, strs).toString());
//	}
//	
////	public void update
//	
//	
//	private JSONArray toJSONArray(List list,String[] strs){
//		JsonConfig config = new JsonConfig();
//		config.setExcludes( strs ) ;
//		return JSONArray.fromObject(list,config);
//	}
	
	
}
