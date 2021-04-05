package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.techelevator.model.CollectionDTO;
import com.techelevator.model.ComicDTO;

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
    public boolean addCollection(CollectionDTO collection,int userID){
    	//TODO: unstub
    	return true;
    }
    public boolean updateCollection(CollectionDTO collection,int userID){
    	//TODO: unstub
    	return true;
    }
    public boolean deleteCollection(long id,int userID){
    	//TODO: unstub
    	return true;
    }
}
