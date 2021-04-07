<template>
<div class="card text-center mx-auto bg-light" style="max-width: 650px; margin: 175px 0px" >
  <div class="card-body">
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div>
      <label for="username" class="sr-only">Username:</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <label for="password" class="sr-only">Password:</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <h3 style="margin-top: 50px">Choose your account type</h3>
    <div id="cards_landscape_wrap-2">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                  <div class="card">

        <div class="image-box">
        <img class="card-img-top" src="../images/captain_america_running.png" alt="Card image cap">
        </div>
          <div class="card-body">
            <h5 class="card-title">Standard User</h5>
            <p class="card-text">Sign up for free today! All standard users can save up to 100 comics per collection.</p>
            <button type="button" class="btn btn-primary" v-on:click="user.role = 'standard'">{{user.role === 'standard' ? 'Selected' : 'Become A Standard User'}}</button>
          </div>
          </div>
      </div>
      <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
      <div class="card">
        <div class="image-box">
        <img class="card-img-top" src="../images/ironman_flying.png" alt="Card image cap">
         </div>
          <div class="card-body">
            <h5 class="card-title">Premium User</h5>
            <p class="card-text">Receive all the benefits of a standard account, without a limit on your comic book collections.</p>
            <button type="button" class="btn btn-primary" v-on:click="user.role = 'premium'">{{user.role === 'premium' ? 'Selected' : 'Become A Premium User'}}</button>
          </div>
      </div>
      </div>
            </div>
        </div>
    </div>

      <!-- <p>Select account type:</p>
      <div>
        <input type="radio" id="standard" name="accountType" value="standard"
              checked v-model="user.role">
        <label for="standard">Standard User</label>
      </div>

      <div>
        <input type="radio" id="Premium" name="accountType" value="premium" v-model="user.role">
        <label for="premium">Premium User</label>
      </div> -->


      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create My Account
      </button>
    </form>
  </div>
  </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'standard'
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>


.form-register {
  max-width: 650px;
  justify-content: center;
  margin: 0 auto;
  
  /* margin-top: 100px; */
}

.form-register input {
  margin-bottom: 5px;
  
}

#cards_landscape_wrap-2{
  text-align: center;
  background: #F7F7F7;
}
#cards_landscape_wrap-2 .container{
  padding-top: 30px; 
  padding-bottom: 100px;
}

#cards_landscape_wrap-2 .card {
  border-radius: 5px;
}

#cards_landscape_wrap-2 .card .image-box{
  background: #ffffff;
  overflow: hidden;
  box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.50);
  border-radius: 5px;
}
#cards_landscape_wrap-2 .card .image-box img{
  -webkit-transition:all .9s ease; 
  -moz-transition:all .9s ease; 
  -o-transition:all .9s ease;
  -ms-transition:all .9s ease; 
  width: 100%;
  height: 200px;
}

#cards_landscape_wrap-2 .card:hover .image-box img{
  opacity: 0.7;
  -webkit-transform:scale(1.15);
  -moz-transform:scale(1.15);
  -ms-transform:scale(1.15);
  -o-transform:scale(1.15);
  transform:scale(1.15);
}
#cards_landscape_wrap-2 .card .text-box{
  text-align: center;
}
#cards_landscape_wrap-2 .card .text-box .text-container{
  padding: 30px 18px;
}
#cards_landscape_wrap-2 .card{
  background: #FFFFFF;
  margin-top: 50px;
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
  box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.40);
}
#cards_landscape_wrap-2 .card:hover{
  background: #fff;
  box-shadow: 0px 15px 26px rgba(0, 0, 0, 0.50);
  -webkit-transition: all 0.2s ease-in;
  -moz-transition: all 0.2s ease-in;
  -ms-transition: all 0.2s ease-in;
  -o-transition: all 0.2s ease-in;
  transition: all 0.2s ease-in;
  margin-top: 50px;
}
#cards_landscape_wrap-2 .card .text-box p{
  margin-top: 10px;
  margin-bottom: 0px;
  padding-bottom: 0px; 
  font-size: 14px;
  letter-spacing: 1px;
  color: #000000;
}
#cards_landscape_wrap-2 .card h5{
  margin-top: 0px;
  margin-bottom: 4px; 
  font-size: 18px;
  font-weight: bold;
  text-transform: uppercase;
  font-family: 'Roboto Black', sans-serif;
  letter-spacing: 1px;
  color: #00acc1;
}


</style>
