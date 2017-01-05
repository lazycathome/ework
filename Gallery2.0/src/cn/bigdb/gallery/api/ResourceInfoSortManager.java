package cn.bigdb.gallery.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.bigdb.gallery.resource.service.ResourceInfoService;
import cn.bigdb.gallery.utils.StringUtils;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;

@Path("/api/gallery/resource/sort/?$")
public class ResourceInfoSortManager {

	@Autowired
	private ResourceInfoService resourceInfoService;
	
	@POST
	public ApiResult sort(@QueryParam("ids") String ids, @QueryParam("sorts") String sorts){
		if(StringUtils.isBlank(ids) || StringUtils.isBlank(sorts)){
			return (new ParamIllegalResult()).getJsonResult();
		}
		List<ResourceInfo> sortList =  this.parser(ids, sorts);
		if(sortList == null){
			ParamIllegalResult illegal = new ParamIllegalResult();
			illegal.setCode(ResourceCode.PARAM_DIFFER);
			illegal.setMessage(ResourceCode.PARAM_DIFFER_MESSAGE);
			return illegal.getJsonResult();
		}else{
			resourceInfoService.update(sortList);
			SuccessResult result = new SuccessResult(Code.SUCCESS, Code.SUCCESS_MESSAGE);
			return result.getJsonResult();
		}
		
	}
	
	private List<ResourceInfo> parser(String ids, String sorts){
		List<Long> idList = StringUtils.splitStrs2LongList(ids, ",");
		List<Integer> sortList = StringUtils.splitStrs2IntegerList(sorts, ",");
		
		if(idList.size() == sortList.size()){
			List<ResourceInfo> results = new ArrayList<ResourceInfo>(idList.size());
			for(int i = 0; i < idList.size(); i++){
				ResourceInfo resourceInfo = new ResourceInfo();
				resourceInfo.setId(idList.get(i));
				resourceInfo.setSort(sortList.get(i));
				results.add(resourceInfo);
			}
			return results;
		}
		return null;
	}
}
