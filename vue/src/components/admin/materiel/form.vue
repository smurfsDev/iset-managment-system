<template>
  <div
    class="modal fade"
    id="MaterielModal"
    tabindex="-1"
    aria-labelledby="MaterielModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="MaterielModalLabel">Créer une Materiel</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('MaterielModal')"
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
        <form class="mb-3" @submit.prevent="addMateriele">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Titre:</label>
              <input
                type="text"
                class="border-0 m form-control"
                placeholder="titre"
                v-model="oldMateriel.titre"
                required="required"
              />
              <label>Description:</label>
              <textarea
                class="border-0 m form-control"
                placeholder="Description"
                v-model="oldMateriel.description"
                required="required"
              ></textarea>
              <label>Categorie</label>
              <select 
                class="border-0 m form-control"
                v-model="oldMateriel.idCategorie"
                required="required"
              >
                <option value="" selected disabled>-- Selectionner une categorie --</option>
                <option v-for="categorie in categories" :value="categorie.id" :key="categorie.id">
                  {{ categorie.titre }}
                </option>
              </select>
              
              <label>quantité:</label>
              <input
                type="number"
                class="border-0 m form-control"
                placeholder="quantité"
                v-model="oldMateriel.quantité"
                required="required"
              />
              
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('MaterielModal')"
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
    oldMateriel: Object,
    edit: Boolean,
  },
  data() {
    return {
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      categories: [],
      err: null,
    };
  },
  emits: ["fetchMateriel"],
  mounted() {this.fetchCategories()},
  methods: {
    fetchMateriel() {
      this.$emit(
        "fetchMateriel",
        "http://127.0.0.1:8000/api/m/",
        this.alert
      );
    },
    fetchCategories(){
      this.$http.get("http://localhost:8000/api/c/").then(response => {
        this.categories = response.data.data;
      });
    },
    resetModal1() {
      $(".m").val("");
    },
    addMateriele() {
      this.show = true;
      if (!this.edit) {
        this.$http
          .post("http://localhost:8000/api/m", this.oldMateriel)
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
              this.alert.msg = "Materiel ajouté avec succès";
              this.alert.dismissCountDown = 5;
              this.resetModal1();
              this.hideModal("MaterielModal");
            }
            this.fetchMateriel();
          });
      } else {
        this.$http
          .put(
            "http://localhost:8000/api/m/" + this.oldMateriel.id,
            this.oldMateriel
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
              this.alert.msg = "Materiel modifié avec succès";
              this.alert.dismissCountDown = 5;
              this.fetchMateriel();
              this.resetModal1();
              this.hideModal("MaterielModal");
            }
          });
      }
    },
  },
};
</script>

<style></style>
