//displays the users home page when signed in
<template>
  <v-container>
      <user-home />
  </v-container>
</template>

<script>
import UserHome from '@/components/UserHome'
export default {
  components: { UserHome },

}
</script>

<style>

</style>