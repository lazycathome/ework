package cn.bigdb.gallery.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bigdb.gallery.canvas.biz.CanvasBiz;
import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.bigdb.gallery.utils.ConvertUtils;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;

@Path("/api/gallery/resource/list/?$")
public class CanvasListManager {

	@Autowired
	private CanvasBiz canvasBiz;
	
	@GET
	public ApiResult get(@QueryParam("contentId") String sContentId, @QueryParam("pageSize") String sPageSize, @QueryParam("pageNo") String sPageNo){
		int pageNo =  ConvertUtils.toInt(sPageNo, 15);
		int pageSize =  ConvertUtils.toInt(sPageSize, 0);
		int contentId =  ConvertUtils.toInt(sContentId, 0);
		if(contentId == 0){
			return (new ParamIllegalResult()).getJsonResult();
		}
		List<ResourceInfo> results = canvasBiz.getResourceInfo(contentId);
		CanvasListResult result = new CanvasListResult(Code.SUCCESS, Code.SUCCESS_MESSAGE, results);
		return result.getJsonResult();
	}
	
}
