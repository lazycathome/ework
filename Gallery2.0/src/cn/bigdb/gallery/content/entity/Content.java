package cn.bigdb.gallery.content.entity;

import java.util.ArrayList;
import java.util.List;

import cn.bigdb.gallery.canvas.entity.Canvas;
import cn.bigdb.gallery.core.JSONUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Content {

	private long id;
	
	private String title;
	
	private String desc;
	
	private int duration;
	
	private int state;
	
	private List<ShowDay> showdays = new ArrayList<ShowDay>(0);
	
	private Window window;
	
	private String sWindow;
	
	private String sShowDays;

	private long creator;
	
	private long createTime;
	
	private long updateTime;
	
	private int category; //设备类型：宽屏或者竖屏
	
	private List<Canvas> maindata = new ArrayList<Canvas>(0);
	
	
	public enum Category{
 		horizontal(1),
 		vertical(2);
 		
 		private int value;
 		
 		private Category(int value) {    //    必须是private的，否则编译错误
 	        this.value = value;
 	    }
 		
 		public static Category valueOf(int value) {    //    手写的从int到enum的转换函数
 	        switch (value) {
 	        case 1:
 	            return horizontal;
 	        case 2:
 	            return vertical;
 	        default:
 	            return null;
 	        }
 	    }

 	    public int value() {
 	        return this.value;
 	    }
 	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<ShowDay> getShowdays() {
		if(sShowDays == null || "".equals(sShowDays)){
			return showdays;
		}else {
			List<ShowDay> result = JSONUtils.fromJson(sShowDays, new TypeToken<List<ShowDay>>(){});
			return result == null ? showdays : result;
		}
	}

	public void setShowdays(List<ShowDay> showdays) {
		this.showdays = showdays;
	}

	public Window getWindow() {
		if(sWindow == null || "".equals(sWindow)){
			return window;
		}else {
			Window result = JSONUtils.fromJson(sWindow, Window.class);
			return result == null ? window : result;
		}
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public String getsWindow() {
		if(window == null){
			return sWindow;
		}else {
			Gson gosn = new Gson();
			return gosn.toJson(window);
		}
	}

	public void setsWindow(String sWindow) {
		this.sWindow = sWindow;
	}

	public String getsShowDays() {
		if(showdays == null || showdays.size() == 0){
			return sShowDays;
		}else {
			Gson gosn = new Gson();
			return gosn.toJson(showdays);
		}
	}

	public void setsShowDays(String sShowDays) {
		this.sShowDays = sShowDays;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public List<Canvas> getMaindata() {
		return maindata;
	}

	public void setMaindata(List<Canvas> maindata) {
		this.maindata = maindata;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
}

