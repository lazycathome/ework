package com.bloomp.service.images;

import com.bloomp.service.ServiceResult;

/**
 * 图片服务的返回结果包装类
 * @author azhi
 *
 */
public class ImageResult implements ServiceResult {

	private int code = 0;
	private String message = "OK";
	private String url = "";

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
