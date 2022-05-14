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

        <form class="mb-3" @submit.prevent="addDemande">
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
  emits: ["addDemande"],
  mounted() {},
  created() {
      
  },
  methods: {
     
    addDemande() {
      this.$emit("addDemande", this.oldDemande);
      this.resetModal1();
      this.hideModal("demandeEventModal");
    },
    resetModal1() {
      $(".demandeEvent").val("");
    },
  },
};
</script>

<style></style>
