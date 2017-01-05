package com.bloomp.api.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.account.entity.Account;
import com.bloomp.account.service.AccountService;

@Path("/api/bloomp/accounts/usernames/?$")
public class AccountsUserNameManager {

	@Autowired
	private AccountService accountService;

	@GET
	public ApiResult get(@QueryParam("userNames") String userNames, @QueryParam("accountId") String accountId ){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(userNames) || StringUtils.isBlank(accountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		
		String[] tUserNames = userNames.split(",");
		List<Long> lUserNames = new ArrayList<Long>(tUserNames.length);
		for(String userName : tUserNames){
			lUserNames.add(Long.parseLong(userName));
		}
	
		List<Account> accounts = accountService.queryForUserNames(lUserNames);
		
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("results", accounts);
		
		return new JsonResult(result);
	}
}
