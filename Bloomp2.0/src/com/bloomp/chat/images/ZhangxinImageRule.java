package com.bloomp.chat.images;

import java.util.ArrayList;
import java.util.List;

import com.bloomp.service.images.CutCommand;
import com.bloomp.service.images.Image;
import com.bloomp.service.images.ImageCommand;
import com.bloomp.service.images.ResizeCommand;

public class ZhangxinImageRule {

	private int maxWidth = 240;
	private int minWidth = 80;
	private int maxHeight = 240;
	private int minHeight = 80;
	private int maxScale = 3;

	public List<ImageCommand> getRuleCommand(String localFileUrl){
		Image image = new Image();
		image.read(localFileUrl);
		int[] realSizes = image.getImageRealSize();
		int width = realSizes[0];
		int height = realSizes[1];
		List<ImageCommand> results = new ArrayList<ImageCommand>();
		ImageCommand result = new ImageCommand();
		if(width > height){
			result =  wRectangle(width, height, localFileUrl);
		}else if(width < height){
			result = hRectangle(width, height, localFileUrl);
		}else if(width == height){
			result = square(width, height, localFileUrl);
		}
		results.add(result);
		return results;
	}
	
	private ImageCommand square(int width, int height, String destFileUrl){
		ImageCommand command = new ImageCommand();
		int w = width;
		int h = height;
		if(width > maxWidth){
			w = maxWidth;
			h = maxHeight;
		}else if(width < minWidth){
			w = maxWidth;
			h = maxHeight;
		}
		
		command.setResizeCommand(new ResizeCommand(w, h, destFileUrl, true));
		
		return command;
	}
	
	private ImageCommand hRectangle(int width, int height, String destFileUrl){
		double scale = (double)height / width;
		ImageCommand command = new ImageCommand();
		int w = width;
		int h = height;
		//高宽的比例大于3
		if(scale > maxScale){
			int top = (height - width*maxScale)/2;
			
			command.setCutCommand(new CutCommand(0, top, width, width*maxScale, destFileUrl));
//			ImageResult cutResult = imageService.cut(destFileUrl, 0, top, width, width*maxScale);
			destFileUrl = "";
			w = minWidth;
			h = maxHeight;
		}
		////高宽的比例大于1小于3
		else{
			//宽度大于最大宽度 或者 宽度大于最小宽度且小于最大宽度，而且高度大于最大高度
			if(width > maxWidth || height > maxHeight){
				double tempScale = (double)maxHeight / height;
				h = maxHeight;
				w = (int) (width*tempScale);
			}
			//宽度小于最小宽度
			else if(width < minWidth){ 
				double tempScale = (double)minWidth / width;
				h = (int)(height*tempScale);
				w = minWidth;
			}
		}
		command.setResizeCommand(new ResizeCommand(w, h, destFileUrl, true));
		return command;
	}

	private ImageCommand wRectangle(int width, int height, String destFileUrl){
		double scale = (double)width / height;
		ImageCommand command = new ImageCommand();
		int w = width;
		int h = height;
		//宽高的比例大于3
		if(scale > maxScale){
			int left = (width - height*maxScale)/2;

			command.setCutCommand(new CutCommand(left, 0, h * maxScale, h, destFileUrl));

			destFileUrl = "";
			w = maxWidth;
			h = minHeight;
		}
		////高宽的比例大于1小于3
		else{
			//高度大于最大高度 或者 高度大于最小高度且小于最大高度，而且宽度大于最大宽度
			if(height > maxHeight){
				double tempScale = (double)maxWidth / width;
				h = (int) (height*tempScale);
				w = maxWidth;
			}
			//高度小于最小高度
			else if(height < minHeight){ 
				double tempScale = (double)minHeight / height;
				w = (int)(width*tempScale);
				h = minHeight;
			}
		}
		//imageResult = imageService.resize(destFileUrl, w, h, true);
		
		command.setResizeCommand(new ResizeCommand(w, h, destFileUrl, true));
		return command;
	}
	
}
