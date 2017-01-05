package com.bloomp.service.core.images;

import cn.gaiay.storage.StorageFile;
import cn.gaiay.storage.StorageRoot;

import com.bloomp.utils.PathUtils;

/**
 * 图片服务的缩放核心
 * @author azhi
 *
 */

public class ResizeFunction {

	private StorageRoot storage;

	public String resize(String target, int width, int height, boolean scale) {
		storage = new StorageRoot();
		StorageFile file = storage.locate(target);
		if(file != null) {
			String fileName = file.getPath();
//			String fileExt = getFileExt(fileName);
			String newName = fileName+"_r" + width + "x" + height;
			if(fileName.lastIndexOf("_c") > 0){
				StringBuffer strName = new StringBuffer(fileName);
				newName = strName.substring(0, strName.lastIndexOf("_c")).toString()+"_r" + width + "x" + height;
			}
			StorageFile resizeFile = storage.asign(newName);
			Image image = new Image();

			image.read(fileName);
			image.resize(width, height, scale);
			image.saveAs(resizeFile.getPath());
			String fileUrl = resizeFile.getUrl();
			
			return fileUrl;

		}
		return "";
	}	
	
}
