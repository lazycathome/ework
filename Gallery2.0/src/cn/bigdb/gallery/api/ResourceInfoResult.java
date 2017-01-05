package cn.bigdb.gallery.api;

import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

public class ResourceInfoResult {

	private int code;
	
	private String message;
	
	private ResourceInfo result;
	
	public ResourceInfoResult(int code, String message, ResourceInfo result){
		this.result = result;
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

	public ResourceInfo getResult() {
		return result;
	}

	public void setResult(ResourceInfo result) {
		this.result = result;
	}
	
	public ApiResult getJsonResult(){
		return new JsonResult(this);
	}
}
