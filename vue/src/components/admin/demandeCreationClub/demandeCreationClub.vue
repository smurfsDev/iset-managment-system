<template>
  <div>
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay
          v-if="show"
          :show="show"
          class="d-inline-block"
          style="height: 500px; width: 100%"
        ></b-overlay>
        <div class="pt-4 mt-4" v-if="!show">
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
            @acceptDemande="acceptDemande"
            :demandes="DemandeCreationClub"
            @fetchDemande="fetchDemandeCreationClub"
            @declineDemande="declineDemande"
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

export default {
  components: {
    showDemandes,
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
    document.title = "Demande";
    this.fetchDemandeCreationClub();
  },
  methods: {
    fetchDemandeCreationClub(page_url = "http://127.0.0.1:8000/api/dcc/") {
      let vm = this;
      this.$http.get(page_url).then((res) => {
        this.DemandeCreationClub = res.data.data;
        this.show = false;
        vm.makePagination(res.data);
      });
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
      if (confirm("Modifier document " + id)) {
        this.show = true;
        this.$http
          .delete("http://localhost:8000/api/dcc/" + id, {})
          .then(() => {
            this.fetchDemandeCreationClub();
            this.alert.variant = "danger";
            this.alert.msg = "Demande suprimée avec succès";
            this.alert.dismissCountDown = 5;
          });
      }
    },
    resetModal1() {
      this.document = {};
    },
    acceptDemande(id) {
      this.$http
        .put("http://localhost:8000/api/dcc/a/" + id)
        .then(() => {
          this.fetchDemandeCreationClub();
          this.edit = false;
          this.alert.variant = "warning";
          this.alert.msg = "Demande acceptée avec succès";
          this.alert.dismissCountDown = 5;
        })
        .catch((error) => {
          this.fetchDemandeCreationClub();
          this.alert.variant = "danger";
          this.alert.msg = error.response.data.data.error;
          this.alert.dismissCountDown = 5;
        });
    },
    declineDemande(id) {
      this.$http.put("http://localhost:8000/api/dcc/d/" + id).then(() => {
        this.fetchDemandeCreationClub();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "Demande non acceptée !";
        this.alert.dismissCountDown = 5;
      });
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
