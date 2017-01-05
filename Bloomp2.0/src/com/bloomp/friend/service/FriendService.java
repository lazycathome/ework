package com.bloomp.friend.service;

import java.util.List;

import com.bloomp.account.entity.Account;
import com.bloomp.friend.entity.Friend;

public interface FriendService {

	List<Account> queryForCreator(long creator, long lastQueryTime);
	
	List<Long> save(List<Friend> friends);
	
	int updateGroup(long groupId, long targetGroupId);
	
	int removeGroup(List<Long> accountIdList, long groupId, long targetGroupId);
}
