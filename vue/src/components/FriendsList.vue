<template>
  <v-container class="friends-container">
      <v-flex class="sticky">
          <!-- remove this an uncomment code below-->
          <!-- <v-sheet v-for="friend in friends.userId"
            v-bind:key="friend.id"
            >
            <div>
                <v-card-title 
                v-text="friend.name">
                </v-card-title>
            </div> 
          </v-sheet> -->
          <v-sheet>
              <div>
                  <v-card-title class="headline">
                      placeholder1
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder2
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder3
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder4
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder5
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder6
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder7
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder8
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder9
                  </v-card-title>
                   <v-card-title class="headline">
                      placeholder10
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder12
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder13
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder14
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder15
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder16
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder17
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder18
                  </v-card-title>
                  <v-card-title class="headline">
                      placeholder19
                  </v-card-title>
              </div>
          </v-sheet>
      </v-flex>
  </v-container>
</template>

<script>
export default {
    data() {
        return {
            friends: {

            }
        }
    },
    created() {

    },
    computed: {

    }
}
</script>

<style scoped>
.friends-container {
    margin-bottom: 43px;
    margin-top: 150px;
    width:250px;
    height: 400px;
    overflow: auto;
    position: absolute;
    bottom: 0;
    right: 0;
}
.sticky {
    position: sticky;
}

.headline {
    border: 1px solid black;
}
</style>