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

  removeCollection(collectionId){
    return axios.delete(`/collection/`, collectionId)
  }

}
