//Add a collection to a user list of collections

<template>
<div>
    <form>
        <label for="collectionName">Collection Name:</label>
        <input type="text" id="collectionName" name="collectionName" v-model="collections.name">

        <label for="porp">Private or Public?</label>
        <input type="radio" id="porp" value="true" v-model="collections.private">
        <input type="radio" id="porp" value="false" v-model="collections.private">
        
        
        <button type="submit" class="btn btn-primary" v-on:click="addCollection()">Save Collection</button>

    </form>
  </div>
</template>

<script>

import ComicServices from '../services/ComicServices';

export default {
    data() {
        return {
            collections: {
                userID: this.$store.state.user.id,
                name: "",
                private: false
            }
        };

    },
    methods: {
        addCollection(){
            ComicServices
            .addCollection(this.collections)
            // .then(response =>{
            //    if(response.status === 200){
            //        this.$router.push("/");
            //    }
            // })
        }
    }

}
</script>

<style>

</style>