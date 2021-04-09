//Displays every comic book in users collection
<template>

  <div>
      <div class="collections">
            <div class="comic"
            v-for="comic in collection.comics"
            v-bind:key="comic.id"
            >
            <div>
            {{comic.name}}
            </div>
            <button type="button" class="btn-danger delete" id="deletebtn" @click="deleteComic(comic.id)">X</button>
        <img v-bind:src="comic.thumbnailLink" alt="" srcset=""/>
      </div>
  </div>
  </div>

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
 div img {
     max-width: 200px;
     max-height: 250px;
 }


</style>