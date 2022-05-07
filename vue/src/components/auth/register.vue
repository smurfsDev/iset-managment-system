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
                <label for="department">Department</label>
                <select class="form-control" id="department" @change="fetchClasses(form.department)" v-model="form.department">
                  <option 
                    v-for="departement in departements"
                    :key="departement.id"
                    :value="departement.id" 
                  >
                    {{ departement.titre }}
                  </option>
                </select>
            </div>
            <div v-if="form.role==2" class="form-group">
                <label for="department">Classe</label>
                <select class="form-control"  id="department" v-model="form.classe">
                  <option 
                    v-for="classe in classes"
                    :key="classe.id"
                    :value="classe.id" 
                  >
                    {{ classe.abreviation }}
                  </option>
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
            },
            departements: [],
            classes: [],

        }
    },
    created() {
        this.fetchDepartements();
    },
    methods: {
        ...mapActions(["Register", "LogOut"]),
        logout() {
            this.LogOut();
        },
        register() {
            this.Register(this.form);
        },
        fetchDepartements() {
            fetch("http://localhost:8000/api/Departement")
                .then((response) => response.json())
                .then((data) => {
                    this.departements = data.data;
                });
        },
        fetchClasses(id) {
            fetch("http://localhost:8000/api/classe/"+id)
                .then((response) => response.json())
                .then((data) => {
                    this.classes = data;
                });
        }
    }


}
</script>

<style>
</style>