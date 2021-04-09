<template>
  <div>
      
      <div class="search">
            <div class="comic"
            v-for="comic in searchResults"
            v-bind:key="comic.id"
            >
            <div class="card">
                <img v-bind:src='comic.thumbnail.path.concat("." + comic.thumbnail.extension)' srcset=""/>
                <div class="card-title">{{comic.title}}</div>
            
            <!-- {{comic.issueNumber}} -->
            <!-- {{comic.id}} -->
            <!-- {{comic.characters}} -->
            <!-- {{comic.description}} -->
            <button type="button" class="btn-success delete" @click="addComicToCollection(comic)">add</button>
            </div>
            
        
      </div>
  </div>

  </div>
</template>

<script>
import ComicServices from '../services/ComicServices'
import MarvelService from '../services/MarvelService'

export default {
    data() {
        return {
            searchResults: []
        }
    },
    created() {
        let config = {
        params: {
            title : this.$route.params.search,
        }
        }
        MarvelService.getComicList(config).then(response => {
             this.searchResults = response.data.data.results;
         });
    },
    methods: {
        addComicToCollection(comic) {
            let comicDTO = {
                name: comic.title,
                issueNumber: comic.issueNumber,
                publisher: "Marvel",
                creators: [
                    
                ],
                characters: [
                    
                ],
                thumbnailLink: comic.thumbnail.path.concat("." + comic.thumbnail.extension),
                series: comic.series.name
            }
            ComicServices.addComic(this.$route.params.collectionID, comicDTO)
        }
    }
}
</script>

<style>

</style>