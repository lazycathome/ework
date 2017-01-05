package com.bloomp.api.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.BodyParam;
import cn.jedisoft.framework.annotations.DELETE;
import cn.jedisoft.framework.annotations.FormParam;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.Constants;
import com.bloomp.account.entity.Account;
import com.bloomp.account.entity.Login;
import com.bloomp.account.service.AccountService;
import com.bloomp.account.service.LoginService;

@Path("/api/bloomp/login/?$")
public class LoginManager {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LoginService loginService;
	
	@POST
	public ApiResult login(@FormParam("userName") String userName, @FormParam("password") String password, 
			@FormParam("appOs") String appOs, @BodyParam("deviceToken") String deviceToken){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)
				|| StringUtils.isBlank(appOs)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		int type = 0;
		if(Constants.ANDROID.equals(appOs)){
			type = 1;
		}else if(Constants.IOS.equals(appOs)){
			type = 2;
		}
		Account account = accountService.login(Long.parseLong(userName), password, type);
		if(account.isEmpty()){
			result.put("code", Code.PASSWORD_ERROR);
			result.put("message", Code.PASSWORD_ERROR_MESSAGE);
		}else{
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			result.put("account", account);
		}
		
		return new JsonResult(result);
	}
	
	@DELETE
	public ApiResult login(@QueryParam("accountId") String sAccountId, @QueryParam("appOs") String appOs){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sAccountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		int type = 0;
		if(Constants.ANDROID.equals(appOs)){
			type = 1;
		}else if(Constants.IOS.equals(appOs)){
			type = 2;
		}
		Login login = new Login();
		login.setDeviceToken("");
		login.setId(Long.parseLong(sAccountId));
		login.setLoginTime(System.currentTimeMillis());
		login.setType(type);
		int code = loginService.update(login);
		if(code <= 0){
			result.put("code", Code.PASSWORD_ERROR);
			result.put("message", Code.PASSWORD_ERROR_MESSAGE);
		}else{
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
		}
		
		return new JsonResult(result);
	}
}
