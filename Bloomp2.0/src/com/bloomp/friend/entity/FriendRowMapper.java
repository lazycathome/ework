package com.bloomp.friend.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FriendRowMapper implements RowMapper<Friend> {

	@Override
	public Friend mapRow(ResultSet rs, int i) throws SQLException {
		Friend friend = new Friend();
		friend.setId(rs.getLong("id"));
		friend.setAccountId(rs.getLong("accountId"));
		friend.setCreateTime(rs.getLong("createTime"));
		friend.setCreator(rs.getLong("creator"));
		friend.setState(rs.getInt("state"));
		friend.setUpdateTime(rs.getLong("updateTime"));
		friend.setGroupId(rs.getLong("groupId"));
		return friend;
	}

	
}
