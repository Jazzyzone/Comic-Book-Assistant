package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.CollectionDTO;
import com.techelevator.model.ComicDTO;
import com.techelevator.model.CreateCollectionDTO;

@Service
public class CollectionDAO {
	private JdbcTemplate jdbcTemplate;

    public CollectionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public CollectionDTO getCollection(long id,int userID) {
    	//TODO: unstub this
    	ComicDTO com = new ComicDTO();
    	com.setName("Amazing Testor");
    	com.setId(1);
    	com.setCharacters(new String[] {"Testy","TestLass"});
    	com.setCreators(new String[] {"Shawn", "Jasmine"});
    	com.setIssueNumber(404);
    	com.setPublisher("TE Comics");
    	com.setThumbnailLink("none");
    	ComicDTO com2 = new ComicDTO();
    	com2.setName("Amazing Testor 2");
    	com2.setId(2);
    	com2.setCharacters(new String[] {"Testy","TestLass"});
    	com2.setCreators(new String[] {"Dennis", "JP"});
    	com2.setIssueNumber(405);
    	com2.setPublisher("TE Comics");
    	com2.setThumbnailLink("none");
    	CollectionDTO col = new CollectionDTO();
    	col.setName("Test Collection");
    	col.setComics(new ComicDTO[] {com,com2});
    	col.setPublic(false);
    	col.setUserID(1);
    	col.setCollectionID(id);
    	return col;
    }
    public ComicDTO getComic(long id) {
    	//TODO: unstub this
    	ComicDTO com = new ComicDTO();
    	com.setName("Amazing Testor");
    	com.setId(1);
    	com.setCharacters(new String[] {"Testy","TestLass"});
    	com.setCreators(new String[] {"Shawn", "Jasmine"});
    	com.setIssueNumber(404);
    	com.setPublisher("TE Comics");
    	com.setThumbnailLink("none");
    	return com;
    }
    /***
     * Links a comic to our collection and adds it to the db if it was not already present
     * @param CollectionID
     * @param comic
     * @param userID
     * @return
     */
    public boolean addComicToCollection(long CollectionID, ComicDTO comic,int userID) {
    	//if not the owner get the heck out of there
    	if(!ownsCollection(CollectionID, userID))
    		return false;
    	
    	//check if the comic exists and if not add it to the db.
    	long id = getComicID(comic);
    	if(id>0) {
    		id = addComic(comic);
    	}
    	//link comic in collection-comic
    	return false;
    }
    /***
     * Adds a new comic to the DB
     * @param comic
     * @return success
     */
    public int addComic(ComicDTO comic) {
    	//TODO: Stub will add the comic to the DB
    	return 1;
    }
    /***
     * Deletes comic from the db should only be called if the comic is orphaned first
     * @param id
     * @return success
     */
    public boolean deleteComic(long id) {
    	return false;
    }
    /***
     * Gets comic ID if it exists in our db, if not returns -1
     * @param comic
     * @return
     */
    public long getComicID(ComicDTO comic) {
    	//TODO: Expand Stub
    	return -1;
    }
    /**
     * Checks if there are any references to the comic remaining in the link table
     * @param comic
     * @return orphaned status
     */
    public boolean comicOrphaned(ComicDTO comic) {
    	//TODO: Expand Stub
    	return false;
    }
    /***
     * Adds the collection
     * @param collection
     * @param userID
     * @return
     */
    public boolean addCollection(CreateCollectionDTO collection,int userID){
    	//TODO: unstub
    	if(collection.getUserID()!= ((long) userID))
    		return false;
    	//Add empty Collection

    	
    	
    	
    	return true;
    }
    /***
     * Updates the name of the collection and privacy settings
     * @param collection
     * @param userID
     * @return
     */
    public boolean updateCollection(CreateCollectionDTO collection,int userID){
    	//TODO: unstub
    	return true;
    }
    /***
     * Expunges the Collection and unlinks all comics, if orphans are created remove them
     * @param id
     * @param userID
     * @return
     */
    public boolean deleteCollection(long id,int userID){
    	//TODO: unstub
    	
    	ComicDTO[] comics = null;
    	//get all comics linked to id
    	
    	//Delete from collection-comic all instances where this collection is linked to a comic
    	if(comics!=null) {
	    	for(ComicDTO comic : comics) {
	    		if(comicOrphaned(comic)) {
	    			deleteComic(comic.getId());
	    		}
	    	}
    	}
    	
    	return true;
    }
    
    /***
     * Unlinks the comic from the collection
     * @param collectionID
     * @param comicID
     * @param userID
     * @return success
     */
    public boolean removeComic(long collectionID, long comicID,int userID) {
    	if(!ownsCollection(collectionID, userID))
    		return false;
		// TODO Auto-generated method stub
		//delete the connecting row in comic-collection
    	if(comicOrphaned(getComic(comicID))) {
    		//delete comic if the comic has no references.
    	}
		return false;
	}
    /***
     * Checks if the user owns the Collection
     * @param collectionID
     * @param userID
     * @return
     */
    private boolean ownsCollection(long collectionID,int userID) {
    	//TODO: expand stub
    	
    	return true;
    }
    
    
    private ComicDTO mapRowToComic(SqlRowSet comicRow) {
    	//TODO: Expand Stub
    	return null;
    }
    private CollectionDTO mapRowToCollection(SqlRowSet comicRow) {
    	//TODO: Expand Stub
    	return null;
    }

	
}
