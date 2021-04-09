//Display all collections a user has
//if they select on the icon it will take
//them to ComicsList

<template>
<div>
<h1> {{this.$route.params.username}} Comic Collections</h1>
        <div class="collections">
            <div class="collection"
            v-for="collection in collections"
            v-bind:key="collection.collection_id"
            >
            <div id="cards_landscape_wrap-2" v-if="($store.state.token == '' && collection.private === false) || $store.state.token != ''">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-title">
                                {{collection.name}}
                            <div v-if="isCurrentUser">               
                                <button type="button" class="btn btn-secondary btn-sm" v-on:click="changeCollectionName(collection.collectionID)">{{collection.collectionID === changeId ? "Cancel" :"Rename Collection"}}</button>
                            </div>
                            <div v-if="changeName && collection.collectionID === changeId">
                                 <label for="collectionName">Collection Name:</label>
                                    <input type="text" id="collectionName" name="collectionName" v-model="collection.name" required>
                                    <button type="submit" class="btn btn-primary" v-on:click="updateCollection(collection)">Save Changes</button>       
                                </div>
                                <div class="card-text">
                                    <p>placeholder for comicbook</p>
                                    <div v-if="isCurrentUser">
                                    <button type="button" class="btn btn-success">Add Comic</button>
                                      <button type="button" class="btn btn-danger" @click="deleteCollection(collection.collectionID)">Delete Collection</button>  
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
    <div v-if="isCurrentUser">
     <button type="button" class="btn btn-primary" v-on:click="createCollection = !createCollection">{{!createCollection? "Create a Collection" : "Cancel"}}</button>
    
        <div v-if="createCollection">
            <add-a-collection />
        </div>
    </div>
    
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

import ComicServices from '../services/ComicServices';
import AddACollection from './AddACollection.vue';


export default {
  components: { AddACollection },
   data() {
       return {
     createCollection: false,
     changeName: false,
     changeId: ''
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
        }
    }
}
</script>

<style>
#cards_landscape_wrap-2{
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
#cards_landscape_wrap-2 .card h5{
  margin-top: 0px;
  margin-bottom: 4px; 
  font-size: 18px;
  font-weight: bold;
  text-transform: uppercase;
  font-family: 'Roboto Black', sans-serif;
  letter-spacing: 1px;
  color: #00acc1;
}
</style>