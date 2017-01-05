package com.bloomp.service.core.images;

import cn.gaiay.storage.StorageFile;
import cn.gaiay.storage.StorageRoot;

import com.bloomp.utils.PathUtils;

/**
 * 图片服务的裁剪核心
 * @author azhi
 *
 */
public class CutFunction {

	private StorageRoot storage;

	/**
	 * 裁剪请求的处理方法
	 * @param target
	 * @param x
	 * @param y
	 * @param cx
	 * @param cy
	 * @return 返回裁剪之后的文件路径
	 */
	public String cut(String target, int x, int y, int cx, int cy) {
		storage = new StorageRoot();
		String path = PathUtils.getWebPath();
		StorageFile file = storage.locate(path+target);
		System.out.println("cut+path+target:"+path+target);
		if(file != null) {
			String fileName = file.getPath();
			String newName = fileName +"_c"+ cx + "x" + cy;
//			String fileExt = getFileExt(fileName);
//			String newName = "-c" + cx + "x" + cy + fileExt;
			System.out.println("cut+path+newName:"+newName);
			StorageFile cutFile = storage.asign(newName);
			Image image = new Image();
			image.read(fileName);
			image.cut(x, y, cx, cy);
			image.saveAs(cutFile.getPath());
			String fileUrl = cutFile.getUrl();
			if(fileUrl != null && !"".equals(fileUrl)){
				fileUrl = fileUrl.replaceAll(new String(path), "");
			}
			return fileUrl;
		} 
        return "";
	}
	
	public static void main(String[] args){
		String fileUrl = "/E:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GaiayServices/zhangmen/touxiang/images/20140807/012556-383.png_c800x800";
		String path = "/E:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GaiayServices";
		if(fileUrl != null && !"".equals(fileUrl)){
			fileUrl = fileUrl.replaceAll(path, "");
		}
		System.out.println(fileUrl);
	}
	
}
