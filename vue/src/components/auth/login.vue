<template>
  <div class="py-4 " style="margin-top:10%;">
    <section class="container h-100 bg-white card" style="border-radius: 25px;">
      <div class="container my-5  ">
        <div class="row  justify-content-center align-items-center ">
          <div class="col-6 col-xl-5">
            <img
              src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
              class="img-fluid"
              alt="Sample image"
            />
          </div>
          <div class="col-6 col-xl-4 offset-xl-1">
            <b-alert
              class="mt-4"
              :show="alert.dismissCountDown"
              dismissible
              :variant="alert.variant"
              @dismissed="alert.dismissCountDown = 0"
            >
              <p>{{ alert.msg }}</p>
            </b-alert>
            <form @submit.prevent="logIn">
              <!-- Email input -->
              <div class="form-outline mb-4">
                <input
                  v-model="form.email"
                  type="email"
                  id="form3Example3"
                  class="form-control form-control-lg"
                />
                <label v-if="!form.email" class="form-label" for="form3Example3"
                  >Email address</label
                >
              </div>

              <!-- Password input -->
              <div class="form-outline mb-3">
                <input
                  type="password"
                  v-model="form.password"
                  autocomplete
                  id="form3Example4"
                  class="form-control form-control-lg"
                />
                <label v-if="!form.password" class="form-label" for="form3Example4">Password</label>
              </div>

              <div class="text-center text-lg-start mt-4 pt-2">
                <button
                  class="btn btn-primary btn-lg"
                  style="padding-left: 2.5rem; padding-right: 2.5rem"
                >
                  Login
                </button>
                <p class="small fw-bold mt-2 pt-1 mb-0">
                  Vous n'avez pas de compte ?
                    <router-link to="/register" class="link-danger">
                      s'inscrire</router-link>
                </p>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      form: {
        email: "",
        password: "",
      },
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
    };
  },
  methods: {
    ...mapActions(["LogIn"]),
    logIn() {
      try {
        this.LogIn(this.form).then(() => {
          if (this.$store.getters.authStatus == 2) {
            this.alert.dismissCountDown = 3;
            this.alert.variant = "danger";
            this.alert.msg = this.$store.getters.authMessage;
          }
        });
      } catch (e) {
        console.log(e);
      }
    },
  },
  computed: {
    error() {
      return this.$route.params.msg;
    },
  },
};
</script>

<style>
</style>