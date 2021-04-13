package com.techelevator.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.FriendDAO;
import com.techelevator.dao.UserDAO;


@RestController
@CrossOrigin
@PreAuthorize("IsAuthenticated()")
public class FriendController {
	private FriendDAO friendDAO;
	private UserDAO userDAO;
	public FriendController(FriendDAO friendDAO, UserDAO userDAO) {
		this.friendDAO = friendDAO;
		this.userDAO = userDAO;
	}
	
	@RequestMapping(value = "friend/", method = requestMethod.GET)
}
