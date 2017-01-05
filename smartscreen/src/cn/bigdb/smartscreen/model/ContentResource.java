package cn.bigdb.smartscreen.model;

import java.io.Serializable;

public class ContentResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611494509511055694L;
	private String id; //主键ID
	private ResourceInfo resourceInfo; //资源文件ID
	private Content content; 
	private String description; //描述
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ResourceInfo getResourceInfo() {
		return resourceInfo;
	}
	public void setResourceInfo(ResourceInfo resourceInfo) {
		this.resourceInfo = resourceInfo;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
