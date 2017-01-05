package cn.bigdb.smartscreen.contorller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.MessageConstants;
import cn.bigdb.smartscreen.model.User;
import cn.bigdb.smartscreen.service.IUserManager;
import cn.bigdb.smartscreen.utils.DESPlus;
import cn.bigdb.smartscreen.utils.EncryptException;
import cn.bigdb.smartscreen.vo.MessageVo;

@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Resource(name="userManager")
	private IUserManager userManager;
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	@ResponseBody
	public MessageVo login(HttpServletRequest request, User user){
		String password ="";
		try {
			password = DESPlus.doEncrypt(Constants.SECRET_KEY, user.getPassword());
		} catch (EncryptException e) {
			logger.error("密码加密失败!");
		}
		String result = userManager.login(user.getUsername(), password);
		MessageVo message = new MessageVo();
		if(Constants.OP_SUCCESS.equals(result)){
			HttpSession session = request.getSession();
			session.setAttribute(Constants.ACCOUNT_SESSION_KEY, user.getUsername());
			message.setCode(MessageConstants.ACTION_SUCCESS_CODE);
			message.setMsg(MessageConstants.ACTION_SUCCESS_MSG);
		}else{
			message.setCode(MessageConstants.PWD_ERROR_CODE);
			message.setMsg(MessageConstants.PWD_ERROR_MSG);
		}
		return message;
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id,HttpServletRequest request){
		
		request.setAttribute("user", userManager.getUser(id));
	
		return "/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		
		userManager.addUser(user);
		
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletResponse response){
		
		String result = "{\"result\":\"error\"}";
		
		if(userManager.delUser(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user,HttpServletRequest request){
		
		if(userManager.updateUser(user)){
			user = (User) userManager.getUser(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		}else{
			return "/error";
		}
	}
}