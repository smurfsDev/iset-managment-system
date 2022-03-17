<template>
  <div>
    <formDemande @addDemande="addDemande" :oldDemande="demande" />
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay
          v-if="show"
          :show="show"
          class="d-inline-block"
          style="height: 500px; width: 100%"
        ></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
              <b-col cols="8">
                <button
                  type="button"
                  class="btn btn-primary mx-1 float-start"
                  data-bs-toggle="modal"
                  @click="initModal()"
                  data-bs-target="#demandeModal"
                >
                  Nouvelle demande
                </button>
              </b-col>
              <b-col></b-col>
            </b-row>
          </b-container>
          <b-alert
            class="mt-4"
            :show="alert.dismissCountDown"
            dismissible
            :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0"
          >
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <showDemandes
            @deleteDemande="deleteDemande"
            :demandes="DemandeCreationClub"
            @fetchDemande="fetchDemandeCreationClub"
            @updateDemande="updateDemande"
            :pagination="pagination"
          />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showDemandes from "./show.vue";
import formDemande from "./form.vue";
// import search from '../search.vue';

export default {
  components: {
    showDemandes,
    formDemande,
    //     search
  },
  data() {
    return {
      DemandeCreationClub: [],
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
    };
  },
  created() {
    // console.log(typeof $);
    document.title = "Demande";
    this.fetchDemandeCreationClub();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Employé ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Employé modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchDemandeCreationClub(
      page_url = "http://127.0.0.1:8000/api/dcc/" + this.myid
    ) {
      let vm = this;
      // let headersi = new Headers();
      // headersi.append('auth', 5);
      fetch(page_url, {
        method: "GET",
      })
        .then((res) => res.json())
        .then((res) => {
          if (res.constructor !== Array) {
            this.DemandeCreationClub = res.data;
          } else {
            this.DemandeCreationClub = [];
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
          "http://localhost:8000/api/dcc?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteDemande(id) {
      let headersi = new Headers();
      headersi.append("auth", 5);
      if (confirm("Delete document " + id)) {
        this.show = true;
        fetch("http://localhost:8000/api/dcc/" + id, {
          method: "delete",
          headers: headersi,
        })
          .then(() => {
            this.fetchDemandeCreationClub();
            this.alert.variant = "danger";
            this.alert.msg = "Demande suprimée avec succès";
            this.alert.dismissCountDown = 5;
          })
          .then(() => {})
          .catch((err) => console.log(err));
      }
    },
    resetModal1() {
      this.document = {};
    },
    addDemande(demande) {
      let headersi = new Headers();
      headersi.append("auth", 5);
      headersi.append("Content-Type", "application/json");
      demande.responsableClubId = this.myid;
      this.show = true;
      console.log(demande.id);
      if (!this.edit) {
        fetch("http://localhost:8000/api/dcc/", {
          method: "post",
          body: JSON.stringify(demande),
          headers: headersi,
        })
          .then((res) => res.json())
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
              this.alert.msg = "Employé ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchDemandeCreationClub();
          })
          .catch((err) => console.log(err));
      } else {
        fetch("http://localhost:8000/api/dcc/" + demande.id, {
          method: "put",
          body: JSON.stringify(demande),
          headers: headersi,
        })
          .then((res) => res.json())
          .then(() => {
            this.fetchDemandeCreationClub();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Employé modifié avec succès";
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
      this.fetchDemandeCreationClub();
    },
    initModal() {
      this.demande = {};
      this.showModal("demandeModal");
    },
  },
};
</script>

<style>
.btun {
  color: white !important;
  background-color: #d32f2f !important;
}
</style>
