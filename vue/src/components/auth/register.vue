<template>
    <div class="login-form container">
        <form @submit.prevent="register">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" v-model="form.name" placeholder="Name">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" v-model="form.email" placeholder="Email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" v-model="form.password"
                    placeholder="Password">
            </div>
            <div class="form-group">
                <label for="role">Role</label>
                <select class="form-control" id="role" v-model="form.role">
                    <option value="2">Etudiant</option>
                    <option value="5">Chef departement</option>
                </select>
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input type="password" class="form-control" id="confirm_password" v-model="form.confirm_password"
                    placeholder="Confirm Password">
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</template>

<script>
import { mapActions } from "vuex";
export default {

    data() {
        return {
            form: {
                name: '',
                email: '',
                password: '',
                confirm_password: ''
            }
        }
    },

    methods: {
        ...mapActions(["Register", "LogOut"]),
        logout() {
            this.LogOut();
        },
        register() {
            this.Register(this.form);
        }
    }


}
</script>

<style>
</style>