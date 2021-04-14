package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.CollectionDTO;
import com.techelevator.model.FriendDTO;

@Service
public class FriendDAO {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<FriendDTO> getAllFriendID() {

		List<FriendDTO> friendDTO = new ArrayList<>();
		String sqlFindFriendID = "SELECT f.* FROM friend f " +
				"INNER JOIN friend_user fu ON fu.friend_id = f.friend_id " +
				"WHERE fu.user_id = ? ORDER by f.friend_id ";
		SqlRowSet friendRow = jdbcTemplate.queryForRowSet(sqlFindFriendID);
		while (friendRow.next()) {
			friendDTO.add(mapRowToFriend(friendRow));
		}

		return friendDTO;
	}
	
//	public FriendDTO findFriendByFriendID(int friendID) {
//		FriendDTO findFriendID = null;
//		String sqlFindFriendID = "SELECT * FROM friend WHERE friend_id = ?";
//		SqlRowSet findFriendIDRow = jdbcTemplate.queryForRowSet(sqlFindFriendID, friendID);
//		if (findFriendIDRow.next()) {
//			findFriendID = mapRowToFindFriendByFriendID(findFriendIDRow);
//		}
//		return findFriendID;
//	}
	
//	public FriendDTO mapRowToFindFriendByFriendID(SqlRowSet row) {
//		FriendDTO mapRowToFindFriendByFriendID = new FriendDTO();
//		mapRowToFindFriendByFriendID.setFriendByFriendID(row.getInt("friend_id"));
//		
//		return mapRowToFindFriendByFriendID;
//	}
		
	private FriendDTO mapRowToFriend(SqlRowSet friendRow) {
		FriendDTO friend = new FriendDTO();
		friend.setFriendID(friendRow.getInt("friend_id"));
		friend.setUserID(friendRow.getInt("user_id"));
		friend.setStatusID(friendRow.getInt("status_id"));
		friend.setStatusIDDesc(friendRow.getString("status_id_desc"));
		return friend;
	}
	
}




