package cn.bigdb.smartscreen.contorller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.bigdb.smartscreen.service.IUserManager;

@Controller
public class HelloworldController {

	@Resource(name="userManager")  
    private IUserManager userManager; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/springmvc/helloworld", method = RequestMethod.GET)
	public String helloworld(Model model) {
		model.addAttribute("msg", "springmvc helloworld例子演示成功啦...");
		userManager.queryList(null);
		return "helloworld";
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}
	
	
}