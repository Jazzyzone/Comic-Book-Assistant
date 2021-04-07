//Add a collection to a user list of collections

<template>
<div>
    <form v-on="submit">
        <label for="collectionName">Name:</label>
        <input type="text" id="collectionName" name="collectionName" v-model="collections.name"><input>

        <label for="porp">Private or Public?</label>
        <input type="radio" id="porp" value="true" v-model="collections.private">
        <input type="radio" id="porp" value="false" v-model="collections.private">
        
        <label for="addCollection">Submit</label>
        <input type="button" id="addCollection">

    </form>
  </div>
</template>

<script>
export default {
    data() {
        return {
            collections: {
                user_id: this.$store.state.user.id,
                name: "",
                private: false
            }
        };

    },
    methods: {
        addCollection(){
            
        }
    }

}
</script>

<style>

</style>