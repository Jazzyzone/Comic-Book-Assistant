package com.techelevator.model;

public class FullCollectionDTO {
	private String name;
	private long collectionID;
	private long userID;
	private boolean isPublic;
	private ComicDTO[] comics;
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public ComicDTO[] getComics() {
		return comics;
	}
	public void setComics(ComicDTO[] comics) {
		this.comics = comics;
	}
	public long getCollectionID() {
		return collectionID;
	}
	public void setCollectionID(long collectionID) {
		this.collectionID = collectionID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
