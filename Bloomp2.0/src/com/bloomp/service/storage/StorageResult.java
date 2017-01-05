package com.bloomp.service.storage;

import com.bloomp.service.ServiceResult;

/**
 * 存储服务的返回结果包装类
 * @author azhi
 *
 */
public class StorageResult implements ServiceResult {

	private int code = 0;
	private String message = "OK";
	private String url = "";

	public StorageResult(){}
	
	public StorageResult(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	@Override
	public void setCode(int code) {
		// TODO Auto-generated method stub
		this.code = code;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	/**
	 * 设置返回结果的 Url
	 * @param url
	 */
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		this.url = url;
	}

	/**
	 * 获取返回结果的 Url
	 * @return
	 */
	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

}
