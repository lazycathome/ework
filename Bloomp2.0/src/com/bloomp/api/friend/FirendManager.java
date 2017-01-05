package com.bloomp.api.friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jedisoft.framework.annotations.BodyParam;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.PUT;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

import com.bloomp.Code;
import com.bloomp.account.entity.Account;
import com.bloomp.friend.entity.Friend;
import com.bloomp.friend.service.FriendService;

@Path("/api/bloomp/friends/?$")
public class FirendManager {

	@Autowired
	private FriendService friendService;
	
	@GET
	public ApiResult get(@QueryParam("accountId") String sAccountId, @QueryParam("lastQueryTime") String sLastQueryTime){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sAccountId)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
		}else{
			long creator = Long.parseLong(sAccountId);
			long lastQueryTime  = StringUtils.isBlank(sLastQueryTime) ? 0 : Long.parseLong(sLastQueryTime);
			List<Account> accounts = friendService.queryForCreator(creator, lastQueryTime);
			result.put("code", Code.SUCCESS);
			result.put("message", Code.SUCCESS_MESSAGE);
			result.put("lastQueryTime", System.currentTimeMillis());
			result.put("results", accounts);
		}
		return new JsonResult(result);
	}
	
	@PUT
	public ApiResult create(@BodyParam("accountId") String sAccountId, @BodyParam("friendIds") String sFriendIds){
		Map<String, Object> result = new HashMap<String, Object>();
		if(StringUtils.isBlank(sAccountId) || StringUtils.isBlank(sFriendIds)){
			result.put("code", Code.PARAM_ILLEGAL);
			result.put("message", Code.PARAM_ILLEGAL_MESSAGE);
		}else{
			long creator = Long.parseLong(sAccountId);
			String[] fs = sFriendIds.split(",");
			List<Friend> friends = new ArrayList<Friend>();
			long time = System.currentTimeMillis();
			for(String f : fs){
				Friend friend = new Friend();
				friend.setAccountId(Long.parseLong(f));
				friend.setCreateTime(time);
				friend.setCreator(creator);
				friend.setState(1);
				friend.setUpdateTime(time);
				friends.add(friend);
			}
			List<Long> fIds = friendService.save(friends);
			if(fIds.size() > 0){
				result.put("code", Code.SUCCESS);
				result.put("message", Code.SUCCESS_MESSAGE);
			}else{
				result.put("code", Code.ERROR);
				result.put("message", Code.ERROR_MESSAGE);
			}
		}
		return new JsonResult(result);
	}
	
}
