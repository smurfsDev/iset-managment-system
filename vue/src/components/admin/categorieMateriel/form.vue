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
          <h5 class="modal-title" id="CategorieModalLabel">Créer une Categorie</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('CategorieModal')"
          ></button>
        </div>
        <b-alert
          class="mt-4"
          :show="alert.dismissCountDown"
          dismissible
          :variant="alert.variant"
          @dismissed="alert.dismissCountDown = 0"
        >
          <p>{{ alert.msg }}</p>
        </b-alert>
        <form class="mb-3" @submit.prevent="addCategoriee">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Titre:</label>
              <input
                type="text"
                class="border-0 c form-control"
                placeholder="Titre"
                v-model="oldCategorie.titre"
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
  data() {
    return {
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      err: null,
    };
  },
  emits: ["fetchCategorie"],
  mounted() {},
  methods: {
    fetchCategorie() {
      this.$emit(
        "fetchCategorie",
        "http://127.0.0.1:8000/api/c/",
        this.alert
      );
    },
    resetModal1() {
      $(".c").val("");
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldCategorie.logo = reader.result;
      };
      reader.readAsDataURL(file);
    },
    addCategoriee() {
      this.show = true;
      if (!this.edit) {
        this.$http
          .post("http://localhost:8000/api/c", this.oldCategorie)
          .then((data) => {
            data = data.data;
            if (data.success == false) {
              this.alert.variant = "danger";
              let err = "";
              for (const property in data.data) {
                err += data.data[property] + "\n\n";
              }
              console.log(err);
              this.err = err;
              this.alert.msg = `
                            ${err}`;
              this.alert.dismissCountDown = 5;
            } else {
              this.err = "";
              this.alert.variant = "success";
              this.alert.msg = "Categorie ajouté avec succès";
              this.alert.dismissCountDown = 5;
              this.resetModal1();
              this.hideModal("CategorieModal");
            }
            this.fetchCategorie();
          });
      } else {
        this.$http
          .put(
            "http://localhost:8000/api/c/" + this.oldCategorie.id,
            this.oldCategorie
          )
          .then((data) => {
            data = data.data;
            if (data.success == false) {
              this.alert.variant = "danger";
              let err = "";
              for (const property in data.data) {
                err += data.data[property] + "\n\n";
              }
              console.log(err);
              this.err = err;
              this.alert.msg = `
                            ${err}`;
              this.alert.dismissCountDown = 5;
            } else {
              this.err = "";
              this.alert.variant = "warning";
              this.alert.msg = "Categorie modifié avec succès";
              this.alert.dismissCountDown = 5;
              this.fetchCategorie();
              this.resetModal1();
              this.hideModal("CategorieModal");
            }
          });
      }
    },
  },
};
</script>

<style></style>
