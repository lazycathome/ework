package com.bloomp.service.core.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bloomp.service.storage.StorageResult;
import com.bloomp.utils.PathUtils;


/**
 * @description:文件本地存储
 * 	
 * @author 
 * @date:2014-5-14
 */
public class LocalFileStorage{
	protected Logger logger = LoggerFactory.getLogger(LocalFileStorage.class);
	
	public StorageResult saveFile(String saveDirPath, String saveFileName, File file) {
		InputStream is =null; 
		try {
			is = new FileInputStream(file);
			return this.saveFile(saveDirPath, saveFileName, is, true);
		} catch (Exception e) {
			
		} 
		return  new StorageResult(-1, "本地上传图片出错");
	}
	
	public StorageResult saveFile(String saveDirPath, String saveFileName, InputStream is, boolean isPath) {
		StorageResult result = new StorageResult(0, "成功");

		if(isPath){
			//创建文件夹
			this.createFolder(PathUtils.getWebPath(), saveDirPath+saveFileName);
		}
		String pathUrl = isPath ? (PathUtils.getWebPath() + saveDirPath) : saveDirPath;
		logger.info("storagesFile:"+pathUrl);
		try {
			// 把文件写入到上面设置的路径里
			File file = new File(pathUrl, saveFileName);
			Streams.copy(is, new FileOutputStream(file), true);
			result.setUrl(saveDirPath+saveFileName);
		} catch (Exception e) {
			logger.error("本地上传图片出错", e);
			result = new StorageResult(-1, "本地上传图片出错");
		}
		return result;
	}
	
	private boolean createFolder(String realPath, String filePath){
		File file = new File(realPath+filePath);
		File dir = new File(file.getParent());

		if(!dir.exists() || !dir.isDirectory()) {
			dir.mkdirs();
		}
		return true;
	}
	
	public void deleteFile(File file) {
		if(file != null){
			file.delete();
		}
//		FileUtils.deleteQuietly(file);
	}
	
}
