package com.bloomp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author liujt
 * @date 2014年7月18日
 */

public class PathUtils {

	private static String path = "";
	
	public static String getWebPath() {
		if(path == null || "".equals(path)){
			String pathFile =  PathUtils.class.getResource("").getPath();
			int index = pathFile.indexOf("WEB-INF");
			if (index == -1) {
				index = pathFile.indexOf("classes");
			}
			if (index == -1) {
				index = pathFile.indexOf("bin");
			}
			pathFile = pathFile.substring(0, index);
	
			try {
				pathFile = URLDecoder.decode(pathFile, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(pathFile.endsWith("/")){//去除末尾“/”
				pathFile = pathFile.substring(0,pathFile.length()-1);
			}
			path = pathFile;
		}
		return path;
	}

}
