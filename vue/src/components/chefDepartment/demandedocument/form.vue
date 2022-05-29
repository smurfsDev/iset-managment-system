<template>
  <div
    class="modal fade"
    id="DemandeDocumentModal"
    tabindex="-1"
    aria-labelledby="DemandeDocumentModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="DemandeDocumentModalLabel">Faire une Demande Docuement</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('DemandeDocumentModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addDemandeDocument">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Titre:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="nom"
                v-model="oldDemandeDocument.titre"
                required="required"
              />
              <label>Description:</label>
              <input
             type="text"
              class="border-0 dcc form-control"
              placeholder="Description ..."
              v-model="oldDemandeDocument.description"
              required="required"
             />
             
              <label>Type:</label>
               <div class="form-group">
                <select name="document_categories_id" v-model="oldDemandeDocument.document_categories_id">
                  <option
                    v-for="categorie in categories"
                    :key="categorie.id"
                    :value="categorie.id"
                  >
                    {{ categorie.title }}

                  </option>
                </select>
              </div>  

            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('DemandeDocumentModal')"
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
    oldDemandeDocument: Object,
    edit: Boolean,
  },
  data() {
    return {
      categories: [],
    };
  },
  emits: ["addDemandeDocument"],
  mounted() {
    this.fetchCategories();
  },
  methods: {
    addDemandeDocument() {
      this.$emit("addDemandeDocument", this.oldDemandeDocument);
      this.resetModal1();
      this.hideModal("DemandeDocumentModal");
    },
    
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldDemandeDocument.file = reader.result;
      };
      reader.readAsDataURL(file);
    },
    resetModal1() {
      $(".dcc").val("");
    },
    fetchCategories() {
      this.$http.get("http://localhost:8000/api/Categorie/all").then((response) => {
        this.categories = response.data;
      });
    },

  },
};
</script>

<style></style>
