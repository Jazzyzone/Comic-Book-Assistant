<template>
  <v-container>
    <h1>{{$route.params.username}}</h1>
    <v-divider></v-divider>
    <v-container v-if="isCurrentUser">

       <GChart
  
    :settings="{packages: ['bar']}"       
    :data="chartData"
    :options="chartOptions"
    :createChart="(el, google) => new google.charts.Bar(el)"
    @ready="onChartReady"
      />

      
    </v-container>
    <v-container v-else>
      ITS NOT ME
    </v-container>
  </v-container>
</template>

<script>
import { GChart } from 'vue-google-charts'
 


  export default {
    components: {
      GChart
    },
    data: () => ({
      chartsLib: null, 
      chartData: [
        ['Comic Name', 'Comics'],
        ['Spider-Man', 100],
        ['Thor', 50],
        ['Storm', 26],
        ['Wolverine',10],
        ['Gambit', 4],
      ],
    }),
computed: {
    chartOptions () {
      if (!this.chartsLib) return null
      return this.chartsLib.charts.Bar.convertOptions({
        chart: {
          title: 'Top Five Characters in Collection',
        },
        bars: 'vertical', // Required for Material Bar Charts.
        hAxis: { format: 'decimal' },
        height: 400,
        width:600,
        colors: ['#1b9e77']
      })
    },
  

        isCurrentUser() {
            return this.$route.params.username === this.$store.state.user.username;
        },
    },
  methods: {
    onChartReady (chart, google) {
      this.chartsLib = google
    }
  }
}
</script>

<style>

</style>