<template>
  <v-container fluid>
    <h1>Welcome To Comic Elevator</h1>
    <img src="@/images/marvel_comics_cast.png">

    <v-spacer></v-spacer>

  <!--  <v-carousel
      v-model="model"
      cycle
      height="400"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-card max-width="2000">
        <v-carousel-item>
          <v-sheet color="white" height="100%" tile shaped>
            <v-row class="fill-height" align="center" justify="center">
              <div class="display-3" align="center">
                <img src="@/images/ironman_flying.png">
              </div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
        <v-carousel-item>
          <v-sheet color="purple" height="100%" tile>
            <v-row class="fill-height" align="center" justify="center">
              <div class="display-3" align="center">
                <img src="@/images/captain_america_running.png">
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
    </v-carousel> -->

  </v-container>
</template>

<script>

export default {
  components: {
  },
  data() {
    return {
      model: 0,
    };
  },
  created() {

  },
};
</script>
<style>
h1 {
  text-align: center;
  color: whitesmoke;
}
</style>
