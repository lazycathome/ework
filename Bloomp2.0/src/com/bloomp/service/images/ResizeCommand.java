package com.bloomp.service.images;

public class ResizeCommand {

	private int width;
	
	private int height;
	
	private String url;
	
	private boolean lockScale;
	
	public ResizeCommand(){}
	
	public ResizeCommand(int width, int height, String url, boolean lockScale){
		this.width = width;
		this.height = height;
		this.url = url;
		this.lockScale = lockScale;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isLockScale() {
		return lockScale;
	}

	public void setLockScale(boolean lockScale) {
		this.lockScale = lockScale;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
