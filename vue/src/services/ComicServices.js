import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
  });

export default {

  getAllCollections(user) {
    return http.get(`collection/user/${user}`)
  },

  getCollectionById(collectionId) {
      return http.get(`/collection/${collectionId}`)
  }

}
