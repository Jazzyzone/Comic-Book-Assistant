//Add a collection to a user list of collections

<template>

    <v-container
    class="pa-5 d-flex flex-row"
    fluid
    align-center
  >
       
        
         <v-text-field  v-model="collections.name" label="Collection Name" :append-icon="collections.private ? 'mdi-eye':'mdi-eye-off'"
            @click:append="collections.private=!collections.private"/>     
        <v-btn icon v-on:click="addCollection()" class="ml-2"><v-icon>mdi-content-save</v-icon></v-btn>

   </v-container>
  
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
            this.collections.name = this.collections.name.trim();
            if(this.collections.name != "") {
            ComicServices
            .addCollection(this.collections).then(response => {
                if(response.status == 200) {
                    ComicServices.getAllCollections(this.$route.params.username).then(response => {
             this.$store.commit("SET_COLLECTIONS", response.data)
            });
                }
            });
            }


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