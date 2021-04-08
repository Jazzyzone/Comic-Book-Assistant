<template>
  <div>
    <h1>Welcome {{this.$store.state.user.username}}</h1>
    <p><router-link :to="{ name: 'userCollections', params: {username: this.$store.state.user.username} }">View My Comic Book Collections</router-link></p>
    <p><router-link :to="{ name: 'AddACollection', params: {username: this.$store.state.user.username} }">Add Comic Book Collection</router-link></p>
    <p>Add Comic Book</p>
    <p>My Friends</p>
    <p>See Collections of Others</p>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>