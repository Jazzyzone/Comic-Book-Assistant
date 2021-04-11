<template>
  <v-container>
    <v-carousel
     v-model="model"
      cycle
      height="400"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item>
        <v-sheet color="pink" height="100%" tile>
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" align="center">
              Comic Elevator Lets You Make Your Own Custom of Great Comics
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet color="blue" height="100%" tile>
          <v-row class="fill-height" align="center" justify="center">
            <div class="display-3" align="center">
              Love Your Collection? Share It With A Friend?
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
    </v-carousel>

  <v-divider></v-divider>
  <v-banner
  elevation="6"
  icon="mdi-graph"
  single-line
  >Check Your Stats Below</v-banner>
   <h1>Welcome To Comic Elevator</h1>

  </v-container>
</template>

<script>
import ComicServices from "../services/ComicServices";

export default {
  name: "home",
  data() {
    
    return {
      datacollection: null,
      userSearch: "",
    };
 
  },
  mounted() {
    this.fillData();
  },
  methods: {
    searchUser() {
      ComicServices.getUserByUsername(this.userSearch).then((response) => {
        console.log(response.data.username);
        if (response.data !== "") {
          this.$store.commit("SET_USERSEARCH", response.data.username);
          this.$router.push({
            name: "SearchUser",
            params: { username: response.data.username },
          });
        }
      });
    },
    fillData() {
      this.datacollection = {
        labels: [this.getRandomInt(), this.getRandomInt()],
        datasets: [
          {
            label: "Data One",
            backgroundColor: "#f87979",
            data: [this.getRandomInt(), this.getRandomInt()],
          },
          {
            label: "Data One",
            backgroundColor: "#f87979",
            data: [this.getRandomInt(), this.getRandomInt()],
          },
        ],
      };
    },
    getRandomInt() {
      return Math.floor(Math.random() * (50 - 5 + 1)) + 5;
    },
  },
};
</script>
<style>
.small {
  max-width: 600px;
  margin: 150px auto;
}
</style>
