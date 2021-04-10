//Display all collections a user has
//if they select on the icon it will take
//them to ComicsList

<template>
<div>


    <div v-if="isCurrentUser">
     <button type="button" class="btn btn-primary" v-on:click="createCollection = !createCollection">{{!createCollection? "Create a Collection" : "Cancel"}}</button>
        <div v-if="createCollection">
            <add-a-collection />
        </div>
    </div>
    <div class="container">
         
<h1> {{this.$route.params.username}} Comic Collections</h1>
        <div class="collections">
            <div class="collection"
            v-for="collection in collections"
            v-bind:key="collection.collection_id"
            >
            <div class="row" v-if="($store.state.token == '' && collection.private === false) || $store.state.token != ''">
        
           
                <div class="column">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title">
                                <h5>{{collection.name}}</h5>
                            <div v-if="isCurrentUser">               
                                <button type="button" class="btn btn-secondary btn-sm" v-on:click="changeCollectionName(collection.collectionID)">{{collection.collectionID === changeId ? "Cancel" :"Rename Collection"}}</button>
                            </div>
                            <div v-if="changeName && collection.collectionID === changeId">
                                 <label for="collectionName">Collection Name:</label>
                                    <input type="text" id="collectionName" name="collectionName" v-model="collection.name" required>
                                    <button type="submit" class="btn btn-primary" v-on:click="updateCollection(collection)">Save Changes</button>       
                                </div>
                                <div class="card-text">
                                    <comics-list v-bind:collectionID="collection.collectionID"/>
                                    <div v-if="isCurrentUser">
                                    <button type="button" class="btn btn-success" v-on:click="searchComic(collection.collectionID)">Add Comic</button>
                                      <button type="button" class="btn btn-danger" @click="deleteCollection(collection.collectionID)">Delete Collection</button> 
                                      <div v-if="collection.collectionID === collectionid">
                                 <label for="searchComicBook">Enter Comic Title:</label>
                                    <input type="text" id="searchComicBook" name="searchComicBook" v-model="searchFor" placeholder="Enter title" required>
                                    <button type="submit" class="btn btn-primary" @click="searchForComic(collection.collectionID)">Search</button>       
                                </div> 
                                    </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            
        </div>
            </div>
        </div>
    </div>
    <!-- <div>
    <add-a-collection />
    </div> -->
    <!-- <router-link :to="{ name: 'userCollections', params: {username: this.$store.state.user.username} }">View My Comic Book Collections</router-link> -->
    
    
    
    <p>My Friends</p>
    <p>See Collections of Others</p>
</div>

    
</template>

<script>

import ComicServices from '../services/ComicServices';
import AddACollection from './AddACollection.vue';
import ComicsList from './ComicsList.vue';


export default {
  components: { AddACollection, ComicsList },
   data() {
       return {
     createCollection: false,
     changeName: false,
     changeId: '',
     collectionid: '',
     search: false,
     searchFor: ''
       }
   },
    created() {
         ComicServices.getAllCollections(this.$route.params.username).then(response => {
             this.$store.commit("SET_COLLECTIONS", response.data)
         });
         
         //window.alert(this.collections.length);
    },
    computed: {
        collections() {
            return this.$store.state.userCollections;
        },
        isCurrentUser() {
            return this.$route.params.username === this.$store.state.user.username;
        },
    },
    methods: {
        deleteCollection(collectionId) {
            ComicServices.removeCollection(collectionId).then(response => {
                if(response.status == 200) {
                    ComicServices.getAllCollections(this.$route.params.username).then(response => {
             this.$store.commit("SET_COLLECTIONS", response.data)
         });
                }
            });
        },
        changeCollectionName(collectionId) {
            if(this.changeId !== collectionId) {
                this.changeName = true;
                this.changeId = collectionId;
            
            }else if(this.changeId === collectionId) {
                this.changeId = '';
                this.changeName = false;
            }else{
                this.changeName = !this.changeName;
            }     
        },
        searchComic(collectionId) {
             this.collectionid = collectionId;
        },
        searchForComic(collectionId) {
            this.collectionid = collectionId;
            //will need a conditional to make sure string isn't empty and will need a .trim()
            this.$router.push({ name: "ComicSearch", params: {collectionID: this.collectionid, search: this.searchFor}});
        },
        updateCollection(collection) {
            
            ComicServices.updateCollection(collection).then(response => {
                if(response.status == 200) {
                    this.changeId = '';
                    this.changeName = false;
                    ComicServices.getAllCollections(this.$route.params.username).then(response => {
             this.$store.commit("SET_COLLECTIONS", response.data)
         });
                }
            });
        },
    }
}
</script>

<style scoped>
div img {
     max-width: 200px;
     max-height: 250px;
 }

    * {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Float four columns side by side */
.column {
  float: left;
  width: 25%;
  padding: 0 10px;
}

/* Remove extra left and right margins, due to padding in columns */
.rowz {margin: 0 -5px;}

/* Clear floats after the columns */
.rowz:after {
  content: "";
  display: table;
  clear: both;
}

/* Style the counter cards */
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); /* this adds the "card" effect */
  padding: 16px;
  text-align: center;
  background-color: #f1f1f1;
}

/* Responsive columns - one column layout (vertical) on small screens */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
    display: block;
    margin-bottom: 20px;
  }
}

.container {
    margin-left: 300px;
}

/* #cards_landscape_wrap-2{
  text-align: center;
  background: #F7F7F7;
}
#cards_landscape_wrap-2 .container{
  padding-top: 30px; 
  padding-bottom: 100px;
}

#cards_landscape_wrap-2 .card {
  border-radius: 5px;
}

#cards_landscape_wrap-2 .card .image-box{
  background: #ffffff;
  overflow: hidden;
  box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.50);
  border-radius: 5px;
}
#cards_landscape_wrap-2 .card .image-box img{
  -webkit-transition:all .9s ease; 
  -moz-transition:all .9s ease; 
  -o-transition:all .9s ease;
  -ms-transition:all .9s ease; 
  width: 100%;
  height: 200px;
}

#cards_landscape_wrap-2 .card:hover .image-box img{
  opacity: 0.7;
  -webkit-transform:scale(1.15);
  -moz-transform:scale(1.15);
  -ms-transform:scale(1.15);
  -o-transform:scale(1.15);
  transform:scale(1.15);
}
#cards_landscape_wrap-2 .card .text-box{
  text-align: center;
}
#cards_landscape_wrap-2 .card .text-box .text-container{
  padding: 30px 18px;
}
#cards_landscape_wrap-2 .card{
  background: #FFFFFF;
  margin-top: 50px;
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
  box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.40);
}
#cards_landscape_wrap-2 .card:hover{
  background: #fff;
  box-shadow: 0px 15px 26px rgba(0, 0, 0, 0.50);
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
  margin-top: 50px;
}
#cards_landscape_wrap-2 .card .text-box p{
  margin-top: 10px;
  margin-bottom: 0px;
  padding-bottom: 0px; 
  font-size: 14px;
  letter-spacing: 1px;
  color: #000000;
}

.row {
    max-width: 600px;
}

#cards_landscape_wrap-2 .card h5{
  margin-top: 0px;
  margin-bottom: 4px; 
  font-size: 20px;
  font-weight: bold;
  text-transform: uppercase;
  font-family: 'Roboto Black', sans-serif;
  letter-spacing: 1px;
  color: #00acc1;
} */
</style>