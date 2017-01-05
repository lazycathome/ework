package cn.bigdb.gallery.api;

import cn.bigdb.gallery.core.Code;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

public class ParamIllegalResult {

	private int code = Code.PARAM_ILLEGAL;
	
	private String message = Code.PARAM_ILLEGAL_MESSAGE;
	
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
