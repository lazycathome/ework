package cn.bigdb.smartscreen.vo;

import java.io.Serializable;

public class MessageVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4056517581159598595L;

	private String code;
	
	private String msg;
	
	private String content;//

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
