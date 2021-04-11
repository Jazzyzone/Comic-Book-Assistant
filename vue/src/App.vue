<template>

  <v-app >
    
    <v-app-bar app color = primary>
      <v-tabs centered
        class="ml-n9"
        color="white grey"
        
      >
        <v-tab :to="{ name: 'home'}">Home </v-tab>
        <v-tab :to="{ name: 'userHome',params: {username: this.$store.state.user.username}}">Profile </v-tab>

      </v-tabs>
      <v-btn offset-y v-if="$store.state.token == ''" @click="$router.push('login' )" color= secondary >LogIn</v-btn>
      <v-menu offset-y v-if="$store.state.token != ''">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            color= secondary

            v-bind="attrs"
            v-on="on"
          >
            {{$store.state.user.username}}
          </v-btn>
        </template>
        <v-list color=secondary dark>
          <v-list-item :to="{ name: 'userCollections', params: {username: this.$store.state.user.username} }"> 
            <v-list-item-title>My Collection</v-list-item-title>
          </v-list-item>
          <v-list-item >
            <v-list-item-title>Friends</v-list-item-title>
          </v-list-item>
          <v-list-item :to="{ name: 'logout' }">
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
     
    </v-app-bar>
   
    <v-main >
       
      <v-container>
         
        <v-sheet
           min-height="85vh"
   
          >
       
          <router-view />
     
        </v-sheet>
           
      </v-container>
    
    </v-main>
    
    <v-footer app :padless="true">
      <v-card
        flat
        tile
        width="100%"
        class="blue lighten-1 text-center"
      >
      <v-card-text class="white--text">
          {{ new Date().getFullYear() }} — <strong>Comic Elevator</strong>
          
        </v-card-text>
        </v-card>
    </v-footer>
  
  </v-app>
  
</template>
<script>


export default {
  unmounted() {
    this.$store.commit("LOGOUT");
    
  }
}
</script>

<style>

</style>

