<template>
  <div class="" style="">
    <!-- <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
        </b-alert>
        <form @submit.prevent="register">
            <div class="form-group">
                <label for="name">Name</label>
                <input required  required type="text" class="form-control" id="name" v-model="form.name" placeholder="Name">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input required  required type="email" class="form-control" id="email" v-model="form.email" placeholder="Email">
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
                <input required  required type="password" class="form-control" id="password" v-model="form.password"
                    placeholder="Password">
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input required  required type="password" class="form-control" id="confirm_password" v-model="form.confirm_password"
                    placeholder="Confirm Password">
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form> -->
    <section class="vh-100">
      <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-lg-12 col-xl-11">
            <div class="card text-black" style="border-radius: 25px">
              <div class="card-body p-md-5">
                <div class="row justify-content-center">
                  <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">
                      S'inscrire
                    </p>
                    <b-alert
                      class="mt-4"
                      :show="alert.dismissCountDown"
                      dismissible
                      :variant="alert.variant"
                      @dismissed="alert.dismissCountDown = 0"
                    >
                      <p>{{ alert.msg }}</p>
                    </b-alert>
                    <form class="mx-1 mx-md-4" @submit.prevent="register">
                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <input
                            required
                            type="text"
                            id="form3Example1c"
                            class="form-control"
                            v-model="form.name"
                          />
                          <label
                            v-if="!form.name"
                            class="form-label"
                            for="form3Example1c"
                            >Ton nom</label
                          >
                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <input
                            required
                            type="email"
                            id="form3Example3c"
                            class="form-control"
                            v-model="form.email"
                          />
                          <label
                            v-if="!form.email"
                            class="form-label"
                            for="form3Example3c"
                            >Ton Email</label
                          >
                        </div>
                      </div>

                      <div
                        class="
                          form-group
                          d-flex
                          flex-row
                          align-items-center
                          mb-4
                        "
                      >
                        <label for="role" class="mr-4"
                          >Role : &nbsp; &nbsp;</label
                        >
                        <select
                          required
                          class="form-control w-auto"
                          id="role"
                          v-model="form.role"
                        >
                          <option value="2">Etudiant</option>
                          <option value="6">Technicien</option>
                          <option value="5">Chef departement</option>
                          <option value="7">Enseignant</option>
                        </select>
                      </div>
                      <div
                        class="
                          form-group
                          d-flex
                          flex-row
                          align-items-center
                          mb-4
                        "
                      >
                        <label for="sexe" class="mr-4"
                          >Sexe : &nbsp; &nbsp;</label
                        >
                        <select
                          required
                          class="form-control w-auto"
                          id="sexe"
                          v-model="form.sexe"
                        >
                          <option value="homme">Homme</option>
                          <option value="femme">Femme</option>
                        </select>
                      </div>
                      <div
                        class="
                          form-group
                          d-flex
                          flex-row
                          align-items-center
                          mb-4
                        "
                      >
                        <label for="department" class="mr-4"
                          >Department : &nbsp; &nbsp;</label
                        >
                        <select
                          required
                          class="form-control w-auto"
                          id="department"
                          @change="fetchClasses(form.department)"
                          v-model="form.department"
                        >
                          <option
                            v-for="departement in departements"
                            :key="departement.id"
                            :value="departement.id"
                          >
                            {{ departement.titre }}
                          </option>
                        </select>
                      </div>
                      <div
                        v-if="form.role == 2"
                        class="
                          form-group
                          d-flex
                          flex-row
                          align-items-center
                          mb-4
                        "
                      >
                        <label for="department">Classe : &nbsp; &nbsp;</label>
                        <select
                          required
                          class="form-control w-auto"
                          id="department"
                          v-model="form.classe"
                        >
                          <option
                            v-for="classe in classes"
                            :key="classe.id"
                            :value="classe.id"
                          >
                            {{ classe.abreviation }}
                          </option>
                        </select>
                      </div>
                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <input
                            required
                            type="password"
                            id="form3Example4c"
                            class="form-control"
                            v-model="form.password"
                          />
                          <label
                            v-if="!form.password"
                            class="form-label"
                            for="form3Example4c"
                            >Ton mot de passe</label
                          >
                        </div>
                      </div>

                      <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                        <div class="form-outline flex-fill mb-0">
                          <input
                            required
                            type="password"
                            id="form3Example4cd"
                            class="form-control"
                            v-model="form.confirm_password"
                          />
                          <label
                            v-if="!form.confirm_password"
                            class="form-label"
                            for="form3Example4cd"
                            >Confirmer ton mot de passe</label
                          >
                        </div>
                      </div>

                      <div
                        class="form-check d-flex justify-content-center mb-5"
                      >
                        <input
                          required
                          class="form-check-input required me-2"
                          type="checkbox"
                          value=""
                          id="form2Example3c"
                        />
                        <label class="form-check-label" for="form2Example3">
                          I agree all statements in Terms of service
                        </label>
                      </div>

                      <div
                        class="d-flex justify-content-center mx-4 mb-3 mb-lg-4"
                      >
                        <button class="btn btn-primary btn-lg">
                          S'inscrire
                        </button>
                      </div>
                        <p class="small fw-bold mt-2 pt-1 mb-0">
                          Vous avez un compte ?
                          <router-link to="/login" class="link-danger">
                            se connecter</router-link
                          >
                        </p>
                    </form>
                  </div>
                  <div
                    class="
                      col-md-10 col-lg-6 col-xl-7
                      d-flex
                      align-items-center
                      order-1 order-lg-2
                    "
                  >
                    <img
                      src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                      class="img-fluid"
                      alt="Sample image"
                    />
                  </div>
                </div>
              </div>
            </div>
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
        name: "",
        email: "",
        password: "",
        confirm_password: "",
        classe: 0,
      },
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      departements: [],
      classes: [],
    };
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
      if (this.form.password != this.form.confirm_password) {
        this.alert.dismissCountDown = 5;
        this.alert.variant = "danger";
        this.alert.msg = "Password and Confirm Password are not the same";
        return;
      } else {
        this.Register(this.form).then(() => {
          if (this.$store.getters.regStatus == 2) {
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
      fetch("http://localhost:8000/api/classe/" + id)
        .then((response) => response.json())
        .then((data) => {
          this.classes = data;
        });
    },
  },
};
</script>

<style>
</style>