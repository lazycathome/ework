package cn.bigdb.smartscreen.contorller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.bigdb.smartscreen.common.MessageConstants;
import cn.bigdb.smartscreen.utils.FileUtils;
import cn.bigdb.smartscreen.vo.MessageVo;


public class UploadController {

	@RequestMapping(value="/upload/add",method=RequestMethod.POST )
	@ResponseBody
	public MessageVo addEquip(HttpServletRequest request,HttpServletResponse response){

		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
		List<MultipartFile> list = multipartRequest.getFiles("photo"); 
		List<String> fileList = new ArrayList<String>(list.size());
		FileUtils fileUtils = new FileUtils();
		for (MultipartFile mFile : list) {
			fileList.add(fileUtils.saveEquipImg(mFile));
		}
		MessageVo message = new MessageVo();
		if(!fileList.isEmpty()){
			message.setCode(MessageConstants.ACTION_SUCCESS_CODE);
			message.setMsg(fileList.get(0));
		}else{
			message.setCode(MessageConstants.PWD_ERROR_CODE);
			message.setMsg("上传文件失败");
		}
		
		return message;
		
	}
	
}
