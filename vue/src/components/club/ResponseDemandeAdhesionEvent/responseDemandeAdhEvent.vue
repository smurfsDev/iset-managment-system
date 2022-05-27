<template>
  <div>
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div class="pt-4 mt-4" v-if="!show">
          <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <showDemandes @acceptDemande="acceptDemande" :demandes="DemandeEvent"
            @fetchDemande="fetchDemandeEvent" @declineDemande="declineDemande" :pagination="pagination" />
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
      DemandeEvent: [],
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
    this.fetchDemandeEvent();
    
  },
  methods: {
    fetchDemandeEvent(page_url = "http://localhost:8000/api/demandeAdhesionEvent/parClub") {
      let vm = this;
      this.$http.get(page_url)
      .then((res) => {
        // console.log(res.data.data.data);
        this.DemandeEvent = res.data.data.data;
        for (let index = 0; index < this.DemandeEvent.length; index++) {
         // console.log(this.DemandeEvent[index].demande_adhesion_event);
          for (let i = 0; i < this.DemandeEvent[index].demande_adhesion_event.length; i++) {
           console.log(this.DemandeEvent[index].demande_adhesion_event[i].student)
            
          }
          
        }
        this.show = false;
        //console.log(this.DemandeEvent)
     //  console.log(this.demandes.demande_adhesion_event)
        vm.makePagination(res.data.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/demandeAdhesionEvent/parClub?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteDemande(id) {
      if (confirm("Delete demande évenement " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/demandeAdhesionEvent/delete/" + id, {
        },
        ).then(() => {
          this.fetchDemandeEvent();
          this.alert.variant = "danger";
          this.alert.msg = "Demande adhésion évenement suprimée !";
          this.alert.dismissCountDown = 5;
        });
      }
    },
    resetModal1() {
      this.document = {};
    },
    acceptDemande(id) {
      this.$http.put("http://localhost:8000/api/demandeAdhesionEvent/accept/" + id)
      .then(() => {
        this.fetchDemandeEvent();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "Demande adhésion évenement acceptée avec succès";
        this.alert.dismissCountDown = 5;
      });
    },
    declineDemande(id) {
      this.$http.put("http://localhost:8000/api/demandeAdhesionEvent/decline/" + id)
      .then(() => {
        this.fetchDemandeEvent();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "Demande adhésion évenement non acceptée !";
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
