package cn.bigdb.gallery.utils;

public class ConvertUtils {

	public static int toInt(String str){
		return isNumeric(str) ? Integer.parseInt(str) : 0;
	}
	
	public static int toInt(String str, int def){
		return isNumeric(str) ? Integer.parseInt(str) : def;
	}
	
	public static long toLong(String str){
		return isNumeric(str) ? Long.parseLong(str) : 0;
	}
	
	public static long toLong(String str, int def){
		return isNumeric(str) ? Long.parseLong(str) : def;
	}
	
	
	/**
	 * 验证指定的字符串是否为数字
	 * @param str 需要验证的字符串
	 * @return 纯数字组成true，否则false
	 */
	public static boolean isNumeric(String str){
		if(isBlank(str)) return false;
		for (int i = 0; i < str.length(); i++) {
			if (i == 0 && str.charAt(i) == '-') {
				continue;
			} else if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isBlank(String str){
		return (str == null || "".equals(str.trim())) ? true : false;
	}
}
