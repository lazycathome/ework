package cn.bigdb.gallery.api;

import java.util.List;

import cn.bigdb.gallery.content.entity.Content;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

public class ContentListResult {

	private int code;
	
	private String message;
	
	private long totalCount;
	
	private long pageCount;
	
	private List<Content> results;
	
	public ContentListResult(int code, String message, List<Content> results){
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

	public List<Content> getResults() {
		return results;
	}

	public void setResults(List<Content> results) {
		this.results = results;
	}
	
	public ApiResult getJsonResult(){
		return new JsonResult(this);
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
	
}
