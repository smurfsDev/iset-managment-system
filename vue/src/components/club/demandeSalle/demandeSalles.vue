<template>
  <div>
    <ajouterDemandeSalle
      @addDemande="addDemande"
      :oldDemande="demande"
    />
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
          <div
              class="card card-body my-5 py-5 text-center"
              v-if="demandes.length == 0"
            >
              <h3>il y'a aucune demande</h3>
            </div>
            <b-card class="my-2" v-for="demande in demandes" :key="demande.id">
              <md-tabs>
                <md-tab id="tab-home" md-label="demande">
                  <div class="bv-example-row text-center">
                    <b-row class="mb-2">
                      <b-row>
                        <b-col
                          >Date création : {{ demande.created_at }}</b-col
                        >
                      </b-row>
                    <b-row>
                      <b-col> salle : {{ demande.salles.titre }}</b-col>
                    </b-row>
                      <b-row>
                        <b-col
                          >Date d'emploi : {{ demande.dateEmploi }} Date de remise :
                          {{ demande.dateDeRemise }}</b-col
                        >
                      </b-row>
                    </b-row>
                    <b-button variant="danger" v-on:click="Delete(demande.id)"
                      >Delete</b-button
                    >
                    <b-button variant="warning" v-on:click="Update(demande)"
                      >Update</b-button
                    >
                  </div>
                </md-tab>
              </md-tabs>
            </b-card>
            <nav class="row" v-if="demandes.length != 0">
              <ul class="pagination w-auto mx-auto">
                <li
                  :class="[{ disabled: !pagination.prev_page_url }]"
                  class="page-item"
                >
                  <a
                    @click="fetchDemande(pagination.prev_page_url)"
                    class="btn btun page-link"
                    :class="[
                      !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
                    ]"
                    >Precedent</a
                  >
                </li>
                <li class="page-item">
                  <a class="page-link text-dark" href="#">{{
                    pagination.current_page + "/" + pagination.last_page
                  }}</a>
                </li>
                <li
                  :class="[{ disabled: !pagination.next_page_url }]"
                  class="page-item"
                >
                  <a
                    @click="fetchDemande(pagination.next_page_url)"
                    :class="[
                      !pagination.next_page_url ? 'disabled' : 'link-primary btun',
                    ]"
                    class="btun btn page-link"
                    >Suivant</a
                  >
                </li>
              </ul>
            </nav>
          </div>
                  <!-- </b-card> -->
                </div>
              </div>
            </div>
    
</template>

<script>
import ajouterDemandeSalle from "./ajouterDemandeSalle.vue";

export default {
  components: {
    ajouterDemandeSalle,
  },
  data() {
    return {
      // Salles: [],
      demandes: [],
      pagination: {},
      demande: {},
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
       edit: false,
      search: "",
      show: true,
    };
  },
  created() {
    // console.log(typeof $);
    this.fetchDemandeSalle();
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
    
    initModal() {
      this.demande = {};
      this.showModal("demandeSalleModal");
    },
     makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/DemandeSalle?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    fetchDemandeSalle() {
      let vm = this;
      fetch("http://127.0.0.1:8000/api/DemandeSalle", {
        method: "GET",
      })
        .then((res) => res.json())
        .then((res) => {
          this.demandes = res.data.data;
          vm.makePagination(res.data);
          this.show = false;

        })
        .catch((err) => console.log(err));
    },
    Delete(id){
      let headersi = new Headers();
      headersi.append("auth", 5);
      if (confirm("Delete demande " + id)) {
        this.show = true;
        fetch("http://localhost:8000/api/DemandeSalle/" + id, {
          method: "delete",
          headers: headersi,
        })
          .then(() => {
            this.fetchDemandeSalle();
            this.alert.variant = "danger";
            this.alert.msg = "Demande suprimée avec succès";
            this.alert.dismissCountDown = 5;
          })
          .then(() => {})
          .catch((err) => console.log(err));
      }
    },
    addDemande(demande) {
      let headersi = new Headers();
      headersi.append("auth", 5);
      headersi.append("Content-Type", "application/json");
      this.show = true;
      if (!this.edit) {
        fetch("http://localhost:8000/api/DemandeSalle/", {
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
              this.alert.msg = "demande ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchDemandeSalle();
          })
          .catch((err) => console.log(err));
      } else {
        fetch("http://localhost:8000/api/DemandeSalle/" + demande.id, {
          method: "put",
          body: JSON.stringify(demande),
          headers: headersi,
        })
          .then((res) => res.json())
          .then(() => {
            this.fetchDemandeSalle();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "demande modifié avec succès";
            this.alert.dismissCountDown = 5;
          })
          .catch((err) => console.log(err));
      }
    },
    Update(demande) {
      this.edit = true;
      this.demande = demande;
      this.showModal("demandeSalleModal");
    },
    
  },
};
</script>

<style></style>
