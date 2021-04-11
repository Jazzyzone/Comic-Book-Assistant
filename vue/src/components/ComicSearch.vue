<template>
  <div>
      
      <div class="search">
            <div class="comic"
            v-for="comic in searchResults"
            v-bind:key="comic.id"
            >
            <v-card >
                <v-img v-bind:src='comic.thumbnail.path.concat("." + comic.thumbnail.extension)' srcset=""/>
                <div>{{comic.title}}</div>
            
            <!-- {{comic.issueNumber}} -->
            <!-- {{comic.id}} -->
            <!-- {{comic.characters}} -->
            <!-- {{comic.description}} -->
            <v-btn disabled v-if="comicInCollection(comic.title)">already in collection</v-btn>
            <v-btn  @click="addComicToCollection(comic)" v-else>add to collection</v-btn>
            
            </v-card>
            
        
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
            searchResults: [],
            collection: {

            },
            // collectionid: '',
            comicid: '',
            inCollection: false
        }
    },
    created() {
        let config = {
        params: {
            title : null,
            issue : null
        }
        }
        if(this.$route.params.title!=""){
            config.params.title = this.$route.params.title;
        }

        if(this.$route.params.issue!=""){
            config.params.issue = this.$route.params.issue;
        }
        ComicServices.getAllComicsByCollectionId(this.$route.params.collectionID).then(response => {
             this.collection = response.data;
         });
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
        },
        comicInCollection(comicTitle) {
            let comics = (this.collection.comics);
            // comics = true;
            // let comics = comicTitle;
            // if (comicTitle == 'Spider-Man (2019) #5') {
            //     return true;
            // }
            
            if (comics.find(e => e.name === comicTitle)) {
               return true;
             } else {
                 return false;
             }
        },
    },
    computed: {
        
    }
}
</script>

<style>

</style>