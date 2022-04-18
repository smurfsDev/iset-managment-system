<template>
  <div>
    <formDemande @addDemande="addDemande" :oldDemande="demande" />
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
          <showDemandes @deleteDemande="deleteDemande" :demandes="DemandeCreationClub"
            @fetchDemande="fetchDemandeCreationClub" @updateDemande="updateDemande" :pagination="pagination" />
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
      page_url = "http://127.0.0.1:8000/api/dcc/"
    ) {
      let vm = this;
      this.$http.get(page_url, {
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
      },
      ).then((res)=> {
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
      if (confirm("Delete document " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/dcc/" + id, {
          headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
          }
        },
        ).then(() => {
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
    addDemande(demande) {
      
      this.show = true;
      console.log(demande.id);
      if (!this.edit) {
       this.$http.post('http://localhost:8000/api/dcc',
        (demande), {
          headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
          }
        },
        ).then((data) => {
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
              this.alert.msg = "Employé ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchDemandeCreationClub();
        });
      } else {
        this.$http.put("http://localhost:8000/api/dcc/" + demande.id,
        (demande), {
          headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
          }
        },
        ).then(() => {
          this.fetchDemandeCreationClub();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Employé modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
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
