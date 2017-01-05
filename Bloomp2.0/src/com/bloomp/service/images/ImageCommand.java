package com.bloomp.service.images;


public class ImageCommand {
	
	private CutCommand cutCommand;
	
	private ResizeCommand resizeCommand;

	public CutCommand getCutCommand() {
		return cutCommand;
	}

	public void setCutCommand(CutCommand cutCommand) {
		this.cutCommand = cutCommand;
	}

	public ResizeCommand getResizeCommand() {
		return resizeCommand;
	}

	public void setResizeCommand(ResizeCommand resizeCommand) {
		this.resizeCommand = resizeCommand;
	}

}
