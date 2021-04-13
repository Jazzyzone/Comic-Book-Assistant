package com.techelevator.dao;


import javax.validation.constraints.Positive;

public class Friend {
	
	
	@Positive
	private int friendID;
	private int userID;
	private int statusID;
	private String statusIDDesc;
	
	public int getFriendByFriendID() {
		return friendID;
	}
	
	public void setFriendByFriendID(int friendID) {
		this.friendID = friendID;
	}

	public int getFriendByUserID() {
		return userID;
	}
	public void setFriendByUserID(int userID) {
		this.userID = userID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	
	public String getStatusIDDesc() {
		return statusIDDesc;
	}

	public void setpendingRequest(String statusIDDesc) {
		this.statusIDDesc = statusIDDesc;
	}


}
