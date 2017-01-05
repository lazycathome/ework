package cn.bigdb.gallery.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import cn.bigdb.gallery.api.images.StorageResultCode;
import cn.bigdb.gallery.properties.SystemProperties;


public class Upload {

	private String filePath = "/upload";
	public final static String images = "images";
	public final static String video = "video";
	public final static String voice = "voice";
	public final static String zip = "zip";
	public final static String txt = "txt";
	
	private final static Map<String, String> imageAllowUploadFileList = new HashMap<String, String>();
	private final static Map<String, String> videoAllowUploadFileList = new HashMap<String, String>();
	private final static Map<String, String> voiceAllowUploadFileList = new HashMap<String, String>();
	private final static Map<String, String> zipAllowUploadFileList = new HashMap<String, String>();
	private final static Map<String, String> txtAllowUploadFileList = new HashMap<String, String>();
	static {
		imageAllowUploadFileList.put("image/bmp", "image/bmp");
		imageAllowUploadFileList.put("image/jpeg", "image/jpeg");
		imageAllowUploadFileList.put("image/png", "image/png");
		imageAllowUploadFileList.put("image/x-icon", "image/x-icon");
		imageAllowUploadFileList.put("image/gif", "image/gif");
		imageAllowUploadFileList.put("image/webp", "image/webp");
		voiceAllowUploadFileList.put("amr", "amr");
		txtAllowUploadFileList.put("application/octet-stream","application/octet-stream");
		zipAllowUploadFileList.put("application/zip","application/zip");
		
		videoAllowUploadFileList.put("video/mp4", "video/mp4");
		videoAllowUploadFileList.put("video/avi", "video/avi");
		videoAllowUploadFileList.put("video/rmvb", "video/rmvb");
		videoAllowUploadFileList.put("video/mpeg4", "video/mpeg4");
		videoAllowUploadFileList.put("video/qt", "video/qt");
		videoAllowUploadFileList.put("video/mov", "video/mov");
		videoAllowUploadFileList.put("video/f4v", "video/f4v");
		videoAllowUploadFileList.put("video/flv", "video/flv");
		videoAllowUploadFileList.put("video/rm", "video/rm");
		videoAllowUploadFileList.put("video/asf", "video/asf");
		videoAllowUploadFileList.put("video/wmv", "video/wmv");
		videoAllowUploadFileList.put("video/svcd", "video/svcd");
		videoAllowUploadFileList.put("video/vcd", "video/vcd");
		videoAllowUploadFileList.put("video/dvd", "video/dvd");
		videoAllowUploadFileList.put("video/mpg", "video/mpg");
	}

	public Map<String, String> saveFile(HttpServletRequest request, String uploadType) {
		ServletFileUpload upload = new ServletFileUpload();
		Map<String, String> map = new HashMap<String, String>(); 
		int size = request.getContentLength();
		//目前只判断了图片文件的大小，后期会根据不同的上传类型来判断大小
		if(size < 0 && size > this.getImgMaxSize()){
			map.put("code", StorageResultCode.PIC_SIZE_ILLEGAL+"");
			return map;
		}
		upload.setHeaderEncoding("utf-8");
		String fileUrl = "";
		try {
			FileItemIterator iter = upload.getItemIterator(request);
			
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				
				if (item.isFormField()) {
					String columnName = item.getFieldName();
					String columnValue = Streams.asString(item.openStream());
					map.put(columnName, columnValue);
				} else {
					String origName = item.getName();
					String extName = "";
					int pos = origName.lastIndexOf(".");

					if (pos != -1) {
						extName = origName.substring(pos);
					}

					String fileName = new SimpleDateFormat(
							"/yyyyMMddhhmmss-SSS").format(new Date()) + extName;
					fileUrl = this.getFilePath(request) + fileName;
					
					String fileType = item.getContentType();
					
					if(images.equals(uploadType)){
						if(imageAllowUploadFileList.get(fileType) == null || ("application/octet-stream".equals(fileType))){
			    			map.put("code", StorageResultCode.FILE_ILLEGAL+"");
			    			map.put("message", StorageResultCode.FILE_ILLEGAL_MESSAGE);
			    			return map;
			    		}
					}else if(voice.equals(uploadType)){
						if(voiceAllowUploadFileList.get(fileType) == null || ("application/octet-stream".equals(fileType))){
			    			map.put("code", StorageResultCode.FILE_ILLEGAL+"");
			    			map.put("message", StorageResultCode.FILE_ILLEGAL_MESSAGE);
			    			return map;
			    		}
					}else if(zip.equals(uploadType)){
						if(zipAllowUploadFileList.get(fileType) == null && !"application/octet-stream".equals(fileType)){
			    			map.put("code", StorageResultCode.FILE_ILLEGAL+"");
			    			map.put("message", StorageResultCode.FILE_ILLEGAL_MESSAGE);
			    			return map;
			    		}
					}else if(txt.equals(uploadType)){
						if(txtAllowUploadFileList.get(fileType) == null ){
			    			map.put("code", StorageResultCode.FILE_ILLEGAL+"");
			    			map.put("message", StorageResultCode.FILE_ILLEGAL_MESSAGE);
			    			return map;
			    		}
					}else if(video.equals(uploadType) || ("application/octet-stream".equals(fileType))){
						if(videoAllowUploadFileList.get(fileType) == null ){
			    			map.put("code", StorageResultCode.FILE_ILLEGAL+"");
			    			map.put("message", StorageResultCode.FILE_ILLEGAL_MESSAGE);
			    			return map;
			    		}
					}
					
					File file = new File(fileUrl);
					Streams.copy(item.openStream(), new FileOutputStream(file), true);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		map.put("fileUrl", fileUrl);
		return map;
	}
	
	private long getImgMaxSize(){
//		Properties pro = PropertysConstant.SYSTEM_CONFIG;
//		String tempSize = pro.getProperty("img.maxSize");
		long size = 5*1024*1024;
//		if(tempSize != null && !"".equals(tempSize)){
//			size = Long.valueOf(tempSize);
//		}
		return size;
	}
	
	public String getFilePath(HttpServletRequest request){
		Object path = SystemProperties.getInstance().get("gallery.img.tempPath");
		if(path != null && !"".equals(path.toString())){
			return path.toString();
		}else{
			return request.getServletContext().getRealPath(filePath);
		}
	}
	

	/**
	 * 视频类型
	 * 
	 * @author Dylan
	 * @email taodizhou@foxmail.com
	 * @date 2014年12月23日
	 */
	public enum VideoType {

		ZHANGXIN("zhangxin"), THREE_M("3f");

		private final String value;

		VideoType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	
}
