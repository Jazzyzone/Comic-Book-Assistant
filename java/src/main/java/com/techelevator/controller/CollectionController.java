package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.CollectionDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.ComicDTO;
import com.techelevator.model.CollectionDTO;
import com.techelevator.model.FullCollectionDTO;


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
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "collection/", method = RequestMethod.GET)
	public List<CollectionDTO> getAllCollections(Principal principal){
		if(principal!=null) {
			return collectionDAO.getAllCollectionList();
		}else {
			return collectionDAO.getAllPublicCollectionList();
		}
	}
	@RequestMapping(value = "collection/user/{user}", method = RequestMethod.GET )
    public List<CollectionDTO> getCollections(@PathVariable String user , Principal principal) {
		//userID of -1 is a anon user
		int userID = -1;
		if(principal != null) {
			userID = userDAO.findIdByUsername(principal.getName());
		}
		int collectionUserID = -1;
		try {
			collectionUserID = userDAO.findIdByUsername(principal.getName());
		}catch(RuntimeException rex) {
			collectionUserID = -1;
		}

    	return collectionDAO.getCollectionList(userID,collectionUserID);
    }
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "collection/{id}", method = RequestMethod.GET )
    public FullCollectionDTO getCollection(@PathVariable long id , Principal principal) {
		//userID of -1 is a anon user
		int userID = -1;
		if(principal != null) {
			userID = userDAO.findIdByUsername(principal.getName());
		}
    	return collectionDAO.getCollection(id,userID);
    }
	//Creates a blank collection is my guess, maybe it can pass in a list of comics already attached but tbh I don't think that is how I will handle it.
	@RequestMapping(value = "collection/", method = RequestMethod.POST )
    public boolean addCollection(@RequestBody CollectionDTO collection, Principal principal) {

		int userID = userDAO.findIdByUsername(principal.getName());
    	return collectionDAO.addCollection(collection,userID);
    }
	/*
	 * This will just update the name and the privacy settings of the Collection
	 */
	@RequestMapping(value = "collection/{id}", method = RequestMethod.PUT )
    public boolean updateCollection(@RequestBody CollectionDTO collection, @PathVariable long id ,Principal principal) {
		int userID = userDAO.findIdByUsername(principal.getName());
    	return collectionDAO.updateCollection(collection,userID);
    }
    //Deletes the whole collection and removes references to comics, if the comics are orphaned then we remove the comics from our db
	@RequestMapping(value = "collection/{id}", method = RequestMethod.DELETE)
    public boolean deleteCollection(@PathVariable long id  ,Principal principal) {
		int userID = userDAO.findIdByUsername(principal.getName());
    	return collectionDAO.deleteCollection(id,userID);
	}
	//Anyone can look at comic details? Might end up unused since we can get better info from the Marvel API
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "comic/{id}", method = RequestMethod.GET)
	public ComicDTO getComic(@PathVariable long id) {
		return collectionDAO.getComic(id);
	}
	
	
	//Removes a comic from the Collection and if the comic is orphaned and no longer referenced removes it's data as well
	@RequestMapping(value = "collection/{collectionID}/comic/{comicID}", method = RequestMethod.DELETE)
	public boolean removeComic(@PathVariable long collectionID, @PathVariable long comicID,Principal principal) {
		int userID = userDAO.findIdByUsername(principal.getName());
	
		return collectionDAO.removeComic(collectionID,comicID,userID);
	}
	//Add a comic to the collection, checking first if there is information on the comic before adding it to our table.
	@RequestMapping(value = "collection/{collectionID}/comic/", method = RequestMethod.POST)
	public boolean addComic(@PathVariable long collectionID, @RequestBody ComicDTO comic,Principal principal) {
		int userID = userDAO.findIdByUsername(principal.getName());
		return collectionDAO.addComicToCollection(collectionID,comic,userID);
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
