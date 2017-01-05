package cn.bigdb.gallery.utils;

public class SqlUtils {

	public static StringBuilder sqlJoin(int len,String split){
		StringBuilder sb = new StringBuilder("");
		if(len <= 0) return sb;
		for(int i = 1 ; i <= len; i++){
			sb.append(split);
		}
		return sb.deleteCharAt(sb.length() - 1);
	}
	
}
