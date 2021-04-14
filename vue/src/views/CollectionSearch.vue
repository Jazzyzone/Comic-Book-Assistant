<template>
<v-expansion-panels accordion flat >
    <v-expansion-panel
      v-for="collection in collections"
            v-bind:key="collection.collection_ID"
    >
      <v-expansion-panel-header>
        {{collection.name}}
      </v-expansion-panel-header>
      <v-expansion-panel-content class="bg">
        <comics-list v-bind:collectionID="collection.collectionID"/>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>
<script>
    import ComicServices from '../services/ComicServices';
    import ComicsList from '../components/ComicsList.vue';
    export default {

      components: { ComicsList },
      data() {
       return {
         collections :  [],
         }
      },
      created(){
        ComicServices.searchCollections(this.$route.params.name).then(responce => {
          this.collections = responce.data;
        })
      }
    }
</script>
<style >

</style>