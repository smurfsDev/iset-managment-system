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
          <h5 class="modal-title" id="ClasseModalLabel">Créer une Classe</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('ClasseModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addClasse">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Nom du classe:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Nom club"
                v-model="oldClasse.nom"
                required="required"
              />
              <label>Abbreveation:</label>
              <input
                ttype="text"
                class="border-0 dcc form-control"
                placeholder="Nom club"
                v-model="oldClasse.abreviation"
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
    oldClasse: Object,
    edit: Boolean,
  },
  emits: ["addClasse"],
  mounted() {},
  methods: {
    addClasse() {
      this.$emit("addClasse", this.oldClasse);
      this.resetModal1();
      this.hideModal("ClasseModal");
    },
    resetModal1() {
      $(".dcc").val("");
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldClasse.logo = reader.result;
      };
      reader.readAsDataURL(file);
    },
  },
};
</script>

<style></style>
