package com.bloomp.friend.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.account.entity.Account;
import com.bloomp.account.service.AccountService;
import com.bloomp.friend.dao.FriendDao;
import com.bloomp.friend.entity.Friend;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public List<Account> queryForCreator(long creator, long lastQueryTime) {
		try {
			List<Friend> friends = friendDao.query(creator, lastQueryTime);
			if(friends.size() > 0){
				List<Long> ids = new ArrayList<Long>();
				List<Long> addIds = new ArrayList<Long>();
				for(Friend friend : friends){
					if(friend.getUpdateTime() >= lastQueryTime){
						addIds.add(friend.getAccountId());
					}else{
						ids.add(friend.getAccountId());
					}
				}
				List<Account> result = new ArrayList<>();
				if(ids.size() >= 1){
					result = accountService.queryForIds(ids, lastQueryTime);
				}
				if(addIds.size() >= 1){
					result.addAll(accountService.queryForIds(addIds));
				}
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<Account>();
	}

	@Override
	public List<Long> save(List<Friend> friends) {
		List<Long> result = new ArrayList<Long>();
		try {
			long maxId = friendDao.save(friends);
			
			for(int i = (friends.size()-1); i >= 0; i--){
				result.add(maxId-i);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateGroup(long groupId, long targetGroupId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeGroup(List<Long> accountIdList, long groupId,
			long targetGroupId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
