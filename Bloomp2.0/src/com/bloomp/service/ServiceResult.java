package com.bloomp.service;

/**
 * API调用返回
 * @author azhi
 *
 */
public interface ServiceResult {

	/**
	 * 设置返回状态码
	 * @param code
	 */
	public void setCode(int code);
	/**
	 * 获取返回状态码
	 * @return
	 */
	public int getCode();

	/**
	 * 设置返回状态的文本消息
	 * @param message
	 */
	public void setMessage(String message);
	/**
	 * 获取返回状态的文本消息
	 * @return
	 */
	public String getMessage();

}
