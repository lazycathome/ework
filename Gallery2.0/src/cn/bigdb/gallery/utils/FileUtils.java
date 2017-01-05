package cn.bigdb.gallery.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.bigdb.gallery.common.Constants;
import cn.bigdb.gallery.common.WebContext;

public class FileUtils {

	static String 	RESOURCE_DATA = "gallery.js";
	
	public String createJsonFile(String equipInfoId, String fileContent){
		String path = this.getContextPath()+"josndata";
		File pathFile = new File(path);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		path = this.getContextPath()+"josndata"+File.separator+equipInfoId;
		pathFile = new File(path);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		path = this.getContextPath()+"josndata"+File.separator+equipInfoId+File.separator+"data";
		pathFile = new File(path);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		String filename = path+ File.separator +RESOURCE_DATA;
		File file = new File(filename);
		try {
			file.createNewFile();
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(  
	                new FileOutputStream(file), "utf-8"));  
	        writer.write(fileContent);  
			
	        writer.close();
			return filename;
		} catch (IOException e) {
			System.out.println("写设备json文件失败");
		}
		return null;
	} 
	
	public List<String> getUpgradeFiles(long lastTime){
		List<String> folders = getFolders(lastTime);
		List<String> result = new ArrayList<String>();
		for(String filePath : folders){
			result.addAll(this.getFiles(filePath, lastTime));
		}
		return result;
	}
	
	/**
	 * @param filePath
	 * @param lastTime
	 * @return
	 */
	private List<String> getFiles(String filePath, long lastTime){
		File folder = new File(filePath);
		File[] folders = folder.listFiles();
		List<String> result = new ArrayList<String>();
		for(File f : folders){
			if(f.isFile()){
				long createTime = f.lastModified();
				if(createTime > lastTime){
					result.add(filePath+File.separator+f.getName());
				}
			}
		}
		return result;
	}
	
	public List<String> spliceResourceFilePath(List<String> files, long lastTime){
		List<String> result = new ArrayList<String>();
		for(String file : files){
			String filename = getContextPath()+file;
			File f = new File(filename);
			if(f.exists()){
				if(f.lastModified() > lastTime){
					result.add(filename);
				}
			}
		}
		files = null;
		return result;
	}
	
	/**
	 * @param lastTime 
	 * @return
	 */
	private List<String> getFolders(long lastTime){
		List<String> result = new ArrayList<String>();
		
		File folder = new File(getContextPath()+File.separator+Constants.RESOURCEFOLDER);
		File[] folders = folder.listFiles();
		for(File f : folders){
			if(f.isDirectory()){
				long createTime = f.lastModified();
				if(createTime > lastTime){
					result.add(f.getPath());
				}
			}
		}
		return result;
	}
	
	public String getContextPath(){
//		String path = FileUtils.class.getResource("/").getPath();
//		File folder = new File(path);
//		return folder.getParentFile().getParentFile().toString();
		return WebContext.getRealPath();
	}
	
	/**
	 * @return 返回文件上传保存的文件夹目录路径
	 */
	public String getPathName(){ 
		Date now = new Date();
		String nowStr = DateUtil.geDateByFormat(now, "yyyy-MM-dd");
		String filePath = File.separator +Constants.RESOURCEFOLDER + File.separator + nowStr;
		File file = new File(getContextPath()+filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		return filePath;
	}
	
	public String saveEquipImg(MultipartFile mFile){
		String filePath = this.getFilePath(Constants.EQUIPIMG, mFile.getOriginalFilename());
		return this.saveFile(mFile, filePath);
	}
	
	public String saveResourceFile(MultipartFile mFile){
		String filePath = this.getFilePath(getPathName(), mFile.getOriginalFilename());
		return this.saveFile(mFile, filePath);
	}
	
	public String getFilePath(String folder, String fileName){
		String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();  
		String filePath = folder+ File.separator + System.currentTimeMillis()+suffix;
		return filePath;
	}
	
	 /** 保存file
	 * @param mFile 从页面中读取到的文件
	 * @param filePathName 文件保存路径
	 * @return 文件名
	 */
	private String saveFile(MultipartFile mFile, String filePath) {

		String fileFullPath =  WebContext.getRealPath()+filePath;
		File file = new File(fileFullPath, "utf-8");
		try {
			mFile.transferTo(file);//保存文件
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     return filePath;
	}
}
