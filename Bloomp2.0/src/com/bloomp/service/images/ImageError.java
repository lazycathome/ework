package com.bloomp.service.images;

import java.util.Hashtable;

/**
 * 图片服务的错误信息包装
 * @author azhi
 *
 */
public class ImageError {

	public static final int FILE_NOT_FOUND = -1;
	public static final int OK = 0;

	/**
	 * 错误号与错误信息的映射表
	 */
	private static Hashtable<Integer, String> messages = new Hashtable<Integer, String>();

	static {
		messages.put(FILE_NOT_FOUND, "File not found.");
		messages.put(OK, "OK");
	}

	/**
	 * 根据错误号查找对应的文本信息
	 * @param code
	 * @return
	 */
	public static String getMessage(int code) {
		if (messages.containsKey(code)) {
			return messages.get(code);
		}

		return "Unknown error.";
	}

}
