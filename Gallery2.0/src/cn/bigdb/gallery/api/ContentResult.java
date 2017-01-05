package cn.bigdb.gallery.api;

import cn.bigdb.gallery.content.entity.Content;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

public class ContentResult {

	private int code;
	
	private String message;
	
	private Content result;
	
	public ContentResult(int code, String message, Content result){
		this.code = code;
		this.message = message;
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

	public Content getResult() {
		return result;
	}

	public void setResult(Content result) {
		this.result = result;
	}
	
	public ApiResult getJsonResult(){
		return new JsonResult(this);
	}
}
