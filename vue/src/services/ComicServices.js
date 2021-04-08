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

  updateCollection(collectionId){
    return axios.put(`/collection/${collection.Id}`, collectionId)
  }, 

  removeCollection(collectionId){
    return axios.delete(`/collection/`, collectionId)
  },

  getStats() {
    return axios.get(`/stats/${type}`)
}

}
