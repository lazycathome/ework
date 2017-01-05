package cn.bigdb.gallery.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bigdb.gallery.content.biz.ContentBiz;
import cn.bigdb.gallery.content.entity.Content;
import cn.bigdb.gallery.content.service.ContentService;
import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.utils.ConvertUtils;
import cn.jedisoft.framework.annotations.BodyParam;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.PUT;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;

@Path("/api/gallery/content/?$")
public class ContentManager {

	@Autowired
	private ContentService contentService;
	
	@Autowired
	private ContentBiz contentBiz;
	
	@GET
	public ApiResult get(@QueryParam("id") String sId){
		int id = ConvertUtils.toInt(sId, 0);
		if(id == 0){
			return (new ParamIllegalResult()).getJsonResult();
		}else{
			Content content = contentService.get(id);
			return (new ContentResult(Code.SUCCESS, Code.SUCCESS_MESSAGE, content)).getJsonResult();
		}
	}
	
	@PUT
	public ApiResult create(@BodyParam("name") String title, @BodyParam("category") String sCategory, 
			@BodyParam("height") String sHeight, @BodyParam("duration") String sDuration, 
			@BodyParam("creator") String sCreator, @BodyParam("showdays") String showdays, 
			@BodyParam("type") String type, @BodyParam("desc") String desc,
			@BodyParam("width") String sWidth, @BodyParam("pics") String pics,
			 @BodyParam("equipId") String equipId){
		int duration = ConvertUtils.toInt(sDuration, 0);
		int height = ConvertUtils.toInt(sHeight, 0);
		int width = ConvertUtils.toInt(sWidth, 0);
		long creator =  ConvertUtils.toLong(sCreator, 0);
		String[] tPics = pics.split(",");
		List<String> picList = new ArrayList<String>(tPics.length);
		for(int i = 0; i < tPics.length; i++){
			picList.add(tPics[i]);
		}
		try {
			contentBiz.init(equipId, title, desc, duration, height, width, creator, picList, showdays);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (new SuccessResult(Code.SUCCESS, Code.SUCCESS_MESSAGE)).getJsonResult();
	}
	
	
}
