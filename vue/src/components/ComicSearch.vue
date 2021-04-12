<template>
  <div>
    <v-container>
      <v-flex class="d-flex flex-wrap flex-row align-stretch " v-if="searchResults.length > 0">
            <v-sheet class="rounded-xl mx-auto pa-2 ma-3 d-flex flex-wrap flex-column justify-space-between"
            v-for="comic in searchResults.slice(((10 * this.page) - 10),(this.page * 10) + 2)"
            v-bind:key="comic.id"
            >
            <v-row
      class="mb-6"
      no-gutters
    >
    <v-col>
            <v-card max-width="300" :elevation="10" class="mb-5">
                <v-img v-bind:src='comic.thumbnail.path.concat("." + comic.thumbnail.extension)' srcset="" />
                <v-card-title><p>{{comic.title}}</p></v-card-title>
            
            <!-- {{comic.issueNumber}} -->
            <!-- {{comic.id}} -->
            <!-- {{comic.characters}} -->
            <!-- {{comic.description}} -->
            <v-btn disabled v-if="comicInCollection(comic.title)" :class="disabled">already in collection</v-btn>
            <v-btn rounded
      color="primary"
      dark @click="addComicToCollection(comic)" v-else>add to collection</v-btn>
            
            </v-card>
            
            </v-col>
        </v-row>
      </v-sheet>
      </v-flex>
        <div class="loading" v-if="isLoading">
           <v-progress-circular
                indeterminate
                color="primary"
                >
                <div><h4>...Searching for results</h4></div>
                </v-progress-circular>
          </div>
      <h2 v-if="searchResults.length === 0 && !isLoading">No search results found</h2>
    </v-container>
    <div class="text-center">
        <v-pagination
        v-model="page"
        :length="searchResults.length / 10"
        v-if="searchResults.length > 0"
        ></v-pagination>
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
            page: 1,
            // collectionid: '',
            comicid: '',
            inCollection: false,
            isLoading: true
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
             this.isLoading = false;
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
            comic.characters.items.forEach(character => {
                comicDTO.characters.push(character.name);
            });
            comic.creators.items.forEach(creator => {
                comicDTO.creators.push(creator.name);
            });
            ComicServices.addComic(this.$route.params.collectionID, comicDTO);
            this.collection.comics.push(comicDTO);
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
        filterItems () {
        return this.searchResults.slice(this.startIndex, this.stopIndex);  // or any condition u want 
    }
    }
}
</script>

<style scoped>
h2 {
    text-align: center;
}
img:hover {
    height: 50px;
    
}
.loading {
    text-align: center;
}
.loading h4 {
    margin-top: 150px;
}
p {
    font-size: 1rem;
    text-align: center;
    word-break: normal;
}
div button {
    height:20px; 
    width:200px; 
    margin:-100px -100px -80px; 
    position:relative;
    top:50%; 
    left:50%;
}
.disabled {
    height:20px; 
    width:100px; 
    margin: -20px -50px; 
    position:relative;
    top:50%; 
    left:50%;
}
</style>