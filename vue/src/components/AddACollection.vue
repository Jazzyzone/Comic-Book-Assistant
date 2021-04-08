//Add a collection to a user list of collections

<template>
<div>
    <form>
        <label for="collectionName">Name:</label>
        <input type="text" id="collectionName" name="collectionName" v-model="collections.name">

        <label for="porp">Private or Public?</label>
        <input type="radio" id="porp" value="true" v-model="collections.private">
        <input type="radio" id="porp" value="false" v-model="collections.private">
        
        <label for="addCollection">Submit</label>
        <input type="button" id="addCollection" v-on:click="addCollection">

    </form>
  </div>
</template>

<script>

import ComicServices from '../services/ComicServices';

export default {
    data() {
        return {
            collections: {
                user_id: this.$store.state.user.id,
                name: "",
                private: false
            }
        };

    },
    methods: {
        addCollection(){
            ComicServices
            .create(this.collections)
            .then(response =>{
               if(response === 201){
                   this.$router.push('/');
               }
            })
        }
    }

}
</script>

<style>

</style>