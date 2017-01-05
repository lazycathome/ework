package com.bloomp.service.images;

public class CutCommand {

	private int left;
	
	private int top;
	
	private int width;
	
	private int height;
	
	private String url;
	
	public CutCommand(){}
	
	public CutCommand(int left, int top, int width, int height, String url){
		this.left = left;
		this.top = top;
		this.height = height;
		this.width = width;
		this.url = url;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
