<template>
  <div
    class="modal fade"
    id="formDocumentSend"
    tabindex="-1"
    aria-labelledby="formDocumentSendLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="formDocumentSendLabel">
            Repondre document {{ idDoc }}
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('formDocumentSend')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addDemandeDocument">
          <div class="modal-body">
            <div class="form-group mb-2">
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
              @click="hideModal('formDocumentSend')"
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
    props: ["idDoc"],
    data() {
        return {
            myDoc: null,
            file64: ""
        };
    },
  methods: {
    addDemandeDocument() {
      this.$http
        .put("http://localhost:8000/api/DemandeDocument/reponse/"+this.idDoc, {
          file: this.myDoc
        })
        .then(() => {
          this.hideModal("formDocumentSend");
          this.$emit("fetchThem");
        });
    },

    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.myDoc = reader.result;
      };
      reader.readAsDataURL(file);
    },
    resetModal1() {
      $(".dcc").val("");
    },
    fetchCategories() {
      this.$http
        .get("http://localhost:8000/api/Categorie/all")
        .then((response) => {
          this.categories = response.data;
        });
    },
  },
};
</script>

<style></style>
