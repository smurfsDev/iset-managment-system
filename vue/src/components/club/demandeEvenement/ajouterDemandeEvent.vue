<template>
  <div
    class="modal fade"
    id="demandeEventModal"
    tabindex="-1"
    aria-labelledby="demandeEventModalLabel"
    aria-hidden="true"
  >
  <h1>Ajouter une demande d'événement</h1>
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="demandeEventModal">Créer une demande</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('demandeEventModal')"
          ></button>
        </div>
          <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
                @dismissed="alert.dismissCountDown = 0">
                <p>{{ alert.msg }}</p>
          </b-alert>
        <form class="mb-3" @submit.prevent="addDemande(oldDemande)">
          <div class="modal-body">
            <div class="form-group mb-2">
                
              
             <label>Nom de l'évenement :</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Nom de l'évenement"
                v-model="oldDemande.nomEvent"
                required="required"
              />
              <label>Date Emploi :</label>
              <input
                type="date"
                class="border-0 dcc form-control"
                placeholder
                v-model="oldDemande.dateEvent"
                required="required"
              />
               <label>Description l'évenement :</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Description de l'évenement"
                v-model="oldDemande.description"
                required="required"
              />
              
              
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('demandeEventModal')"
            >
              Fermer
            </button>
            <button type="submit" class="btn btn-primary">Sauvegarder</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data() {
    return {
       
    }
    },
  props: {
    oldDemande: Object,
    edit: Boolean,
  },
  data() {
    return {
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      err: null,
    };
  },
  emits: ["fetchDemandeEvent"],
  mounted() {},
  created() {
      
  },
  methods: {
     
    // addDemande() {
    //   this.$emit("addDemande", this.oldDemande);
    //   this.resetModal1();
    //   this.hideModal("demandeEventModal");
    // },
     fetchDemandeEvent() {
      this.$emit(
        "fetchDemandeEvent",
        "http://localhost:8000/api/demandeEvent/getAll",
        this.alert
      )
      console.log(this.alert)
      },
    addDemande(demande) {
      this.show = true;
      console.log(demande)
      if (!this.edit) {
        this.$http.post("http://localhost:8000/api/demandeEvent/create",
        (demande))
        .then((data) => {
          data = data.data;
          console.log(data)
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
               this.hideModal("demandeEventModal");
            }
            this.fetchDemandeEvent();
        });
      } else {
        this.$http.put("http://localhost:8000/api/demandeEvent/update/" + demande.id,
        (demande))
        .then((data) => {
          // this.fetchDemandeEvent();
          //   this.edit = false;
          //   this.alert.variant = "warning";
          //   this.alert.msg = "demande modifié avec succès";
          //   this.alert.dismissCountDown = 5;
          data = data.data;
          console.log(data)
          
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
              this.alert.variant = "warning";
              this.alert.msg = "Demande modifiée avec succès";
              this.alert.dismissCountDown = 5;
               this.hideModal("demandeEventModal");
            }
            this.fetchDemandeEvent();
        });
      }
    },
    resetModal1() {
      $(".demandeEvent").val("");
    },
  },
};
</script>

<style></style>
