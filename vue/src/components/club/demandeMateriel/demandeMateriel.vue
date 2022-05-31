<template>
  <div class="content container">
    <formDemandeMateriel @addDemande="addDemande" :oldDemande="demande" :categories="categories" />
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
              <b-col cols="8">
                <button type="button" class="btn btn-primary mx-1 float-start" data-bs-toggle="modal"
                  @click="initModal()" data-bs-target="#demandeModal">
                  Nouvelle demande
                </button>
              </b-col>
              <b-col></b-col>
            </b-row>
          </b-container>
          <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <Demandemateriels @deleteDemande="deleteDemande" :demandes="DemandeMater" @deleteMateriel="deleteMateriel"
            @fetchDemande="fetchDemandeMateriels" @updateDemande="updateDemande" @attachMateriel="attachMateriel"
            :pagination="pagination" @setQuantity="setQuantity" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Demandemateriels from "./show.vue";
import formDemandeMateriel from "./creeDemandeForm.vue";
// import search from '../search.vue';

export default {
  components: {
    Demandemateriels,
    formDemandeMateriel,
    //     search
  },
  data() {
    return {
      categories: [],
      DemandeMater: [],
      Materiels: [],
      demande: {},
      pagination: {},
      edit: false,
      search: "",
      show: true,
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      myid: 1,
      idCategorie: "",
    };
  },
  created() {
    document.title = "Demande";
    this.fetchDemandeMateriels();
    this.fetchCategories();

    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "materiel ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "materiel modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchDemandeMateriels(page_url = "http://127.0.0.1:8000/api/dm") {
      let vm = this;
      this.$http.get(page_url)
        .then((res) => {
          res = res.data;
          if (res.constructor !== Array) {
            this.DemandeMater = res.data;
          } else {
            this.DemandeMater = [];
          }
          this.show = false;
          vm.makePagination(res);
        })
        .catch((err) => console.log(err));
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/dm?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteDemande(id) {
      if (confirm("Supprimer demande " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/dm/" + id)
          .then(() => {
            this.fetchDemandeMateriels();
            this.alert.variant = "danger";
            this.alert.msg = "Demande suprimée avec succès";
            this.alert.dismissCountDown = 5;
          })
          .then(() => { })
          .catch((err) => console.log(err));
      }
    },
    resetModal1() {
      this.document = {};
    },
    addDemande(demande) {
      this.show = true;
      if (!this.edit) {
        this.$http.post("http://localhost:8000/api/dm/", demande)
          .then((res) => res.data)
          .then((data) => {
            if (data.success == false) {
              this.alert.variant = "danger";
              let err = "";
              for (const property in data.data) {
                err += data.data[property] + "\n\n";
              }
              console.log(err);
              this.alert.msg = `
                            ${err}`;
              this.alert.dismissCountDown = 5;
            } else {
              this.alert.variant = "success";
              this.alert.msg = "demande ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchDemandeMateriels();
          })
          .catch((err) => console.log(err));
      } else {
        this.$http.put("http://localhost:8000/api/dm/" + demande.id, demande)
          .then(() => {
            this.fetchDemandeMateriels();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "demande modifié avec succès";
            this.alert.dismissCountDown = 5;
          })
          .catch((err) => console.log(err));
      }
    },
    updateDemande(demande) {
      this.edit = true;
      this.demande = demande;
    },
    searchEmploye(search) {
      this.search = search;
      this.fetchDemandeMateriels();
    },
    initModal() {
      this.demande = {};
      this.edit = false;
      this.showModal("demandeMaterielModal");
    },

    fetchCategories(page_url = "http://127.0.0.1:8000/api/c") {
      this.$http.get(page_url)
        .then((res) => res.data)
        .then((res) => {
          this.categories = res.data;
        })
        .catch((err) => console.log(err));
    },
    attachMateriel(idMateriel, idDemande) {
      this.$http
        .get("http://localhost:8000/sanctum/csrf-cookie", {
          withCredentials: true
        })
        .then(() => { })
        .catch(() => { }).then(() => {

          this.$http.post("http://127.0.0.1:8000/api/m/" + idMateriel + "/" + idDemande, null)
            .then((res) => res.data)
            .then((data) => {
              if (data.attached == true) {
                this.alert.variant = "success";
                this.alert.msg = "Materiel attachée avec succès";
                this.alert.dismissCountDown = 5;
              } else {
                this.alert.variant = "danger";
                this.alert.msg = "Materiel détachée avec succès";
                this.alert.dismissCountDown = 5;
              }
      this.fetchDemandeMateriels();

            })
            .catch((err) => console.log(err));
        });
    },
    deleteMateriel(idMateriel, idDemande) {
      this.$http.delete("http://127.0.0.1:8000/api/m/" + idMateriel + "/" + idDemande)
        .then((res) => res.data)
        .then((data) => {
          this.fetchDemandeMateriels();
          if (data.attached == true) {
            this.alert.variant = "success";
            this.alert.msg = "Materiel attachée avec succès";
            this.alert.dismissCountDown = 5;
          } else {
            this.alert.variant = "danger";
            this.alert.msg = "Materiel détachée avec succès";
            this.alert.dismissCountDown = 5;
          }
        })
        .catch((err) => console.log(err));
    },
    setQuantity(idMateriel, idDemande, quantite){
      this.$http.put("http://127.0.0.1:8000/api/m/Q/" + idMateriel + "/" + idDemande, { quantité: quantite })
        .then((res) => res.data)
        .then(() => {
            this.alert.variant = "success";
            this.alert.msg = "quantité modifier avec succès";
            this.alert.dismissCountDown = 5;
          }
        )
        .catch((err) => console.log(err));
    }
  },
};
</script>

<style>
.btun {
  color: white !important;
  background-color: #d32f2f !important;
}
</style>
