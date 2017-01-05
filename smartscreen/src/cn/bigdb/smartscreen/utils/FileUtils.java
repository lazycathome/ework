package cn.bigdb.smartscreen.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.WebContext;

public class FileUtils {

	static String 	RESOURCE_DATA = "resourcedata.js";
	
	public String createJsonFile(String equipInfoId, String fileContent){
		String filePath = File.separator+"jsonfile"+File.separator+equipInfoId;
		File pathFile = new File(filePath);
		if(pathFile.isDirectory()){
			pathFile.mkdirs();
		}
		String filename = filePath+ File.separator +RESOURCE_DATA;
		File file = new File(this.getContextPath()+filename);
		try {
			file.createNewFile();
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(fileContent);
			output.close();
			return this.getContextPath()+filename;
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
	
	private String getContextPath(){
//		String path = FileUtils.class.getResource("/").getPath();
//		File folder = new File(path);
//		return folder.getParentFile().getParentFile().toString();
		return WebContext.getRealPath();
	}
	
	/**
	 * @return 返回文件上传保存的文件夹目录路径
	 */
	public String getPathName(){ 
		Date date = new Date(System.currentTimeMillis());
		Date now = new Date();
		String dateStr = DateUtil.geDateByFormat(date, "yyyy-MM-dd");
		String nowStr = DateUtil.geDateByFormat(now, "yyyy-MM-dd");
		if(dateStr.equals(nowStr)){
			return File.separator +Constants.RESOURCEFOLDER + File.separator + nowStr;
		}else{
			return File.separator +Constants.RESOURCEFOLDER + File.separator + dateStr;
		}
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
		String filePath = File.separator+folder+ File.separator + System.currentTimeMillis()+suffix;
		return filePath;
	}
	
	 /** 保存file
	 * @param mFile 从页面中读取到的文件
	 * @param filePathName 文件保存路径
	 * @return 文件名
	 */
	private String saveFile(MultipartFile mFile, String filePath) {

		String fileFullPath =  WebContext.getRealPath()+filePath;
		File file = new File(fileFullPath);
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
