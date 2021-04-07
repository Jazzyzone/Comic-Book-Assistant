<template>
  <div class="home">
    <!-- <h1>Welcome {{this.$store.state.user.username}}</h1> -->
    <router-link :to="{ name: 'UserHome', params: {username: this.$store.state.user.username} }">View My Page</router-link>
    <!-- <p>Add Comic Book Collection</p>
    <p>Add Comic Book</p>
    <p>My Friends</p>
    <p>See Collections of Others</p> -->
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
