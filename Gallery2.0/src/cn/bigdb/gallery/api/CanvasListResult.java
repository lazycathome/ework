package cn.bigdb.gallery.api;

import java.util.ArrayList;
import java.util.List;

import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

public class CanvasListResult {

	private int code;
	
	private String message;
	
	private long totalCount;
	
	private long pageCount;
	
	private List<ResourceInfo> results = new ArrayList<ResourceInfo>(0);

	public CanvasListResult(int code, String message, List<ResourceInfo> results){
		this.code = code;
		this.message = message;
		this.results = results;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public List<ResourceInfo> getResults() {
		return results;
	}

	public void setResults(List<ResourceInfo> results) {
		this.results = results;
	}

	public ApiResult getJsonResult(){
		return new JsonResult(this);
	}
}
