import axios from 'axios';

export default {

  getAllCollections(user) {
    return axios.get(`/collection/${user}`)
  },

  getCollectionById(collectionId) {
      return axios.get(`/collection/${collectionId}`)
  }

}
