package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.FriendDTO;

@Service
public class FriendDAO {
	private JdbcTemplate jdbcTemplate;
	
	public FriendDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<FriendDTO> getAllFriendID() {

		List<FriendDTO> friendDTO = new ArrayList<>();
		String sqlFindFriendID = "SELECT f.* FROM friend f " +
				"WHERE fu.user_id = ? ORDER by f.friend_id ";
		SqlRowSet friendRow = jdbcTemplate.queryForRowSet(sqlFindFriendID);
		while (friendRow.next()) {
			friendDTO.add(mapRowToFriend(friendRow));
		}

		return friendDTO;
	}
	public boolean addFriend(FriendDTO friend, int userID) {
		
		String sqlInsertFriend = "INSERT INTO friend (user_id, friend_id, status_id, status_id_desc) " +
								 "VALUES (?, ?, ?, ?)";
		
		try {
			
			jdbcTemplate.update(sqlInsertFriend,friend.getUserID(),
					friend.getFriendID(), friend.getStatusID(), friend.getStatusIDDesc());
			
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
		
	private FriendDTO mapRowToFriend(SqlRowSet friendRow) {
		FriendDTO friend = new FriendDTO();
		friend.setFriendID(friendRow.getInt("friend_id"));
		friend.setUserID(friendRow.getInt("user_id"));
		friend.setStatusID(friendRow.getInt("status_id"));
		friend.setStatusIDDesc(friendRow.getString("status_id_desc"));
		return friend;
	}
	
}




