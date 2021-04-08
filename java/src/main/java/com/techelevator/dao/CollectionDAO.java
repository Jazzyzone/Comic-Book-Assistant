package com.techelevator.dao;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.CollectionDTO;
import com.techelevator.model.ComicDTO;
import com.techelevator.model.FullCollectionDTO;

@Service
public class CollectionDAO {
	private JdbcTemplate jdbcTemplate;

	public CollectionDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List<CollectionDTO> getAllPublicCollectionList() {

		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		String sqlFindAccountID = "SELECT c.* FROM collections c "
				+ "			INNER JOIN collections_user cu ON cu.collection_id = c.collection_id "
				+ "			WHERE c.private = false ";
		SqlRowSet collectionRow = jdbcTemplate.queryForRowSet(sqlFindAccountID);
		while (collectionRow.next()) {
			collectionDTOs.add(mapRowToCollection(collectionRow));
		}

		return collectionDTOs;
	}
	public List<CollectionDTO> getAllCollectionList() {

		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		String sqlFindAccountID = "SELECT c.* FROM collections c "
				+ "			INNER JOIN collections_user cu ON cu.collection_id = c.collection_id";
		SqlRowSet collectionRow = jdbcTemplate.queryForRowSet(sqlFindAccountID);
		while (collectionRow.next()) {
			collectionDTOs.add(mapRowToCollection(collectionRow));
		}

		return collectionDTOs;
	}
	public List<CollectionDTO> getCollectionList(int userID, int collectionUserID) {

		List<CollectionDTO> collectionDTOs = new ArrayList<>();
		String sqlFindAccountID = "SELECT c.* FROM collections c "
				+ "			INNER JOIN collections_user cu ON cu.collection_id = c.collection_id "
				+ "			WHERE cu.user_id = ? ";
		SqlRowSet collectionRow = jdbcTemplate.queryForRowSet(sqlFindAccountID, collectionUserID);
		while (collectionRow.next()) {
			collectionDTOs.add(mapRowToCollection(collectionRow));
		}

		return collectionDTOs;
	}

	public FullCollectionDTO getCollection(long collectionId, int userID) {
		FullCollectionDTO fullCollectionDTO = new FullCollectionDTO();
		String sqlGetCollection ="SELECT * FROM collections WHERE collection_id = ?";
		String sqlGetComicCollection = "SELECT * FROM collections_comics cc " + 
		        "INNER JOIN comics AS cm ON cm.comic_id = cc.comic_id ";
		SqlRowSet collectionRow = jdbcTemplate.queryForRowSet(sqlGetCollection, collectionId);
		
		if(collectionRow.next()) {
			fullCollectionDTO = mapRowFullCollection(collectionRow);
			SqlRowSet comicRow = jdbcTemplate.queryForRowSet(sqlGetComicCollection);
			while(comicRow.next()) {
				fullCollectionDTO.addComics(mapRowToComic(comicRow));
			}
		}
		return fullCollectionDTO;
	}

	public ComicDTO getComic(long comicId) {
		// TODO: unstub this
		String sqlGetComic = "SELECT * FROM comics WHERE comic_id = ?";
		SqlRowSet comicRow = jdbcTemplate.queryForRowSet(sqlGetComic,comicId);
		ComicDTO comic = new ComicDTO();
		if(comicRow.next()) {
			comic = mapRowToComic(comicRow);
		}
		return comic;
	}

