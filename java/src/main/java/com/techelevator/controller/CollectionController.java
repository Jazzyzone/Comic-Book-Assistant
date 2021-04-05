package com.techelevator.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.CollectionDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.CollectionDTO;
import com.techelevator.model.ComicDTO;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CollectionController {
	private CollectionDAO collectionDAO;
	private UserDAO userDAO;
	public CollectionController(CollectionDAO collectionDAO, UserDAO userDAO) {
		this.collectionDAO = collectionDAO;
		this.userDAO = userDAO;
	}
	/*
	 * Do I need individual comic endpoints for add and up
	 */
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "collection/{id}", method = RequestMethod.GET )
    public CollectionDTO getCollection(@PathVariable long id , Principal principal) {
		//userID of -1 is a anon user
		int userID = -1;
		if(principal != null) {
			userID = userDAO.findIdByUsername(principal.getName());
		}
    	return collectionDAO.getCollection(id,userID);
    }
	@RequestMapping(value = "collection/", method = RequestMethod.POST )
    public boolean addCollection(@RequestBody CollectionDTO collection, Principal principal) {

		int userID = userDAO.findIdByUsername(principal.getName());
    	return collectionDAO.addCollection(collection,userID);
    }
	@RequestMapping(value = "collection/{id}", method = RequestMethod.PUT )
    public boolean updateCollection(@RequestBody CollectionDTO collection, @PathVariable long id ,Principal principal) {
		int userID = userDAO.findIdByUsername(principal.getName());
    	return collectionDAO.updateCollection(collection,userID);
    }
	@RequestMapping(value = "collection/{id}", method = RequestMethod.DELETE)
    public boolean deleteCollection(@PathVariable long id  ,Principal principal) {
		int userID = userDAO.findIdByUsername(principal.getName());
    	return collectionDAO.deleteCollection(id,userID);
	}
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "comic/{id}", method = RequestMethod.GET)
	public ComicDTO getComic(@PathVariable long id) {
		return collectionDAO.getComic(id);
	}
	/*
	 * Both of these two will probably change substantially once I get back the data, I'm thinking a hashmap that ties the search criteria to a value but we shall see.
	 */
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "stats/{type}", method = RequestMethod.GET)
	public int getAggregateStats(@PathVariable String type){
		//TODO: Unstub this and figure out how we are returning it. It might make more sense to have an object with all of the aggregate criteria
		return 10;
	}
	@RequestMapping(value = "collection/{id}/stats", method = RequestMethod.GET)
	public int getCollectionStats(@PathVariable long id , Principal principal){
		//TODO: Unstub this and figure out how we are returning it. It might make more sense to have an object with all of the stats
		int userID = userDAO.findIdByUsername(principal.getName());
		return 10;
	}
	
}
