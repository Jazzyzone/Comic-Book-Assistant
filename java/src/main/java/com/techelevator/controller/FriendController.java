package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.FriendDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.FriendDTO;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class FriendController {
	private FriendDAO friendDAO;
	private UserDAO userDAO;
	public FriendController(FriendDAO friendDAO, UserDAO userDAO) {
		this.friendDAO = friendDAO;
		this.userDAO = userDAO;
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "friend/", method = RequestMethod.GET)
		public List<FriendDTO> getAllFriends(Principal principal){
		if(principal != null) {
			return friendDAO.getAllFriendID();
		}	else {
			return null;
		}
	}

	
	@RequestMapping( value = "friend/", method = RequestMethod.POST )
	public boolean addFriend(@RequestBody FriendDTO friendID, Principal principal ) {
		
		int userID = userDAO.findIdByUsername(principal.getName());
		return friendDAO.addFriend(friendID, userID);
	}
}
	


