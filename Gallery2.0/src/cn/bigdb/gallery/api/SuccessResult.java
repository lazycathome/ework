package cn.bigdb.gallery.api;

import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

public class SuccessResult {

	private int code;
	
	private String message;

	public SuccessResult(int code, String message){
		this.code = code;
		this.message = message;
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
	
	public ApiResult getJsonResult(){
		return new JsonResult(this);
	}
}
