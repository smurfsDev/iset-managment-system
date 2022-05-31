<template>
  <div>
    <div class="main-content" v-if="$store.getters.isChefDepartement">
      <div class="header bg-gradient-primary pb-8 pt-5 pt-md-8">
        <div class="container-fluid">
          <h1 class="mb-5 text-white">DashBoard</h1>
          <div class="header-body">
            <div class="row">
              <div class="mycrd col-xl-3 col-lg-4">
                <div class="card card-stats mb-4 mb-xl-0">
                  <div class="card-body">
                    <div class="row">
                      <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-0">
                          Nombre classes
                        </h5>
                        <span class="h2 font-weight-bold mb-0">{{
                          classes[0].nbr
                        }}</span>
                      </div>
                      <div class="col-auto">
                        <div
                          class="
                            icon icon-shape
                            bg-danger
                            text-white
                            rounded-circle
                            shadow
                          "
                        >
                          <i class="fas fa-chart-bar"></i>
                        </div>
                      </div>
                    </div>
                    <p class="mt-3 mb-0 text-muted text-sm">
                      <span class="text-nowrap">Dernière mise à jour</span>
                      <span class="text-success mr-2">
                        <!-- <i class="fa fa-arrow-up"></i>  -->
                        {{ classes[0].date.substring(0, 10) }}</span
                      >
                    </p>
                  </div>
                </div>
              </div>
              <div class="mycrd col-xl-3 col-lg-4">
                <div class="card card-stats mb-4 mb-xl-0">
                  <div class="card-body">
                    <div class="row">
                      <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-0">
                          Nombre etudiants
                        </h5>
                        <span class="h2 font-weight-bold mb-0">{{
                          students[0].nbr
                        }}</span>
                      </div>
                      <div class="col-auto">
                        <div
                          class="
                            icon icon-shape
                            bg-warning
                            text-white
                            rounded-circle
                            shadow
                          "
                        >
                          <i class="fas fa-chart-pie"></i>
                        </div>
                      </div>
                    </div>
                    <p class="mt-3 mb-0 text-muted text-sm">
                      <span class="text-nowrap">Dernière mise à jour</span>
                      <span class="text-danger mr-2">
                        <!-- <i class="fas fa-arrow-down"></i>  -->
                        {{ students[0].date.substring(0, 10) }}</span
                      >
                    </p>
                  </div>
                </div>
              </div>
              <div class="mycrd col-xl-3 col-lg-4">
                <div class="card card-stats mb-4 mb-xl-0">
                  <div class="card-body">
                    <div class="row">
                      <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-0">
                          Nombre enseignant
                        </h5>
                        <span class="h2 font-weight-bold mb-0">{{
                          teachers.nbr[0]["count(*)"]
                        }}</span>
                      </div>
                      <div class="col-auto">
                        <div
                          class="
                            icon icon-shape
                            bg-dark
                            text-white
                            rounded-circle
                            shadow
                          "
                        >
                          <i class="fas fa-users"></i>
                        </div>
                      </div>
                    </div>
                    <p class="mt-3 mb-0 text-muted text-sm">
                      <span class="text-nowrap">Dernière mise à jour</span>
                      <span class="text-warning mr-2">
                        <!-- <i class="fas fa-arrow-down"></i> -->
                        {{ teachers.date.substring(0, 10) }}</span
                      >
                    </p>
                  </div>
                </div>
              </div>
              <div class="mycrd col-xl-3 col-lg-4">
                <div class="card card-stats mb-4 mb-xl-0">
                  <div class="card-body">
                    <div class="row">
                      <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-0">
                          Moyen general
                        </h5>
                        <span class="h2 font-weight-bold mb-0">{{
                          moyennes.nbr[0]
                        }}</span>
                      </div>
                      <div class="col-auto">
                        <div
                          class="
                            icon icon-shape
                            bg-info
                            text-white
                            rounded-circle
                            shadow
                          "
                        >
                          <i class="fas fa-percent"></i>
                        </div>
                      </div>
                    </div>
                    <p class="mt-3 mb-0 text-muted text-sm">
                      <span class="text-nowrap">Dernière mise à jour</span>
                      <span class="text-success mr-2">
                        <!-- <i class="fas fa-arrow-up"></i> -->
                        {{ moyennes.date.substring(0, 10) }}</span
                      >
                    </p>
                  </div>
                </div>
              </div>
              <div class="mycrd col-xl-3 col-lg-4">
                <div class="card card-stats mb-4 mb-xl-0">
                  <div class="card-body">
                    <div class="row">
                      <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-0">
                          Demandes materiel en cours
                        </h5>
                        <span class="h2 font-weight-bold mb-0">{{
                          demandeMateriels.nbr
                        }}</span>
                      </div>
                      <div class="col-auto">
                        <div
                          class="
                            icon icon-shape
                            bg-dark
                            text-white
                            rounded-circle
                            shadow
                          "
                        >
                          <i class="fa fa-users"></i>
                        </div>
                      </div>
                    </div>
                    <p class="mt-3 mb-0 text-muted text-sm">
                      <span class="text-nowrap">Dernière mise à jour</span>
                      <span class="text-warning mr-2">
                        <!-- <i class="fas fa-arrow-down"></i> -->
                        {{ demandeMateriels.date.substring(0, 10) }}</span
                      >
                    </p>
                  </div>
                </div>
              </div>
              <div class="mycrd col-xl-3 col-lg-4">
                <div class="card card-stats mb-4 mb-xl-0">
                  <div class="card-body">
                    <div class="row">
                      <div class="col">
                        <h5 class="card-title text-uppercase text-muted mb-0">
                          Demandes salles en cours
                        </h5>
                        <span class="h2 font-weight-bold mb-0">{{
                          demandeSalles.nbr
                        }}</span>
                      </div>
                      <div class="col-auto">
                        <div
                          class="
                            icon icon-shape
                            bg-info
                            text-white
                            rounded-circle
                            shadow
                          "
                        >
                          <i class="fas fa-percent"></i>
                        </div>
                      </div>
                    </div>
                    <p class="mt-3 mb-0 text-muted text-sm">
                      <span class="text-nowrap">Dernière mise à jour</span>
                      <span class="text-success mr-2">
                        <!-- <i class="fas fa-arrow-up"></i> -->
                        {{ demandeSalles.date.substring(0, 10) }}</span
                      >
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row my-5">
              <div class="row my-2 mx-4">
                <div class="col-5 bg-light rounded">
                  <line-chart
                    text="Nombre des etudiants"
                    label="Nombre"
                    :labels="['Mars', 'Avril', 'May']"
                    :data="[10, 20, 30]"
                  ></line-chart>
                </div>
                <div class="col-1"></div>
                <div class="col-5 bg-light rounded">
                  <area-chart
                    title="Sexe etudiants"
                    label="a"
                    :labels="['Homme', 'Femme']"
                    :data="hf"
                  ></area-chart>
                </div>
              </div>
              <div class="row my-2 mx-4">
                <div class="col-3"></div>
                <div class="col-6 bg-light rounded">
                  <bar-chart
                    text="moyenne par classes"
                    label="moyennes"
                    :labels="['Ti13', 'Ti12', 'Ti11']"
                    :data="[10, 20, 30]"
                  ></bar-chart>
                </div>
                <div class="col-3"></div>
                <!-- <div class="col-1"></div>
                <div class="col-5 bg-light rounded">
                  <bar-chart
                    label="a"
                    :labels="['Item 1', 'Item 2', 'Item 3']"
                    :data="[10, 20, 30]"
                  ></bar-chart>
                </div> -->
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Page content -->
    </div>
  </div>
