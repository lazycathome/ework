package com.bloomp.group.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloomp.friend.service.FriendService;
import com.bloomp.group.service.GroupService;

@Service
public class GroupBizImpl implements GroupBiz {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private FriendService friendService;
	
	@Override
	@Transactional
	public int delete(long id, long creaotr) {
		
		return 0;
	}

}
