package cn.bigdb.gallery.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.bigdb.gallery.resource.service.ResourceInfoService;
import cn.bigdb.gallery.utils.ConvertUtils;
import cn.jedisoft.framework.annotations.FormParam;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;

@Path("/api/gallery/resource/?$")
public class ResourceInfoManager {

	@Autowired
	private ResourceInfoService resourceInfoService;
	
	@GET
	public ApiResult get(@QueryParam("resourceId") String sResourceId){
		int resourceId = ConvertUtils.toInt(sResourceId, 0);
		if(resourceId == 0){
			return (new ParamIllegalResult()).getJsonResult();
		}
		ResourceInfo resourceInfo = resourceInfoService.get(resourceId);
		ResourceInfoResult result = new ResourceInfoResult(Code.SUCCESS, Code.SUCCESS_MESSAGE, resourceInfo);
		return result.getJsonResult();
	}
	
	@POST
	public ApiResult update(@FormParam("resourceId") String sResourceIds, @FormParam("beautify") String sBeautifys, 
			@FormParam("duration") String sDurations, @FormParam("position") String positions, 
			@FormParam("region") String regions, @FormParam("title") String titles,
			@FormParam("sort") String sorts){
//		int resourceId = ConvertUtils.toInt(sResourceId, 0);
//		int beautify = ConvertUtils.toInt(sBeautify, 0);
//		int duration = ConvertUtils.toInt(sDuration, 0);
//		if(resourceId == 0 || beautify == 0 || duration == 0 || StringUtils.isBlank(title)){
//			return (new ParamIllegalResult()).getJsonResult();
//		}
		if(StringUtils.isBlank(sResourceIds) || StringUtils.isBlank(sBeautifys) || 
				StringUtils.isBlank(sDurations) || StringUtils.isBlank(positions) || 
				StringUtils.isBlank(regions) || StringUtils.isBlank(titles) || 
				StringUtils.isBlank(sorts)){
			return (new ParamIllegalResult()).getJsonResult();
		}
		
		String[] resourceIds = sResourceIds.split(",");
		String[] beautifys = sBeautifys.split(",");
		String[] durations = sDurations.split(",");
		String[] tPosition = positions.split(",");
		String[] tRegion = regions.split(",");
		String[] tTitle = titles.split(",");
		String[] tSort = sorts.split(",");
		long time = System.currentTimeMillis();
		List<ResourceInfo> resList = new ArrayList<ResourceInfo>(resourceIds.length);
		for(int i = 0 ; i < resourceIds.length; i++){
			ResourceInfo resourceInfo = new ResourceInfo();
			resourceInfo.setBeautify(ConvertUtils.toInt(beautifys[i], 0));
			resourceInfo.setDuration(ConvertUtils.toInt(durations[i], 0));
			resourceInfo.setId(ConvertUtils.toLong(resourceIds[i], 0));
			resourceInfo.setPosition(i >= tPosition.length ? "" : tPosition[i]);
			resourceInfo.setRegion(i >= tRegion.length ? "" : tRegion[i]);
			resourceInfo.setName(i >= tTitle.length ? "" : tTitle[i]);
			resourceInfo.setSort(ConvertUtils.toInt(tSort[i], 0));
			resourceInfo.setUpdateTime(time);
			resList.add(resourceInfo);
		}
		resourceInfoService.update(resList);
		ResourceInfoResult result = new ResourceInfoResult(Code.SUCCESS, Code.SUCCESS_MESSAGE, null);
		return result.getJsonResult();
	}
}
