<template>
  <div
    class="modal fade"
    id="DocumentModal"
    tabindex="-1"
    aria-labelledby="DocumentModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="DocumentModalLabel">Créer une Document</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('DocumentModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addDocument">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Nom documnet:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="nom"
                v-model="oldDocument.nom"
                required="required"
              />
              <label>class:</label>
              <!-- <div class="form-group">
                <select name="Categorie" v-model="oldDocument.Categorie">
                  <option
                    v-for="categorie in categories"
                    :key="categorie.id"
                    :value="categorie.id"
                  >
                    {{ categorie.titre }}
                  </option>
                </select>
              </div> -->
             <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="class"
                v-model="oldDocument.class"
                required="required"
              />

              <label>categorie:</label>
              <!-- <div class="form-group">
                <select name="idCategorie" v-model="oldDocument.idCategorie">
                  <option
                    v-for="categorie in categories"
                    :key="categorie.id"
                    :value="categorie.id"
                  >
                    {{ categorie.titre }}
                  </option>
                </select>
              </div>
               -->
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="categorie"
                v-model="oldDocument.categorie"
                required="required"
              />

              <label>Fichier:</label>
              <input
                type="file"
                class="form-control"
                name="backgroundImage"
                @change="convert64"
                ref="file"
                required
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('DocumentModal')"
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
    oldDocument: Object,
    edit: Boolean,
  },
  emits: ["addDocument"],
  mounted() {},
  methods: {
    addDocument() {
      this.$emit("addDocument", this.oldDocument);
      this.resetModal1();
      this.hideModal("DocumentModal");
    },
    resetModal1() {
      $(".dcc").val("");
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldDocument.file = reader.result;
      };
      reader.readAsDataURL(file);
    },
  },
};
</script>

<style></style>
