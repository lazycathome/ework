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

@Path("/api/bloomp/accounts/?$")
public class AccountsManager {

	@Autowired
	private AccountService accountService;
	
	@POST
	public ApiResult get(@QueryParam("ids") String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(ids)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		String[] tIds = ids.split(",");
		List<Long> pIds = new ArrayList<Long>();
		for(String tId : tIds){
			pIds.add(Long.parseLong(tId));
		}
		
		List<Account> accounts = accountService.queryForIds(pIds);
		
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("results", accounts);
		
		return new JsonResult(result);
	}
	
	@GET
	public ApiResult get(@QueryParam("keywords") String keywords, @QueryParam("accountId") String accountId ){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(keywords) || StringUtils.isBlank(accountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
	
		List<Account> accounts = accountService.queryForUserName(Long.parseLong(keywords));
		
		result.put("code", Code.SUCCESS);
		result.put("message", Code.SUCCESS_MESSAGE);
		result.put("results", accounts);
		
		return new JsonResult(result);
	}
}
