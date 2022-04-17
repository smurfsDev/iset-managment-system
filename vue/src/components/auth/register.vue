<template>
    <div class="login-form">
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
            <input type="password" class="form-control" id="password" v-model="form.password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="c_password">Confirm Password</label>
            <input type="password" class="form-control" id="c_password" v-model="form.c_password" placeholder="Confirm Password">
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</template>

<script>
export default {

    data() {
        return {
            form: {
                name: '',
                email: '',
                password: '',
                c_password: ''
            }
        }
    },

    methods: {
        register() {
            // if (this.form.password !== this.form.c_password) {
            //     this.$toast.error('Passwords do not match');
            //     return;
            // }

            // this.$http.post('/api/register', this.form)
            //     .then(response => {
            //         this.$toast.success('Registration successful');
            //         this.$router.push('/login');
            //     })
            //     .catch(error => {
            //         this.$toast.error(error.response.data.message);
            //     });
        }
    }


}
</script>

<style>

</style>