package cn.bigdb.gallery.api.images;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujt
 * @date 2014年11月10日
 * @mail liujiangtao@gaiay.cn
 */

public class ImagesResult {

	private int code;
	
	private String message;
	
	private String url;
	
	private List<String> urls = new ArrayList<String>(0);

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
