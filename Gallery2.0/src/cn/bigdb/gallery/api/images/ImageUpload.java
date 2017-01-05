package cn.bigdb.gallery.api.images;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.properties.SystemProperties;
import cn.bigdb.gallery.utils.StringUtils;
import cn.bigdb.gallery.utils.Upload;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

@Path("/api/gallery/images/upload/?$")
public class ImageUpload {
	
	@POST
	public ApiResult upload(HttpServletRequest request){
		Upload upload = new Upload();
		Map<String, String> map = upload.saveFile(request, Upload.images);
		ImagesResult eResult = new ImagesResult();
		if(map.get("code") != null){
			
			int code = Integer.valueOf(map.get("code"));
			eResult.setCode(code);
			if(StorageResultCode.PIC_SIZE_ILLEGAL == code){
				eResult.setMessage(StorageResultCode.PIC_SIZE_ILLEGAL_MESSAGE);
			}else{
				eResult.setMessage(StorageResultCode.FILE_ILLEGAL_MESSAGE);
			}
			return new JsonResult(eResult);
		}

		String fileUrl = map.get("fileUrl");
	
		String imgHosts = SystemProperties.getInstance().getProperty("gaiay.img.host", "http://127.0.0.1");
		
		if(StringUtils.isNotBlank(fileUrl)){
			eResult.setCode(Code.SUCCESS);
			eResult.setUrl(imgHosts+fileUrl);
			return new JsonResult(eResult);
		}else{
			eResult.setCode(Code.ERROR);
			return new JsonResult(eResult);
		}
	}
	
}


