package com.bloomp.api.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.FormParam;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.Constants;
import com.bloomp.account.entity.Login;
import com.bloomp.account.service.LoginService;
import com.bloomp.utils.ChatOnlineManager;


@Path("/api/bloomp/devicetoken/?$")
public class DeviceTokenManager {

	@Autowired
	private LoginService loginService;
	
	@POST
	public ApiResult update(@FormParam("accountId") String sAccountId, @FormParam("deviceToken") String deviceToken, @FormParam("appOs") String appOs){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sAccountId) || StringUtils.isBlank(deviceToken)
				 || StringUtils.isBlank(appOs) || (deviceToken.length() != 64 && deviceToken.length() != 40 )){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
		}else{
			long id = Long.parseLong(sAccountId);
			long loginTime = System.currentTimeMillis();
			int type = 0;
			if(Constants.ANDROID.equals(appOs)){
				type = 1;
			}else if(Constants.IOS.equals(appOs)){
				type = 2;
			}
			Login login = new Login();
			login.setDeviceToken(deviceToken);
			login.setId(id);
			login.setLoginTime(loginTime);
			login.setType(type);
			loginService.update(login);
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			ChatOnlineManager.getInstance().put(id, login);
		}
		return new JsonResult(result);
	}
	
}
