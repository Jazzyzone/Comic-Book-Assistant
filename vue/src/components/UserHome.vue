<template>
  <v-container >
    <v-card  dark color=primary  class=" grad1 ma-3">
    <h1  >{{$route.params.username}}
      <v-icon
        color="yellow"
        large
        v-if="this.user.authorities[0].name=='ROLE_PREMIUM'"
      >
        mdi-star
      </v-icon> </h1>

    </v-card>
    <v-card  class="mt-5"  dark color=primary text-center>
      <v-row>
        <v-col cols="6" >
      <div class="display-3" v-if="characterData.length>1">
        <h2 class ="ml-1">{{characterOptions.chart.title}}</h2>
        <GChart type="ColumnChart" :data="characterData" :options="characterOptions" v-if="chartLoaded" />
      </div>
      </v-col>
      <v-col cols="6">
      <div class="display-3"  v-if="creatorData.length>1">
        <h2 class ="ml-1">{{creatorOptions.chart.title}}</h2>
        <GChart type="ColumnChart" :data="creatorData" :options="creatorOptions" v-if="chartLoaded2" />
      </div>
      </v-col>
      </v-row>
      <!--<v-carousel v-model="model" >
        <v-carousel-item>
          <v-sheet :color="color" height="100%" tile>
            <v-row class="fill-height" align="center" justify="center" >
              <div class="display-3" v-if="characterData.length>1">
                  <h2 class ="ml-1">{{characterOptions.chart.title}}</h2>
                  <GChart type="ColumnChart" :data="characterData" :options="characterOptions" v-if="chartLoaded" />
              </div>
              <div class="display-3" v-else>
                 <h2 >Fill a collection with comics</h2>
                 <h2 >to see collection stats!</h2>
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
        color:'primary',
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
.grad1 {
  height: 75px;

}
</style>