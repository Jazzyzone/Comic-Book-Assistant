import axios from 'axios';


export default {

  getAllCollections(user) {
    return axios.get(`collection/user/${user}`)
  },

  getCollectionById(collectionId) {
      return axios.get(`/collection/${collectionId}`)
  },

  addCollection(userId){
    return axios.post(`/collection/`, userId)
  },

  addComic(collectionid, comic){
    return axios.post(`/collection/${collectionid}/comic/`, comic)
  },

     updateCollection(collection){
        return axios.put(`/collection/${collection.collectionID}`, collection)
 }, 

  removeCollection(collectionId){
    return axios.delete(`/collection/${collectionId}`);
  },

  returnAllCollections(){
      return axios.get('/collection');
  },
  
  getAllUsernames() {
      return axios.get('/user');
  },

  getUserByUsername(username) {
      return axios.get(`/user/${username}`);
  },

  getAllComicsByCollectionId(collectionId) {
      return axios.get(`/collection/${collectionId}`);
  },

  deleteComicByComicId(collectionID, comicID) {
      return axios.delete(`collection/${collectionID}/comic/${comicID}`)
  },
  getTopCharacterByUser(user){
    return axios.get(`/comic/${user}/topCharacters/`);
  },
  getTopCreatorByUser(user){
    return axios.get(`/comic/${user}/topCreator/`);
  },
  getAllTopCharacterByUser(){
    return axios.get(`/comic/topCharacters/`);
  },
  getAllTopCreatorByUser(){
    return axios.get(`/comic/topCreators/`);
  }

//   getStats() {
//     return axios.get(`/stats/${type}`)


}
