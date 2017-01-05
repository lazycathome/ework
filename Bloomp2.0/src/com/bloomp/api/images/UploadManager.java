package com.bloomp.api.images;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.chat.images.ZhangxinImageRule;
import com.bloomp.service.core.images.CutFunction;
import com.bloomp.service.core.images.ResizeFunction;
import com.bloomp.service.images.CutCommand;
import com.bloomp.service.images.ImageCommand;
import com.bloomp.service.images.ResizeCommand;
import com.bloomp.utils.Upload;

@Path("/api/bloomp/images/upload/?$")
public class UploadManager {

	@POST
	public ApiResult upload(HttpServletRequest request){
		Upload upload = new Upload();
		System.out.println("图片上传开始");
		Map<String, String> map = upload.saveFile(request, Upload.images);
		System.out.println("图片上传完毕，开始剪裁");
		ImagesResult eResult = new ImagesResult();
		if(map.get("code") != null){
			int code = Integer.valueOf(map.get("code"));
			eResult.setCode(code);
			if(Code.PIC_SIZE_ILLEGAL == code){
				eResult.setMessage(Code.PIC_SIZE_ILLEGAL_MESSAGE);
			}else{
				eResult.setMessage(Code.FILE_ILLEGAL_MESSAGE);
			}
		}else{
			String sType = map.get("type");
			int type = Integer.parseInt(sType);
			String fileUrl = map.get("fileUrl");
			if(type == 2){
				String filePath = this.getFilePath(request);
				fileUrl = this.executeChatRule(this.getFilePath(request)+fileUrl);
				fileUrl = fileUrl.substring(filePath.length(), fileUrl.length());
			}
			String url = this.getServerPath(request)+fileUrl;
			eResult.setMessage(Code.SUCCESS_MESSAGE);
			eResult.setCode(Code.SUCCESS);
			eResult.setUrl(url);
		}
		return new JsonResult(eResult);
	}
	
	public String getServerPath(HttpServletRequest request){
		StringBuilder str= new StringBuilder();
		str.append(request.getScheme()).append("://");
		str.append(request.getServerName());
		if(request.getServerPort()!=80){
			str.append(":").append(request.getServerPort());
		}
		str.append(request.getContextPath());
		return str.toString();
	}

	private String filePath = "/";
	
	public String getFilePath(HttpServletRequest request){
		return request.getServletContext().getRealPath(filePath);
	}
	
	
	private String executeChatRule(String fileUrl){
		ZhangxinImageRule zhangxin = new ZhangxinImageRule();
		List<ImageCommand> commands = zhangxin.getRuleCommand(fileUrl);
		if(commands.size() > 0){
			CutFunction cutFunction = new CutFunction();
			for(ImageCommand command : commands){
				CutCommand cut = command.getCutCommand();
				ResizeCommand resize = command.getResizeCommand();
				String cFileUrl = fileUrl;
				if(cut != null){
					cFileUrl = cutFunction.cut(fileUrl, cut.getLeft(), cut.getTop(), cut.getWidth(), cut.getHeight());
				}
				if(resize != null && StringUtils.isNotBlank(cFileUrl)){
				//	imageResult = image.resize(StringUtil.isNotBlank(cuntImageResult.getUrl()) ? cuntImageResult.getUrl() : storageResult.getUrl(), resize.getWidth(), resize.getHeight(), resize.isLockScale());
					ResizeFunction rFunction = new ResizeFunction();
					String rFileUrl = rFunction.resize(cFileUrl, resize.getWidth(), resize.getHeight(), resize.isLockScale());
					if(StringUtils.isNotBlank(rFileUrl)){
						fileUrl = rFileUrl;
					}
				}
			}
		}
		return fileUrl;
	}
}
