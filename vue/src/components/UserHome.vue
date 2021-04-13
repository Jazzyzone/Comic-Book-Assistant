<template>
  <v-container >
    <v-card  dark color="rgba(100,0,0,.3)" justify-center class="semi ma-3" height="100" >
    <h1 class="ml-5" >{{$route.params.username}}
      <v-icon
        color="yellow"
        large
        v-if="this.user.authorities[0].name=='ROLE_PREMIUM'"
      >
        mdi-star
      </v-icon> </h1>

    </v-card>
    <v-card color="rgba(100,0,0,.3)" class="mt-5 semi d-flex flex-row align-center justify-space-around"  dark align-center text-center>
 
      <v-card color="rgba(100,0,0,.3)" class="display-3 d-flex flex-column align-center ma-5  rounded-xl"  text-center width="30vw" v-if="characterData.length>1" >
        <h2 class ="ma-auto text-center">{{characterOptions.chart.title}}</h2>
        <GChart type="ColumnChart" :data="characterData" :options="characterOptions" v-if="chartLoaded" style="color:red"/>
      </v-card>
       <v-card  color="rgba(100,0,0,.3)" class="display-3 align-center ma-5 rounded-xl" width="30vw" v-else>
                 <h2 >Fill a collection with comics</h2>
                 <h2 >to see collection stats!</h2>
                </v-card>
 
      <v-card color="rgba(100,0,0,.3)" class="display-3 d-flex flex-column align-center ma-5  rounded-xl"  width="30vw"  v-if="creatorData.length>1">
        <h2  class ="ma-auto text-center">{{creatorOptions.chart.title}}</h2>
        <GChart type="ColumnChart" :data="creatorData" :options="creatorOptions" v-if="chartLoaded2" />
      </v-card>
       <v-card color="rgba(100,0,0,.3)" class="display-3 align-center ma-5  rounded-xl" width="30vw" v-else>
                 <h2 >Fill a collection with comics</h2>
                 <h2 >to see collection stats!</h2>
                </v-card>

      <!--<v-carousel v-model="model" >
        <v-carousel-item>
          <v-sheet :color="color" height="100%" tile>
            <v-row class="fill-height" align="center" justify="center" >
              <div class="display-3" v-if="characterData.length>1">
                  <h2 class ="ml-1">{{characterOptions.chart.title}}</h2>
                  <GChart type="ColumnChart" :data="characterData" :options="characterOptions" v-if="chartLoaded" />
              </div>
             
            </v-row>
          </v-sheet>
        </v-carousel-item >
        <v-carousel-item >     
        <v-sheet :color="color" height="100%" tile >
          <v-row class="fill-height" align="center" justify="center" >
              <div class="display-3"  v-if="creatorData.length>1">
                <h2 class ="ml-1">{{creatorOptions.chart.title}}</h2>
                <GChart type="ColumnChart" :data="creatorData" :options="creatorOptions" v-if="chartLoaded2" />
              </div>
              <div class="display-3" v-else>
                 <h2 >Fill a collection with comics</h2>
                 <h2 >to see collection stats!</h2>
                </div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>-->
    </v-card>
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
        user: {},
        model: 0,
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
            hAxis: { format: 'decimal' ,
             textStyle:{color: '#FFF'}
            },
            vAxis: {  
             textStyle:{color: '#FFF'}
            },
            legend: {
        textStyle: { color: 'white' }
    },
            height: 300,
            width:450,
            colors: ['#1b9e77'],
             backgroundColor: {
        fill: '#AA0000',
        fillOpacity: 0.0
      },
            
          },
        creatorData: [
          ['Creator Name', 'comics']
        ],
        creatorOptions: {
            chart: {
              title: "",
            },
            bars: 'vertical', // Required for Material Bar Charts.
            hAxis: { format: 'decimal' ,
             textStyle:{color: '#FFF'}
            },
            vAxis: {  
             textStyle:{color: '#FFF'}
            },
            legend: {
        textStyle: { color: 'white' }
    },
            height: 300,
            width:450,
            colors: ['#1c9e77'],
            backgroundColor: {
        fill: '#AA0000',
        fillOpacity: 0.0
      },
       
          },
          
      }
    }, 
    created(){
      ComicServices.getUserByUsername(this.$route.params.username).then(response =>{
          this.user = response.data;
      }),
      ComicServices.getTopCharacterByUser(this.$route.params.username).then(response =>{
        console.log();
        response.data.forEach(element => {
          let arr = [];
          arr.push(element.name);
          arr.push(element.number);
          this.characterData.push(arr);
        });
        this.characterOptions.chart.title = `Top ${this.characterData.length-1} Characters in User's Collections`;
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
        this.creatorOptions.chart.title = `Top ${this.creatorData.length-1} Creators in Collections`;
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

.semi{
    background: rgba(0,0,100,.3);
}
</style>