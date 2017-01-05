package cn.bigdb.gallery.api.images;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.bigdb.gallery.properties.SystemProperties;
import cn.bigdb.gallery.utils.Upload;
import cn.bigdb.gallery.utils.ZIPUtils;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

/**
 * @author liujt
 * @date 2014年11月10日
 * @mail liujiangtao@gaiay.cn
 */
@Path("/api/gallery/images/zip-upload/?$")
public class ZipUpload {

	@POST
	public ApiResult zipUpload(HttpServletRequest request){
		String fileUrl = "";
		List<String> fileList = new ArrayList<String>();
		try{
			Upload upload = new Upload();
			Map<String, String> map = upload.saveFile(request, Upload.zip);
			ImagesResult result = new ImagesResult();
			if(map.get("code") != null){
				int code = Integer.valueOf(map.get("code"));
				result.setCode(code);
				result.setMessage(StorageResultCode.FILE_ILLEGAL_MESSAGE);
				return new JsonResult(result);
			}
			fileUrl = map.get("fileUrl");
			fileList = ZIPUtils.unZip(fileUrl, new File(fileUrl).getParentFile().getAbsolutePath()+File.separator);
			if(fileList != null){
				String tHosts = SystemProperties.getInstance().getProperty("gallery.img.tempPath");
				String hosts = SystemProperties.getInstance().getProperty("gallery.img.host");
				List<String> results = new ArrayList<String>(fileList.size());
				for(int i = 0; i < fileList.size(); i++){
					results.add(fileList.get(i).replace(tHosts, hosts));
				}
				result.setUrls(results);
			}
			result.setUrls(fileList);
			result.setCode(0);
			result.setMessage("数据包上传成功");
			return new JsonResult(result);
		}catch(Exception e){
			e.printStackTrace();
			ImagesResult result = new ImagesResult();
			result.setCode(1);
			result.setMessage("数据包上传失败");
			return new JsonResult(result);
		}
	}
	
}
