<template>
  <v-container  ma-0 pa-0 fill-height>
    <img v-img src="@/images/marvel_comics_cast.png" class="homepage" />

    <div>
    <h1>Comic Elevator</h1>
    <h3>
      Make your own custom collections and add your favorite marvel comics to
      them. Manage your comic book collections and see how your stats compare to other users.
    </h3>
    </div>

  </v-container>

</template>

<script>
export default {
  components: {},
  data() {
    return {
      model: 0,
    };
  },
  created() {},
};
</script>
<style>
h1 {
  text-align: center;
  color: whitesmoke;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}

h3 {
  text-align: center;
  color: whitesmoke;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  overflow: visible;
}

.homepage {
  width:auto;
   height:auto;
   max-width:100%;
   max-height:100%;
}
</style>
