<template>
    <div class="login-form">
        <form @submit.prevent="logIn">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" v-model="form.email" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" v-model="form.password"
                    placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</template>

<script>
// import axios from "axios";
import { mapActions } from "vuex";

export default {
    data() {
        return {
            form: {
                email: '',
                password: ''
            }
        }
    },
    methods: {
        ...mapActions(["LogIn"]),
         logIn() {
            this.LogIn(this.form);
            try {
                 this.LogIn(this.form)
            } catch (error) {
                this.showError = true
            }

        },
    }

}
</script>

<style>
</style>