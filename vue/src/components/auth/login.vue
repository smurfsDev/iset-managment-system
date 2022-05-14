<template>
    <div class="container login-form">
        <div v-if="error" class="alert alert-danger">
            {{ error }}
        </div>
        <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
        </b-alert>
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
import { mapActions } from "vuex";

export default {
    data() {
        return {
            form: {
                email: '',
                password: ''
            },
            alert: {
                dismissCountDown: 0,
                variant: "",
                msg: "",
            },
        }
    },
    methods: {
        ...mapActions(["LogIn"]),
        logIn() {
            try {
                this.LogIn(this.form).then(()=>{
                    if(this.$store.getters.authStatus==2){
                        this.alert.dismissCountDown = 3;
                        this.alert.variant = "danger";
                        this.alert.msg = this.$store.getters.authMessage;
                    }
                });
            }catch (e) {
                console.log(e);
            }

            
        },
    },
    computed: {
        error() {
            return this.$route.params.msg
        }
    }

}
</script>

<style>
</style>