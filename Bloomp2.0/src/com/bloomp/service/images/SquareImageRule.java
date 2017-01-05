package com.bloomp.service.images;

import java.util.ArrayList;
import java.util.List;

public class SquareImageRule {

	public List<ImageCommand> getRuleCommand(String localFileUrl, String widths){
		Image image = new Image();
		image.read(localFileUrl);
		int[] realSizes = image.getImageRealSize();
		int width = realSizes[0];
		int height = realSizes[1];
		List<ImageCommand> results = new ArrayList<ImageCommand>();

		if(width > height){
			results =  wRectangle(width, height, localFileUrl, widths);
		}else if(width < height){
			results = hRectangle(width, height, localFileUrl, widths);
		}else if(width == height){
			results = square(width, height, localFileUrl, widths);
		}
		return results;
	}
	
	private List<ImageCommand> square(int width, int height, String destFileUrl, String widths){
		return getImageResizeCommands(destFileUrl, widths);
	}
	
	private List<ImageCommand> hRectangle(int width, int height, String destFileUrl, String widths){
		List<ImageCommand> commands = new ArrayList<ImageCommand>();
		int y = (height - width)/2;
		ImageCommand command = new ImageCommand();
		command.setCutCommand(new CutCommand(0, y, width, width, destFileUrl));
		commands.add(command);
		commands.addAll(this.getImageResizeCommands(destFileUrl, widths));
		
		return commands;
	}
	
	private List<ImageCommand> wRectangle(int width, int height, String destFileUrl, String widths){
		List<ImageCommand> commands = new ArrayList<ImageCommand>();
		ImageCommand command = new ImageCommand();
		int x = (width - height)/2;
		command.setCutCommand(new CutCommand(x, 0, height, height, destFileUrl));
		commands.add(command);
		commands.addAll(this.getImageResizeCommands(destFileUrl, widths));
		return commands;
	}

	private String[] getScaleSize(String widths){
		return widths.split(",");
	}
	
	private List<ImageCommand> getImageResizeCommands(String destFileUrl, String widths){
		List<ImageCommand> commands = new ArrayList<ImageCommand>();
		String[] temps = this.getScaleSize(widths);
		for(String s : temps){
			ImageCommand command = new ImageCommand();
			int w = Integer.valueOf(s);
			int h = Integer.valueOf(s);
			command.setResizeCommand(new ResizeCommand(w, h, destFileUrl, true));
			commands.add(command);
		}
		return commands;
	}
	
	
	
}
