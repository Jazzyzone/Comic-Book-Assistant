<template>
  <div class="home">

    <img src="../images/marvel_comics_cast.png" alt="" srcset="">

    <div id="searchUser">
    <form @submit.prevent="searchUser">
        <label for="collectionName">Enter Users Name:</label>
        <input type="text" id="collectionName" name="collectionName" v-model="userSearch">

        <!-- <router-link :to="{ name: 'userCollections', params: {username: userSearch} }"> -->
          <button type="submit" class="btn btn-primary">Search</button>
        <!-- </router-link> -->
        

    </form>
    </div>
  </div>
</template>

<script>
import ComicServices from '../services/ComicServices';

export default {
  name: "home",
  data() {
    return {
    userSearch: ''
    }
  },
  methods: {
    searchUser() {
      
      ComicServices
        .getUserByUsername(this.userSearch)
        .then(response => {
          console.log(response.data.username);
          if (response.data !== '') {
            this.$store.commit("SET_USERSEARCH", response.data.username)
            this.$router.push({ name: "SearchUser", params: {username: response.data.username}})
          }
        })


      // ComicServices.getAllUsernames().then(response => {
      //       let found = response.data;
      //         found.find(e => e === this.userSearch);
      //       if (found) {
      //         this.$store.commit("SET_USERSEARCH", found )
      //         this.this.$router.push({ name: "userCollections", params: {username: found}});
      //       }
             
      //    });
    }
  }
  
};
</script>
