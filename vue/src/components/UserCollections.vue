//Display all collections a user has
//if they select on the icon it will take
//them to ComicsList

<template>
<div>
<h1> {{this.$store.state.user.username}} Comic Collections</h1>
    
    <div class="collections">
        <div class="collection"
        v-for="collection in collections"
        v-bind:key="collection.id"
        >
            {{collection.name}}
        </div>
    </div>


    <!-- <router-link :to="{ name: 'userCollections', params: {username: this.$store.state.user.username} }">View My Comic Book Collections</router-link> -->
    <p>Add Comic Book Collection</p>
    <p>Add Comic Book</p>
    <p>My Friends</p>
    <p>See Collections of Others</p>
  <div>
      <div>
      <!-- for collections in collections creates separate columns displaying collection names -->
      </div>
    <div>
        <!-- hamburger menu the collection columns and then when they're clicked they expand
        to show the full list of comic book names, which could also be a link when clicked
        displays the comic collection -->
    </div>
  </div>
</div>

    
</template>

<script>
export default {
    data() {
        return {
            collections: []
        }
    }
    // created() {
    //     this.retrieveCollections();
    // },
    // methods: {
    //     retrieveCollections() {
    //         this.
    //     }
    // }
}
</script>

<style>

</style>