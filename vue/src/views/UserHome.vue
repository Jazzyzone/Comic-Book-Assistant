//displays the users home page when signed in
<template>
  <div>
      <user-collections />
  </div>
</template>

<script>
import UserCollections from '@/components/UserCollections'
export default {
  components: { UserCollections },

}
</script>

<style>

</style>