package cn.bigdb.gallery.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	
	public static boolean isBlank(String str){
		return (str == null || "".equals(str.trim())) ? true : false;
	}
	
	public static boolean isNotBlank(String str){
		return (str == null || "".equals(str.trim())) ? false : true;
	}

	public static List<String> splitStrs2List(String strs){
		return splitStrs2List(strs, ",");
	}
	
	public static List<String> splitStrs2List(String strs, String split){
		List<String> results = null;
		if(strs.indexOf(split) >= 1){
			String[] tStrs = strs.split(split);
			results = new ArrayList<String>(tStrs.length);
			for(int i = 0; i < tStrs.length; i++){
				results.add(tStrs[i]);
			}
		}else{
			results = new ArrayList<String>(1);
			results.add(strs);
		}
		return results;
	}
	
	public static List<Long> splitStrs2LongList(String strs, String split){
		List<Long> results = null;
		if(strs.indexOf(split) >= 1){
			String[] tStrs = strs.split(split);
			results = new ArrayList<Long>(tStrs.length);
			for(int i = 0; i < tStrs.length; i++){
				results.add(ConvertUtils.toLong(tStrs[i]));
			}
		}else{
			results = new ArrayList<Long>(1);
			results.add(ConvertUtils.toLong(strs));
		}
		return results;
	}
	
	public static List<Integer> splitStrs2IntegerList(String strs, String split){
		List<Integer> results = null;
		if(strs.indexOf(split) >= 1){
			String[] tStrs = strs.split(split);
			results = new ArrayList<Integer>(tStrs.length);
			for(int i = 0; i < tStrs.length; i++){
				results.add(ConvertUtils.toInt(tStrs[i]));
			}
		}else{
			results = new ArrayList<Integer>(1);
			results.add(ConvertUtils.toInt(strs));
		}
		return results;
	}
}
