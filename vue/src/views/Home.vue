<template>
  <v-container> 
<div class="small">
    <test-chart :chart-data="datacollection"></test-chart>
    <button @click="fillData()">Randomize</button>
</div>
  </v-container>
</template>

<script>
import ComicServices from '../services/ComicServices';
import TestChart from '../charts/TestChart.js'
export default {
   components: {
      TestChart
    },
  name: "home",
  data() {
    return {
      datacollection: null,
    userSearch: ''
    }
  },
  mounted () {
      this.fillData()
    },
  methods: {
    searchUser() {
      
      ComicServices
        .getUserByUsername(this.userSearch)
        .then(response => {
          console.log(response.data.username);
          if (response.data !== '') {
            this.$store.commit("SET_USERSEARCH", response.data.username)
            this.$router.push({ name: "SearchUser", params: {username: response.data.username}})
          }
        })

    },
    fillData () {
        this.datacollection = {
          labels: [this.getRandomInt(), this.getRandomInt()],
          datasets: [
            {
              label: 'Data One',
              backgroundColor: '#f87979',
              data: [this.getRandomInt(), this.getRandomInt()]
            }, {
              label: 'Data One',
              backgroundColor: '#f87979',
              data: [this.getRandomInt(), this.getRandomInt()]
            }
          ]
        }
      },
      getRandomInt () {
        return Math.floor(Math.random() * (50 - 5 + 1)) + 5
      }
    
  }
  
};
</script>
<style>
 .small {
    max-width: 600px;
    margin:  150px auto;
  }
</style>
