<template>
  <v-container>
    <h1>{{$route.params.username}}</h1>
    <v-divider></v-divider>
    <h2>{{characterOptions.chart.title}}</h2>
    <GChart
     type="ColumnChart"
    :data="characterData"
    :options="characterOptions"
    v-if="chartLoaded"
    />
    <h2>{{creatorOptions.chart.title}}</h2>
    <GChart
     type="ColumnChart"
    :data="creatorData"
    :options="creatorOptions"
    v-if="chartLoaded2"
    />
    <v-container v-if="isCurrentUser">

       


    </v-container>
    <v-container v-else>
      ITS NOT ME
    </v-container>
  </v-container>
</template>

<script>
import { GChart } from 'vue-google-charts'
import ComicServices from '../services/ComicServices';


  export default {
    components: {
      GChart
    },
    data() {
      return{
        chartLoaded:false,
        chartLoaded2:false,
        characterData: [
          ['Comic Name', 'Comics']
        ],
        characterOptions: {
            chart: {
              title: "",
            },
            bars: 'vertical', // Required for Material Bar Charts.
            hAxis: { format: 'decimal' },
            height: 400,
            width:600,
            colors: ['#1b9e77']
          },
        creatorData: [
          ['Creator Name', 'comics']
        ],
        creatorOptions: {
            chart: {
              title: "",
            },
            bars: 'vertical', // Required for Material Bar Charts.
            hAxis: { format: 'decimal' },
            height: 400,
            width:600,
            colors: ['#1c9e77']
          },
      }
    }, 
    created(){
      ComicServices.getTopCharacterByUser(this.$route.params.username).then(response =>{
        console.log(response.data);
        response.data.forEach(element => {
          let arr = [];
          arr.push(element.name);
          arr.push(element.number);
          this.characterData.push(arr);
        });
        this.characterOptions.chart.title = `Top ${this.characterData.length-1} Characters in Collections`;
        this.chartLoaded=true;
      });
      ComicServices.getTopCreatorByUser(this.$route.params.username).then(response =>{
        console.log(response.data);
        response.data.forEach(element => {
          let arr = [];
          arr.push(element.name);
          arr.push(element.number);
          this.creatorData.push(arr);
        });
        this.creatorOptions.chart.title = `Top ${this.characterData.length-1} Creators in Collections`;
        this.chartLoaded2=true;
      });
    },
    computed: {
     

        isCurrentUser() {
            return this.$route.params.username === this.$store.state.user.username;
        },
    },
  methods: {
    onVharacterReady (chart, google) {
      this.characterLib = google
    },
    onCreatorReady (chart, google) {
      this.creatorLib = google
    }
  }
}
</script>

<style>

</style>