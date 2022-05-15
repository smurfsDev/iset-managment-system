<template>
  <div>
      <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
          </b-alert>
    <div class="card card-body my-5 py-5 text-center" v-if="demandes.length == 0">
      <h3>il y'a aucune demande</h3>
    </div>
    <b-card class="my-2" v-for="demande in demandes" :key="demande.id">
        <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'success',
          title: 'Student accepted',
          placement: 'topright',
        }"
        v-if="demande.status == 1"
        icon="check-square"
        scale="2"
        variant="success"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'danger',
          title: 'Student not accepted',
          placement: 'topright',
        }"
        v-if="demande.status == 2"
        icon="x-square"
        scale="2"
        variant="danger"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'warning',
          title: 'Student pending',
          placement: 'topright',
        }"
        v-if="demande.status == 0"
        icon="x-square"
        scale="2"
        variant="warning"
      ></b-icon>
      <md-tabs>
        <md-tab id="tab-home" md-label="demande">
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>Nom du Responsable : {{ demande.responsable_club.name }} Date
                  création : {{ demande.created_at }}</b-col>
              </b-row>

              <b-row>
                <b-col>Date d'emploi : {{ demande.dateEmploi }} Date de remise :
                  {{ demande.dateDeRemise }}</b-col>
              </b-row>
            </b-row>
            <b-row>
                <div class="col-md-4 row">
                    <strong>Materiels :</strong>
                </div>
                        
                <b-row v-for="mat in demande.materiel" :key="mat.id">
                        <b-col> <p class="text-center">
                                <strong>{{ mat.titre }}</strong>
                                 De quantité :
                                    <strong>{{ mat.pivot.quantité }}</strong>
                                </p>
                  </b-col>
                </b-row>
                
            </b-row>
                <b-row >
                    <div role="group" class="row" style="align-items: center">
                        <div class="col-md-4 row">
                                        <strong>Reponse :</strong>
                        </div>
                        <div class="col-md-5">
                            <input type="text" v-model="demande.reponse" class="form-control" name="reponse" style="
                                background-color: rgb(236, 239, 241);
                                border: 0px !important;
                                " />
                        </div>
                        <div class="col-md-3">
                            <div class="input-group-append">
                                <b-button type="button" variant="success" :class="[{ disabled: demande.status == 2 }]" @click="setReponse(demande.id,demande.reponse)">Add Reponse</b-button>
                            </div>
                        </div>
                    </div>
                </b-row>            
            <b-button variant="success" :class="[{ disabled: demande.status == 1 }]" v-on:click="Approve(demande.id)">Approve</b-button>
            <b-button variant="danger" :class="[{ disabled: demande.status == 2 }]" v-on:click="Desapprove(demande.id)">Desapprove</b-button>
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="demandes.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li :class="[{ disabled: !pagination.prev_page_url }]" class="page-item">
          <a @click="fetchDemande(pagination.prev_page_url)" class="btn btun page-link" :class="[
            !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
          ]">Precedent</a>
        </li>
        <li class="page-item">
          <a class="page-link text-dark" href="#">{{
              pagination.current_page + "/" + pagination.last_page
          }}</a>
        </li>
        <li :class="[{ disabled: !pagination.next_page_url }]" class="page-item">
          <a @click="fetchDemande(pagination.next_page_url)" :class="[
            !pagination.next_page_url ? 'disabled' : 'link-primary btun',
          ]" class="btun btn page-link">Suivant</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>

export default {
  data() {
    return {
        demandes: [],
        pagination: {},
        show: true,
        alert: {
            dismissCountDown: 0,
            variant: "",
            msg: "",
        },

    };
  },
  created() {
      this.fetchDemandeMateriels();
  },

  methods: {
   fetchDemandeMateriels(
      page_url = "http://127.0.0.1:8000/api/dm/MesDemandes"
    ) {
        let vm = this;
        this.$http.get(page_url)
        .then((res) => res.data)
        .then((res) => {
            this.demandes = res.data;
            this.show = false;
            vm.makePagination(res);
        })
        .catch((err) => console.log(err));
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/dm/MesDemandes?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    Approve(id) {
      this.$http.post("http://127.0.0.1:8000/api/dm/accept/" + id)
        .then((res) => res.data)
        .then((res) => {
            this.fetchDemandeMateriels();
            this.alert.variant = "warning";
            this.alert.msg = "Demande acceptée avec succès";
            this.alert.dismissCountDown = 5;
        })
        .catch((err) => console.log(err));
    },
    Desapprove(id) {
      this.$http.post("http://127.0.0.1:8000/api/dm/refuse/" + id)
        .then((res) => res.data)
        .then((res) => {
          this.fetchDemandeMateriels();
            this.alert.variant = "warning";
            this.alert.msg = "Demande refusée avec succès";
            this.alert.dismissCountDown = 5;
        })
        .catch((err) => console.log(err));
    },
    setReponse(id,reponse) {
      this.$http.put("http://127.0.0.1:8000/api/dm/reponse/" + id,{reponse:reponse})
        .then((res) => res.data)
        .then((res) => {
          this.fetchDemandeMateriels();
            this.alert.variant = "warning";
            this.alert.msg = "Reponse envoyée avec succès";
            this.alert.dismissCountDown = 5;
        })
        .catch((err) => console.log(err));
    },
  },
}
</script>

<style>
</style>
