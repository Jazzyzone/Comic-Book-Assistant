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

  addComic(userId){
    return axios.post(`/comic/`, userId)
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
  }


//   getStats() {
//     return axios.get(`/stats/${type}`)


}
