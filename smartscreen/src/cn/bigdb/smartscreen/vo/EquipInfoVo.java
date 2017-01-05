package cn.bigdb.smartscreen.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EquipInfoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887728716565102463L;

	private int width;
	
	private int height;
	
	private List<ContentVo> pages = new ArrayList<ContentVo>();

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

	public List<ContentVo> getPages() {
		return pages;
	}

	public void setPages(List<ContentVo> pages) {
		this.pages = pages;
	}
	
	
}
