<template>
  <v-container fluid>
    <h1>Welcome To Comic Elevator</h1>
    <p>
      Home to comic enthusiasts who wish to keep their collections all in one
      place.
    </p>

      <v-spacer></v-spacer>
          <v-text-field
            placeholder="Search For Collections"
            prepend-inner-icon="mdi-magnify"
            class="expanding-search mt-1"
            filled
            dense
            dark
          ></v-text-field>

    <v-carousel
      v-model="model"
      cycle
      height="400"
      hide-delimiter-background
      show-arrows-on-hover
    >
    <v-card max-width="2000">
      <v-carousel-item>
        <v-sheet color="pink" height="100%" tile>
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" align="center">
              Comic Elevator Lets You Make Your Own Custom Collection of Great
              Comics
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet color="purple" height="100%" tile>
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" align="center">
              Love Your Collection? Why Not Share It With A Friend?
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet color="red" height="100%" tile>
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" align="center">
              Quickly Check How Many Comics You Have and Aim For Top Of The
              Leaderboard!
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      </v-card>
    </v-carousel>

    <v-divider></v-divider>
    <v-banner elevation="9" single-line
      ><h2><span class="mdi mdi-chart-box"></span> Check Out Some of the Current
      Stats of the Site Below</h2></v-banner
    >
   
    <v-divider></v-divider>
 
   <v-card color="transparent" class="mt-5 semi d-flex flex-row align-center justify-space-around" dark align-center text-center>
            <v-card color="rgba(100,0,0,.3)" class="mt-5 semi d-flex flex-row align-center justify-space-around" dark align-center text-center>
            <div class="display-2" v-if="characterData.length > 1">
              <h2 class="ml-1">{{ characterOptions.chart.title }}</h2>
              <GChart type="ColumnChart" :data="characterData" :options="characterOptions" v-if="chartLoaded"
              />
            </div>
            </v-card>
            <v-card  color="rgba(100,0,0,.3)" class="display-3 align-center ma-5 rounded-xl" width="30vw">
                </v-card>
      
 <v-card color="rgba(100,0,0,.3)" class="mt-5 semi d-flex flex-row align-center justify-space-around"  dark align-center text-center>
            <div class="display-2" v-if="creatorData.length > 1">
              <h2 class="ml-1">{{ creatorOptions.chart.title }}</h2>
              <GChart type="ColumnChart" :data="creatorData" :options="creatorOptions" v-if="chartLoaded2"
              />
            </div>
 </v-card>
   </v-card>

     </v-container>

</template>

<script>
import ComicServices from "../services/ComicServices";
import { GChart } from "vue-google-charts";

export default {
  components: {
    GChart,
  },
  data() {
    return {
      model: 0,
      chartLoaded: false,
      chartLoaded2: false,
      characterData: [["Comic Name", "Comics"]],
      characterOptions: {
        chart: {
          title: "",
        },
        bars: "vertical", // Required for Material Bar Charts.
        hAxis: { format: "decimal" },
        height: 400,
        width: 600,
        colors: ["#1b9e77"],
      },
      creatorData: [["Creator Name", "comics"]],
      creatorOptions: {
        chart: {
          title: "",
        },
        bars: "vertical", // Required for Material Bar Charts.
        hAxis: { format: "decimal" },
        height: 400,
        width: 600,
        colors: ["#1c9e77"],
      },
    };
  },
  created() {
    ComicServices.getAllTopCharacterByUser().then((response) => {
      console.log(response.data);
      response.data.forEach((element) => {
        let arr = [];
        arr.push(element.name);
        arr.push(element.number);
        this.characterData.push(arr);
      });
      this.characterOptions.chart.title = `Top ${
        this.characterData.length - 1
      } Characters in Collections`;
      this.chartLoaded = true;
    });
    ComicServices.getAllTopCreatorByUser().then((response) => {
      console.log(response.data);
      response.data.forEach((element) => {
        let arr = [];
        arr.push(element.name);
        arr.push(element.number);
        this.creatorData.push(arr);
      });
      this.creatorOptions.chart.title = `Top ${
        this.characterData.length - 1
      } Creators in Collections`;
      this.chartLoaded2 = true;
    });
  },
  methods: {
    onVharacterReady(chart, google) {
      this.characterLib = google;
    },
    onCreatorReady(chart, google) {
      this.creatorLib = google;
    },
  },
};
</script>
<style>
.small {
  max-width: 300px;
  margin: 150px auto;
}
.expanding-search{
  border-color: transparent;
  max-width: 45px;
}

</style>
