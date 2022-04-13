<template>
  <div
    class="modal fade"
    id="demandeModal"
    tabindex="-1"
    aria-labelledby="demandeModalLabel"
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
            @click="hideModal('demandeModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addDemande">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Nom du club:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Nom club"
                v-model="oldDemande.nomClub"
                required="required"
              />
              <label>Logo:</label>
              <input
                type="file"
                class="form-control"
                name="backgroundImage"
                @change="convert64"
                ref="file"
                required
              />
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Logo"
                v-model="oldDemande.logo"
                required="required"
              />
              <label>Date création</label>
              <input
                type="date"
                class="border-0 dcc form-control"
                placeholder
                v-model="oldDemande.dateCreation"
                required="required"
              />
              <label>Activité:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="activite"
                v-model="oldDemande.activite"
                required="required"
              />
              <label>President:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="president"
                v-model="oldDemande.president"
                required="required"
              />
              <label>Vice president:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Vice president"
                v-model="oldDemande.vicePresident"
                required="required"
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('demandeModal')"
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
    oldDemande: Object,
    edit: Boolean,
  },
  emits: ["addDemande"],
  mounted() {},
  methods: {
    addDemande() {
      this.$emit("addDemande", this.oldDemande);
      this.resetModal1();
      this.hideModal("demandeModal");
    },
    resetModal1() {
      $(".dcc").val("");
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
       
        this.oldDemande.logo = reader.result;
      };
      reader.readAsDataURL(file);
 
    },
  },
};
</script>

<style></style>
