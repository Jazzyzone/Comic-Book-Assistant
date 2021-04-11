<template>
  <v-container>
    <h1>{{$route.params.username}}</h1>
    <v-divider></v-divider>
    <v-container v-if="isCurrentUser">
      <v-sparkline
    :value="value"
    :labels="labels"
    :smooth="false"
    
    :line-width="width"
    :stroke-linecap="lineCap"


    :type="type"
    :auto-line-width="autoLineWidth"
    auto-draw
  ><template v-slot:label="item">
            {{ item.value }} {{item.label}}
          </template></v-sparkline>
    </v-container>
    <v-container v-else>
      ITS NOT ME
    </v-container>
  </v-container>
</template>

<script>


  export default {
    data: () => ({
      width: 5,

      lineCap: 'square',
      value: [100,200,300,150,220],
      labels: ["Spider-Man","Iron Man","Thor","Captain America","Thanos"],

      fill: false,
      type: 'bar',
      autoLineWidth: true,
    }),
  computed: {
        
        isCurrentUser() {
            return this.$route.params.username === this.$store.state.user.username;
        },
    },
}
</script>

<style>

</style>