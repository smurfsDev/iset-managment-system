<template>
  <div
    class="modal fade"
    id="demandeMaterielModal"
    tabindex="-1"
    aria-labelledby="demandeMaterielModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="demandeModalLabel">Créer une demande</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('demandeMaterielModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addDemande">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Id Responsable club :</label>
              <input
                type="integer"
                class="border-0 dcc form-control"
                placeholder="(1)"
                v-model="oldDemande.idResponsableClub"
                required="required"
              />
              <label>Id destinateur :</label>
              <input
                type="integer"
                class="border-0 dcc form-control"
                placeholder="(1)"
                v-model="oldDemande.idDestinataire"
                required="required"
              />
              <label>Date Emploi :</label>
              <input
                type="date"
                class="border-0 dcc form-control"
                placeholder
                v-model="oldDemande.dateEmploi"
                required="required"
              />
              <label>Date de Remise :</label>
              <input
                type="date"
                class="border-0 dcc form-control"
                placeholder
                v-model="oldDemande.dateDeRemise"
                required="required"
               />
                 <label>Materiels :</label>

                <div 
                class="form-check"
                v-for="materiel in Materiels"
                :key="materiel.id">
                    <input class="form-check-input" type="checkbox" :value="materiel.titre" :id="materiel.id">
                    <label class="form-check-label">
                        {{materiel.titre}}
                    </label>
                </div>

            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('demandeMaterielModal')"
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
  props: {
    Materiels: Array,
    oldDemande: Object,
    edit: Boolean,
  },
  emits: ["addDemande"],
  mounted() {},
  methods: { 
    addDemande() {
      this.$emit("addDemande", this.oldDemande);
      this.resetModal1();
      this.hideModal("demandeMaterielModal");
    },
    resetModal1() {
      $(".dm").val("");
    },
  },
};
</script>

<style></style>
