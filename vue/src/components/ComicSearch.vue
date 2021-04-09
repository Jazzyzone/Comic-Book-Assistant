<template>
  <div>
      
      <div class="search">
            <div class="comic"
            v-for="comic in searchResults"
            v-bind:key="comic.id"
            >
            <div>
            {{comic.title}}
            </div>
            <button type="button" class="btn-success delete">add</button>
        <img v-bind:src="comic.thumbnailLink" alt="" srcset=""/>
      </div>
  </div>

  </div>
</template>

<script>
import MarvelService from '../services/MarvelService'

export default {
    data() {
        return {
            searchResults: []
        }
    },
    created() {
        MarvelService.getComicListTest().then(response => {
             this.searchResults = response.data.data.results;
         });
    }
}
</script>

<style>

</style>