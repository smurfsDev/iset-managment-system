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
          <h5 class="modal-title" id="DocumentModalLabel">
            Créer une Document
          </h5>
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
                placeholder="Title"
                v-model="oldDocument.nom"
                required="required"
              />

              <label>Class:</label>
              <div class="form-group">
                <select name="classe" v-model="oldDocument.classe">
                  <option
                    v-for="classe in classes"
                    :key="classe.id"
                    :value="classe.id"
                  >
                    {{ classe.abreviation }}
                  </option>
                </select>
              </div>

              <label>Categorie:</label>
              <div class="form-group">
                <select
                  name="document_categories"
                  v-model="oldDocument.categorie"
                >
                  <option
                    v-for="categorie in categories"
                    :key="categorie.id"
                    :value="categorie.id"
                  >
                    {{ categorie.title }}
                  </option>
                </select>
              </div>

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

  mounted() {
    this.fetchCategories();
    this.fetchClasses();
  },

  data() {
    return {
      categories: [],
      classes: [],
    };
  },

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

    fetchCategories() {
      let page_url = "http://127.0.0.1:8000/api/Categorie/all";
      this.$http
        .get(page_url)
        .then((res) => {
          this.categories = res.data;
        })
        .finally(() => {
          this.show = false;
        });
    },

    fetchClasses() {
      let page_url = "http://127.0.0.1:8000/api/Classe";
      this.$http
        .get(page_url)
        .then((res) => {
          this.classes = res.data.data;
        })
        .finally(() => {
          this.show = false;
        });
    },
  },
};
</script>

<style></style>
