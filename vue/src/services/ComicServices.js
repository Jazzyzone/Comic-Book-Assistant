import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8081"
  });

export default {

  getAllCollections(user) {
    return http.get(`collection/user/${user}`)
  },

  getCollectionById(collectionId) {
      return axios.get(`/collection/${collectionId}`)
  }

}
