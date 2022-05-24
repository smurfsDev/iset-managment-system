<template>
    <div class="login-form container">
         <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
        </b-alert>
        <form @submit.prevent="register">
            <div class="form-group">
                <label for="name">Name</label>
                <input required type="text" class="form-control" id="name" v-model="form.name" placeholder="Name">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input required type="email" class="form-control" id="email" v-model="form.email" placeholder="Email">
            </div>
            
            <div class="form-group">
                <label for="role">Role</label>
                <select required class="form-control" id="role" v-model="form.role">
                    <option value="2">Etudiant</option>
                    <option value="6">Technicien</option>
                    <option value="5">Chef departement</option>
                    <option value="7">Enseignant</option>
                </select>
            </div>
            <div class="form-group">
                <label for="department">Department</label>
                <select required class="form-control" id="department" @change="fetchClasses(form.department)" v-model="form.department">
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
                <select required class="form-control"  id="department" v-model="form.classe">
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
                <label for="password">Password</label>
                <input required type="password" class="form-control" id="password" v-model="form.password"
                    placeholder="Password">
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input required type="password" class="form-control" id="confirm_password" v-model="form.confirm_password"
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
                confirm_password: '',
                classe :0
            },
            alert: {
                dismissCountDown: 0,
                variant: "",
                msg: "",
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
            if(this.form.password != this.form.confirm_password){
                this.alert.dismissCountDown = 5;
                this.alert.variant = "danger";
                this.alert.msg = "Password and Confirm Password are not the same";
                return;
            }else{
                this.Register(this.form).then(()=>{
                    if(this.$store.getters.regStatus==2){
                        this.alert.dismissCountDown = 3;
                        this.alert.variant = "danger";
                        this.alert.msg = this.$store.getters.regMessage;
                    }
                });
            }
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