	/***
	 * Links a comic to our collection and adds it to the db if it was not already
	 * present
	 * 
	 * @param CollectionID
	 * @param comic
	 * @param userID
	 * @return
	 */
	public boolean addComicToCollection(long collectionID, ComicDTO comic, int userID) {
		// if not the owner get the heck out of there
		if (!ownsCollection(collectionID, userID))
			return false;

		// check if the comic exists and if not add it to the db.
		long id = getComicID(comic);
		if (id > 0) {
			id = addComic(comic);
		}
		// link comic in collection-comic and all bride tables + depending tables
		String sqlInsertComic = "INSERT INTO comics (title, issue_num, img) VALUES (?,?,?) RETURNING comic_id";
		String sqlInsertCollectionComic = "INSERT INTO collections_comics (collection_id, comic_id) VALUES (?,?)";
		String sqlInsertCharacterComic = "INSERT INTO characters_comics (character_id, comic_id) VALUES (?,?)";
		String sqlInsertSeriesComic = "INSERT INTO series_comics (series_id, comic_id) VALUES (?,?)";
		String sqlInsertPublisherComic = "INSERT INTO publisher_comics (publisher_id, comic_id) VALUES (?,?)";
		String sqlInsertCreatorComic = "INSERT INTO creator_comics (creator_id, comic_id) VALUES (?,?)";
		String sqlInsertCharacters = "INSERT INTO characters (name) VALUES (?) RETURNING character_id";
		String sqlInsertCreator = "INSERT INTO creator (full_name) VALUES (?) RETURNING creator_id";
		String sqlInsertSeries = "INSERT INTO series (title) VALUES (?) RETURNING series_id";
		String sqlInsertPublisher = "INSERT INTO publisher (name) VALUES (?) RETURNING publisher_id";

		try {

			SqlRowSet row = jdbcTemplate.queryForRowSet(sqlInsertComic, comic.getName(), comic.getIssueNumber(),
					comic.getThumbnailLink());
			int newComicId = 0;

			if (row.next()) {
				newComicId = row.getInt("comic_id");
				jdbcTemplate.update(sqlInsertCollectionComic, collectionID, newComicId);

			}

			row = jdbcTemplate.queryForRowSet(sqlInsertSeries, comic.getSeries());

			if (row.next()) {
				jdbcTemplate.update(sqlInsertSeriesComic, row.getInt("series_id"), newComicId);
			}

			row = jdbcTemplate.queryForRowSet(sqlInsertPublisher, comic.getPublisher());

			if (row.next()) {
				jdbcTemplate.update(sqlInsertPublisherComic, row.getInt("publisher_id"), newComicId);
			}

			for (String character : comic.getCharacters()) {

				row = jdbcTemplate.queryForRowSet(sqlInsertCharacters, character);

				if (row.next()) {
					jdbcTemplate.update(sqlInsertCharacterComic, row.getInt("character_id"), newComicId);
				}

			}

			for (String creator : comic.getCreators()) {

				row = jdbcTemplate.queryForRowSet(sqlInsertCreator, creator);

				if (row.next()) {
					jdbcTemplate.update(sqlInsertCreatorComic, row.getInt("creator_id"), newComicId);
				}
			}

		} catch (DataAccessException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/***
	 * Adds a new comic to the DB
	 * 
	 * @param comic
	 * @return success
	 */
	public int addComic(ComicDTO comic) {
		// TODO: Stub will add the comic to the DB
		return 1;
	}

	/***
	 * Deletes comic from the db should only be called if the comic is orphaned
	 * first
	 * 
	 * @param id
	 * @return success
	 */
	public boolean deleteComic(long id) {
		
		return false;
	}

	/***
	 * Gets comic ID if it exists in our db, if not returns -1
	 * 
	 * @param comic
	 * @return
	 */
	public long getComicID(ComicDTO comic) {
		// TODO: Expand Stub
		return -1;
	}

	/**
	 * Checks if there are any references to the comic remaining in the link table
	 * 
	 * @param comic
	 * @return orphaned status
	 */
	public boolean comicOrphaned(ComicDTO comic) {
		// TODO: Expand Stub
		return false;
	}

	/***
	 * Adds the collection
	 * 
	 * @param collection
	 * @param userID
	 * @return
	 */
	public boolean addCollection(CollectionDTO collection, int userID) {
		// TODO: unstub
		// if(collection.getUserID()!= ((long) userID))
		// return false;
		// Add empty Collection

		String sqlInsertCollection = "INSERT INTO collections (user_id,name,private) VALUES (?,?,?) RETURNING collection_id";
		String sqlInsertCollectionUser = "INSERT INTO collections_user (user_id,collection_id) VALUES (?,?)";
		try {

			SqlRowSet row = jdbcTemplate.queryForRowSet(sqlInsertCollection, collection.getUserID(),
					collection.getName(), collection.isPrivate());
			if (row.next()) {
				jdbcTemplate.update(sqlInsertCollectionUser, collection.getUserID(), row.getInt("collection_id"));
			}
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/***
	 * Updates the name of the collection and privacy settings
	 * 
	 * @param collection
	 * @param userID
	 * @return
	 */
	public boolean updateCollection(CollectionDTO collection, int userID) {
		// TODO: unstub
		return true;
	}

	/***
	 * Expunges the Collection and unlinks all comics, if orphans are created remove
	 * them
	 * 
	 * @param id
	 * @param userID
	 * @return
	 */
	public boolean deleteCollection(long collectionID, int userID) {
		// TODO: unstub
		String getCollectionReferences = "SELECT comic_id FROM collections_comics WHERE collection_id = ?";
		String deleteCollectionComicReferences = "DELETE FROM collections_comics WHERE collection_id = ?";
		String deleteCollectionUserReferences = "DELETE FROM collections_user WHERE collection_id = ?";
		String deleteCollection = "DELETE FROM collections WHERE collection_id = ?";
//		SqlRowSet refRows = jdbcTemplate.queryForRowSet(getCollectionReferences);
//		List<Long> comicIds = new ArrayList<>();
//		while (refRows.next()) {
//			comicIds.add(refRows.getLong("comic_id"));
//		}
		//String deleteUnreferecedComics = "DELETE c FROM comics c JOIN collections_comics cc ON cc.comic_id = c.comic_id WHERE cc.comic_id IS NULL";
//		for(Integer i : comicIds) {
//			
//		}
		//jdbcTemplate.update(deleteUnreferecedComics);
		jdbcTemplate.update(deleteCollectionComicReferences, collectionID);
		jdbcTemplate.update(deleteCollectionUserReferences, collectionID);
		
		jdbcTemplate.update(deleteCollection,collectionID);
		return true;
	}

	/***
	 * Unlinks the comic from the collection
	 * 
	 * @param collectionID
	 * @param comicID
	 * @param userID
	 * @return success
	 */
	public boolean removeComic(long collectionID, long comicID, int userID) {
		if (!ownsCollection(collectionID, userID))
			return false;
		// TODO Auto-generated method stub
		// delete the connecting row in comic-collection
		if (comicOrphaned(getComic(comicID))) {
			// delete comic if the comic has no references.
		}
		return false;
	}

	/***
	 * Checks if the user owns the Collection
	 * 
	 * @param collectionID
	 * @param userID
	 * @return
	 */
	private boolean ownsCollection(long collectionID, int userID) {
		// TODO: expand stub

		return true;
	}

	private ComicDTO mapRowToComic(SqlRowSet comicRow) {
		ComicDTO comic = new ComicDTO();
		comic.setIssueNumber(comicRow.getInt("issue_num"));
		comic.setName(comicRow.getString("title"));
		comic.setThumbnailLink(comicRow.getString("img"));
		comic.setId(comicRow.getLong("comic_id"));
		
		String getCreatorComic = "SELECT * FROM creator_comics cc " + 
				"        INNER JOIN creator AS c ON cc.creator_id = c.creator_id " +
				"WHERE cc.comic_id = ?";
		SqlRowSet creatorRow = jdbcTemplate.queryForRowSet(getCreatorComic, comic.getId());
		ArrayList<String> creator = new ArrayList<>(); 
		 
		while(creatorRow.next()) {
			creator.add(creatorRow.getString("full_name"));			
		}
		comic.setCreators(creator.toArray(new String[creator.size()]));
		
		
		String getCharacterComic = "SELECT * FROM characters_comics ccc " + 
				"        INNER JOIN characters AS ch ON ccc.character_id = ch.character_id " +
				"WHERE ccc.comic_id = ?";
		SqlRowSet characterComicRow = jdbcTemplate.queryForRowSet(getCharacterComic, comic.getId());
		ArrayList<String> characters = new ArrayList<>(); 
		 
		while(characterComicRow.next()) {
			characters.add(characterComicRow.getString("name"));			
		}
		comic.setCharacters(characters.toArray(new String[characters.size()]));
		String getPublisherComics = "SELECT * FROM publisher_comics pcc " +
				"	 INNER JOIN publisher AS p ON pcc.publisher_id = p.publisher_id " +
				"WHERE pcc.comic_id = ?";


		SqlRowSet publisherComicRow = jdbcTemplate.queryForRowSet(getPublisherComics, comic.getId());
		if(publisherComicRow.next()) {
			comic.setPublisher(publisherComicRow.getString("name"));
		}
		String getSeriesComic = "SELECT * FROM series_comics scc " +
				"        INNER JOIN series AS s ON scc.series_id = s.series_id " +
				"WHERE scc.comic_id =?";
		SqlRowSet seriesComicRow = jdbcTemplate.queryForRowSet(getSeriesComic, comic.getId());
		if(seriesComicRow.next()) {
			comic.setSeries(seriesComicRow.getString("title"));
		}
		return comic;
	}
		
		

	private CollectionDTO mapRowToCollection(SqlRowSet comicRow) {
		// TODO: Expand Stub
		CollectionDTO collection = new CollectionDTO();
		collection.setName(comicRow.getString("name"));
		collection.setUserID(comicRow.getInt("user_id"));
		collection.setPrivate(comicRow.getBoolean("private"));
		collection.setCollectionID(comicRow.getInt("collection_id"));
		return collection;
	}
	
	private FullCollectionDTO mapRowFullCollection(SqlRowSet comicRow) {
		// TODO: Expand Stub
		FullCollectionDTO FullCollectionDTO = new FullCollectionDTO();
		FullCollectionDTO.setName(comicRow.getString("name"));
		FullCollectionDTO.setUserID(comicRow.getInt("user_id"));
		FullCollectionDTO.setPrivate(comicRow.getBoolean("private"));
		FullCollectionDTO.setCollectionID(comicRow.getInt("collection_id"));
		return FullCollectionDTO;
	 }

}
