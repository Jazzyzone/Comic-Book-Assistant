<template>
  <v-container class="friends-container">
      
      <v-expand-transition>
      <v-flex >
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
              <!-- <v-btn
        class="ma-2 bg"
        color="primary"
        @click="expand = !expand"
      >
        View Friends
      </v-btn> -->
             <div class="friends-display" @click="expand = !expand">
                <p>Friends</p>
                 </div>
              <div v-show="expand">
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
      </v-expand-transition>
  </v-container>
</template>

<script>
export default {
    data() {
        return {
            expand: false,
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
    height: 320px;
    overflow: auto;
    position: fixed;
    bottom: 0;
    right: 0;
    z-index: 10;
    font-size: 1rem;
    background-color: black;
}
.friends-display {
    color: white;
    background-color: black;
    text-align: center;
}
.sticky {
    position: sticky;
}

.headline {
    border: 1px solid black;
    height: 60px;
    justify-content: center;    
}

</style>