//Displays every comic book in users collection
<template>
    <v-container>
        
        <v-flex class="d-flex flex-wrap flex-row align-stretch ">
            <v-sheet v-for="comic in collection.comics"
            v-bind:key="comic.id"
            class="rounded-xl mx-auto transition-swing semi pa-2 ma-3 d-flex flex-wrap flex-column justify-space-between"
            max-width="300"
            dark
            :elevation="10"
            color="rgba(100,0,0,.3)"
            >


                <div>
                    <v-card-title 
                    
                    class="headline"
                    v-text="comic.name"
                    ></v-card-title>
                    
                    <v-card-subtitle v-text="comic.publisher"></v-card-subtitle>
                     <v-divider></v-divider>
                </div>
                <div>

                    <v-img  min-height="480" min-width="280" class="rounded-xl" :aspect-ratio="6.5/10" :src="comic.thumbnailLink"></v-img>
                    <v-btn   v-if="isCurrentUser" width="100%" class="rounded-xl" color="red" @click="deleteComic(comic.id)" >Remove</v-btn>
                </div>
          </v-sheet>
        </v-flex>
        
    </v-container>
</template>

<script>
import ComicServices from '../services/ComicServices'
export default {
    props: ["collectionID"],
    data() {
        return {
            collection: {

            },
            collectionid: '',
            comicid: '',
        }
    },
    created() {
         ComicServices.getAllComicsByCollectionId(this.collectionID).then(response => {
             this.collection = response.data;
         });
         //window.alert(this.collections.length);
    },
    computed: {
        
        isCurrentUser() {
            return this.$route.params.username === this.$store.state.user.username;
        },
    },
    methods: {
        deleteComic(comicID){
            this.comicid = comicID;

            ComicServices.deleteComicByComicId(this.collectionID, comicID).then(response => {
                if(response.status == 200) {
                    ComicServices.getAllComicsByCollectionId(this.collectionID).then(response => {
                    this.collection = response.data
                    });
                }      
            });
        },

    }
    
}
</script>

<style>
.semi{
    background: rgba(100,0,0,.3);
}


</style>