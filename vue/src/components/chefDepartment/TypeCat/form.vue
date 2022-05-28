<template>
  <div
    class="modal fade"
    id="TypecatModal"
    tabindex="-1"
    aria-labelledby="TypecatModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="TypecatModalLabel">Créer une Typecat</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('TypecatModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addTypecat">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Typecat TITLE:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="title"
                v-model="oldTypecat.title"
                required="required"
              />
              </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('TypecatModal')"
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
    oldTypecat: Object,
    edit: Boolean,
  },
  emits: ["addTypecat"],
  mounted() {},
  methods: {
    addTypecat() {
      this.$emit("addTypecat", this.oldTypecat);
      this.resetModal1();
      this.hideModal("TypecatModal");
    },
    resetModal1() {
      $(".dcc").val("");
    },

  },
};
</script>

<style></style>
