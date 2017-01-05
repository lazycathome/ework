package cn.bigdb.smartscreen.contorller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bigdb.smartscreen.annotation.JsonFilter;
import cn.bigdb.smartscreen.annotation.JsonFilters;
import cn.bigdb.smartscreen.annotation.filter.ContentResourceFilter;
import cn.bigdb.smartscreen.annotation.filter.EquipContentFilter;
import cn.bigdb.smartscreen.annotation.filter.ResourceInfoFilter;
import cn.bigdb.smartscreen.model.Content;
import cn.bigdb.smartscreen.model.ContentResource;
import cn.bigdb.smartscreen.model.EquipContent;
import cn.bigdb.smartscreen.model.ResourceInfo;
import cn.bigdb.smartscreen.service.IContentManager;
import cn.bigdb.smartscreen.utils.Utils;

@Controller
public class ContentController {

	@Resource (name="contentManager")
	private IContentManager contentManager;
	 
	
	private static Logger logger = Logger.getLogger(ContentController.class);
	
	@RequestMapping(value="/content/add",method=RequestMethod.POST )
	@ResponseBody
	public String addContent(HttpServletRequest request,HttpServletResponse response, Content content){
		content.setId(Utils.getPriKeyId());
		long time = System.currentTimeMillis();
		content.setCreateTime(time);
		content.setLastTime(time);
		content.setPlayCount(0);
		content.setStatus(0);
		content.setPlayType(0);
		content.setAuthor("admin");
		content.setAudioUrl("test");
		List<ContentResource> list = content.getCrs();
		for(ContentResource cr : list){
			cr.setContent(content);
		}
		content.setContentResources(new HashSet<ContentResource>((list)));
		content.setCrs(null);
		contentManager.addContent(content);
		return "redirect:content/list";
		
	}

	@JsonFilters(values={@JsonFilter(mixin=EquipContentFilter.class, target=EquipContent.class)  
	,@JsonFilter(mixin=ContentResourceFilter.class, target=ContentResource.class)
	,@JsonFilter(mixin=ResourceInfoFilter.class, target=ResourceInfo.class)})  
	@RequestMapping(value="/content/list",method=RequestMethod.GET )
	@ResponseBody
	public Object getContentList(HttpServletRequest request,HttpServletResponse response){
		List<Content> contentList = contentManager.getAllContent();
		request.setAttribute("contentList", contentList);
//		return "content/contentList";
		return contentList;
	}
	
	@JsonFilters(values={@JsonFilter(mixin=EquipContentFilter.class, target=EquipContent.class)  
		,@JsonFilter(mixin=ContentResourceFilter.class, target=ContentResource.class)
		,@JsonFilter(mixin=ResourceInfoFilter.class, target=ResourceInfo.class)})  
	@RequestMapping(value="/content/query",method=RequestMethod.POST )
	@ResponseBody
	public Object getContent(HttpServletRequest request,HttpServletResponse response,Content content){
		 
		String equipId = request.getParameter("equipId");
		List<Content> cons =  contentManager.queryConent(content,equipId);
		request.setAttribute("contents", cons);
		return cons;
	}
	
	
	
	
	@JsonFilters(values={@JsonFilter(mixin=EquipContentFilter.class, target=EquipContent.class)  
    	,@JsonFilter(mixin=ContentResourceFilter.class, target=ContentResource.class)
		,@JsonFilter(mixin=ResourceInfoFilter.class, target=ResourceInfo.class)})  
	@RequestMapping(value="/content/get", method=RequestMethod.GET)
	@ResponseBody
	public Object get(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") String id){
		Content content = contentManager.getContent(id);
		request.setAttribute("content", content);
		return content;
	}
	
	@RequestMapping(value="/content/edit",method=RequestMethod.POST )
	@ResponseBody
	public String updateEquip(HttpServletRequest request,HttpServletResponse response,Content content){
		String operate = request.getParameter("operate");
		return contentManager.updateContent(content,operate);
	}
	
	
	public IContentManager getContentManager() {
		return contentManager;
	}

	public void setContentManager(IContentManager contentManager) {
		this.contentManager = contentManager;
	}
	
}
