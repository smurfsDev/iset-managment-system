<template>
  <div
    class="modal fade"
    id="ClasseModal"
    tabindex="-1"
    aria-labelledby="ClasseModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="ClasseModalLabel">Ajouter une matiere</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('ClasseModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addMatiere">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Nom du matiere:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Nom matiere"
                v-model="oldMatiere.nom"
                required="required"
              />
              
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('ClasseModal')"
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
    oldMatiere: Object,
    edit: Boolean,
  },
  emits: ["addMatiere"],
  mounted() {},
  methods: {
    addMatiere() {
      this.$emit("addMatiere", this.oldMatiere);
      this.resetModal1();
      this.hideModal("ClasseModal");
    },
    resetModal1() {
      $(".dcc").val("");
    }
  },
};
</script>

<style></style>
