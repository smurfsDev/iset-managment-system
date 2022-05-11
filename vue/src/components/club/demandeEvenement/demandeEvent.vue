<template>
  <div class="w-100">
    <h1>Demande d'événement</h1>
    <ajouterDemandeEvent @addDemande="addDemande" :oldDemande="demande" />
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
          <showDemande :demandes="demandes" :pagination="pagination" @fetchDemande="fetchDemandeSalle"
            @updateDemande="Update" @deleteDemande="Delete" />
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import ajouterDemandeEvent from "./ajouterDemandeEvent.vue";
import showDemande from "./showDemande.vue";
export default {
  components: {
    ajouterDemandeEvent,
    showDemande
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
    this.fetchDemandeEvent();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Evenement ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Evenement modifié avec succès";
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
      this.showModal("demandeEventModal");
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/club/demandeEvent?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    fetchDemandeEvent(url = "http://localhost:8000/api/club/demandeEvent/getAll") {
      let vm = this;
      this.$http.get(url)
      .then((res)=> {
        this.demandes = res.data.data.data;
        this.show = false;
        vm.makePagination(res.data.data);
      });
    },
    Delete(id) {
      if (confirm("Delete demande " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/club/demandeEvent/delete/" + id)
          .then(() => {
            this.fetchDemandeEvent();
            this.alert.variant = "danger";
            this.alert.msg = "Demande suprimée avec succès";
            this.alert.dismissCountDown = 5;
          });
      }
    },
    addDemande(demande) {
      this.show = true;
      if (!this.edit) {
        this.$http.post("http://localhost:8000/api/club/demandeEvent/create",
        (demande))
        .then((data) => {
          data = data.data;
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
              this.alert.msg = "Demande ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchDemandeEvent();
        });
      } else {
        this.$http.put("http://localhost:8000/api/club/demandeEvent/update/" + demande.id,
        (demande))
        .then(() => {
          this.fetchDemandeEvent();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "demande modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    Update(demande) {
      this.edit = true;
      this.demande = demande;
      this.showModal("demandeEventModal");
    },

  },
};
</script>

<style>
</style>
