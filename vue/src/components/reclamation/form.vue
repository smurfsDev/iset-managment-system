<template>
  <div
    class="modal fade"
    id="ReclamationModal"
    tabindex="-1"
    aria-labelledby="ReclamationModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="ReclamationModalLabel">Faire une Reclamation</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('ReclamationModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addReclamation">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Titre:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="nom"
                v-model="oldReclamation.titre"
                required="required"
              />
              <label>Description:</label>
              <input
             type="text"
              class="border-0 dcc form-control"
              placeholder="class"
              v-model="oldReclamation.description"
              required="required"
             />

              <label>Type:</label>
               <!--  <div class="form-group">
                <select name="idCategorie" v-model="oldDemande.idCategorie">
                  <option
                    v-for="categorie in categories"
                    :key="categorie.id"
                    :value="categorie.id"
                  >
                    {{ categorie.titre }}
                  </option>
                </select>
              </div>-->
              
            <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="type"
                v-model="oldReclamation.type"
                required="required"
              />

            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('ReclamationModal')"
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
    oldReclamation: Object,
    edit: Boolean,
  },
  emits: ["addReclamation"],
  mounted() {},
  methods: {
    addReclamation() {
      this.$emit("addReclamation", this.oldReclamation);
      this.resetModal1();
      this.hideModal("ReclamationModal");
    },
    resetModal1() {
      $(".dcc").val("");
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldReclamation.file = reader.result;
      };
      reader.readAsDataURL(file);
    },
  },
};
</script>

<style></style>
