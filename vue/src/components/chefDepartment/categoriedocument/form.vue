<template>
  <div
    class="modal fade"
    id="CategorieModal"
    tabindex="-1"
    aria-labelledby="CategorieModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="CategorieModalLabel">Créer une CATEGORIE</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('CategorieModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addCategorie">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>CATEGORIE TITLE:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="title"
                v-model="oldCategorie.title"
                required="required"
              />
              </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('CategorieModal')"
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
    oldCategorie: Object,
    edit: Boolean,
  },
  emits: ["addCategorie"],
  mounted() {},
  methods: {
    addCategorie() {
      this.$emit("addCategorie", this.oldCategorie);
      this.resetModal1();
      this.hideModal("CategorieModal");
    },
    resetModal1() {
      $(".dcc").val("");
    },

  },
};
</script>

<style></style>
