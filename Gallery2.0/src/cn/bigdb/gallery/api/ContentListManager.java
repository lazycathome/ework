package cn.bigdb.gallery.api;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bigdb.gallery.content.entity.Content;
import cn.bigdb.gallery.content.service.ContentService;
import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.core.Page;
import cn.bigdb.gallery.utils.ConvertUtils;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;

@Path("/api/gallery/content/list/?$")
public class ContentListManager {

	@Autowired
	private ContentService contentService;
	
	@GET
	public ApiResult get(@QueryParam("equipId") String equipId, @QueryParam("creator") String sCreator, @QueryParam("pageSize") String sPageSize, @QueryParam("pageNo") String sPageNo){
		int pageNo =  ConvertUtils.toInt(sPageNo, 1);
		int pageSize =  ConvertUtils.toInt(sPageSize, 15);
		long creator = ConvertUtils.toLong(sCreator, 0);
		if(creator == 0){
			return (new ParamIllegalResult()).getJsonResult();
		}
		
		Page<Content> page = contentService.getList(creator, equipId, pageSize, pageNo);
		
		ContentListResult result = new ContentListResult(Code.SUCCESS, Code.SUCCESS_MESSAGE, page.getResults());
		result.setTotalCount(page.getTotalCount());
		result.setPageCount(page.getPageCount());
		return result.getJsonResult(); 
	}
	
}
