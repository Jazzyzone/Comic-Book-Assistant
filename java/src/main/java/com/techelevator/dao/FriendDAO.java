package com.techelevator.dao;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;




@Service
public class FriendDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List<Friend> getAllFriends(long id) {
		return null;
	}
	
	public Friend findFriendByFriendID(int friendID) {
		Friend findFriendID = null;
		String sqlFindFriendID = "SELECT * FROM friend WHERE friend_id = ?";
		SqlRowSet findFriendIDRow = jdbcTemplate.queryForRowSet(sqlFindFriendID, friendID);
		if (findFriendIDRow.next()) {
			findFriendID = mapRowToFindFriendByFriendID(findFriendIDRow);
		}
		return findFriendID;
	}
	public boolean friendStatus(Friend friend) {
		return false;
	}
	public boolean pendingRequest(Friend friend) {
		return false;
	}
	public boolean friendRequest() {
		return false;
	}
	
	public Friend mapRowToFindFriendByFriendID(SqlRowSet row) {
		Friend mapRowToFindFriendByFriendID = new Friend();
		mapRowToFindFriendByFriendID.setFriendByFriendID(row.getInt("friend_id"));
		
		return mapRowToFindFriendByFriendID;
	}
	
}




