package com.bloomp.utils;

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

import com.bloomp.Code;

public class Upload {

	private String filePath = "/upload";
	public final static String images = "images";
	
	private final static Map<String, String> imageAllowUploadFileList = new HashMap<String, String>();
	static {
		imageAllowUploadFileList.put("image/bmp", "image/bmp");
		imageAllowUploadFileList.put("image/jpeg", "image/jpeg");
		imageAllowUploadFileList.put("image/png", "image/png");
		imageAllowUploadFileList.put("image/x-icon", "image/x-icon");
		imageAllowUploadFileList.put("image/gif", "image/gif");
		imageAllowUploadFileList.put("image/webp", "image/webp");
		
	}

	public Map<String, String> saveFile(HttpServletRequest request, String uploadType) {
		ServletFileUpload upload = new ServletFileUpload();
		Map<String, String> map = new HashMap<String, String>(); 
		int size = request.getContentLength();
		//目前只判断了图片文件的大小，后期会根据不同的上传类型来判断大小
		if(size < 0 && size > this.getImgMaxSize()){
			map.put("code", Code.PIC_SIZE_ILLEGAL+"");
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
					
					/*if(images.equals(uploadType)){
						if(imageAllowUploadFileList.get(fileType) == null || ("application/octet-stream".equals(fileType))){
			    			map.put("code", Code.FILE_ILLEGAL+"");
			    			map.put("message", Code.FILE_ILLEGAL_MESSAGE);
			    			return map;
			    		}
					}*/
					
					File file = new File(fileUrl);
					Streams.copy(item.openStream(), new FileOutputStream(file), true);
					map.put("fileUrl", filePath+fileName);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
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
		return request.getServletContext().getRealPath(filePath);
	}
	
}