</template>

<script>
import barChart from "./barChart.vue";
import lineChart from "./lineChart.vue";
import areaChart from "./areaChart.vue";
export default {
  components: {
    barChart,
    lineChart,
    areaChart,
  },
  created() {
    this.fetchStats();
  },
  data() {
    return {
      classes: [
        {
          nbr: 0,
          date: null,
        },
      ],
      students: [
        {
          nbr: 0,
          date: null,
        },
      ],
      teachers: [
        {
          nbr: 0,
          date: null,
        },
      ],
      moyennes: [{
        nbr: 0,
        date: null,
      }],
      demandeMateriels: [{
        nbr: 0,
        date: null,
      }],
      demandeSalles: [{
        nbr: 0,
        date: null,
      }],
      hf: {
        h: 0,
        f: 0,
      },
    };
  },
  methods: {
    fetchStats() {
      this.$http.get("http://localhost:8000/api/stats").then((res) => {
        this.classes = res.data.classes;

        this.students = res.data.students;
        this.teachers = res.data.teachers;
        this.moyennes = res.data.moyennes;
        this.demandeMateriels = res.data.demandeMateriels;
        this.demandeSalles = res.data.demandeSalles;
        this.hf = res.data.hf;
        let myh = this.hf;
        this.hf = [];
        this.hf[0] = myh.h["COUNT(*)"];
        this.hf[1] = myh.f["COUNT(*)"];
      });
    },
  },
};
</script>

<style>
.icon {
  width: 3rem;
  height: 3rem;
}

.icon i {
  font-size: 2.25rem;
}

.icon-shape {
  display: inline-flex;
  padding: 12px;
  text-align: center;
  border-radius: 50%;
  align-items: center;
  justify-content: center;
}

.icon-shape i {
  font-size: 1.25rem;
}
.bg-gradient-primary {
  background: linear-gradient(87deg, #5e72e4 0, #825ee4 100%) !important;
}

.bg-gradient-primary {
  background: linear-gradient(87deg, #5e72e4 0, #825ee4 100%) !important;
}

.mycrd {
  margin-top: 1rem;
  margin-bottom: 1rem;
}
</style>