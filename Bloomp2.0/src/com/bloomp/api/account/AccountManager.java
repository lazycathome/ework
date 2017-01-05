package com.bloomp.api.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.BodyParam;
import cn.jedisoft.framework.annotations.FormParam;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.PUT;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.Constants;
import com.bloomp.account.entity.Account;
import com.bloomp.account.service.AccountService;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Path("/api/bloomp/account/?$")
public class AccountManager {

	@Autowired
	private AccountService accountService;
	
	private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	
	@PUT
	public ApiResult create(@BodyParam("userName") String userName, @BodyParam("password") String password,
			@BodyParam("nickName") String nickName, @BodyParam("logo") String logo, 
			@BodyParam("appOs") String appOs, @BodyParam("firstName") String firstName,
			@BodyParam("lastName") String lastName, @BodyParam("country") String sCountry,
			@BodyParam("sex") String sSex, @BodyParam("orgId") String sOrgId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(password) 
				|| StringUtils.isBlank(firstName) || StringUtils.isBlank(appOs)
				|| StringUtils.isBlank(lastName)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		long phone = Long.parseLong(userName);
//		PhoneNumber number = new PhoneNumber();
//		number.setCountryCode(86).setNationalNumber(phone);
//		boolean flag = phoneUtil.isValidNumber(number);
//		if(!flag){
//			result.put("code", Code.PARAM_ILLEGAL);
//			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
//			return new JsonResult(result);
//		}
		nickName = firstName + " " + lastName;
		Account account = new Account();
		long time = System.currentTimeMillis();
		account.setLogo(logo);
		account.setNickName(nickName);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setPassword(password);
		account.setCreateTime(time);
		account.setUpdateTime(time);
		account.setUserName(phone);
		int type = 0;
		if(Constants.ANDROID.equals(appOs)){
			type = 1;
		}else if(Constants.IOS.equals(appOs)){
			type = 2;
		}
		long id = accountService.save(account, type);
		if(id >0){
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			result.put("createTime", time);
			result.put("id", id);
		}else if(id == -1){
			result.put("code", Code.USER_NAME_EXSIST);
			result.put("message", Code.USER_NAME_EXSIST_MESSAGE);
			
		}else{
			result.put("code", Code.ERROR);
			result.put("message", Code.ERROR_MESSAGE);
		}
		return new JsonResult(result);
	}
	
	@POST
	public ApiResult update(@FormParam("accountId") String sAccountId, @FormParam("nickName") String nickName, 
			@FormParam("logo") String logo, @FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sAccountId) || StringUtils.isBlank(firstName)
				 || StringUtils.isBlank(lastName)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
			return new JsonResult(result);
		}
		nickName = firstName + " " + lastName;
		long id = Long.parseLong(sAccountId);
		Account account = new Account();
		account.setLogo(logo);
		account.setNickName(nickName);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setId(id);
		id = accountService.update(account);
		if(id <= 0){
			result.put("code", Code.PASSWORD_ERROR);
			result.put("message", Code.PASSWORD_ERROR_MESSAGE);
		}else{
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			result.put("id", account.getId());
		}
		
		return new JsonResult(result);
	}
	
}
