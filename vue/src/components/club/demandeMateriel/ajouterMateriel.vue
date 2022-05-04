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
          <h5 class="modal-title" id="MaterielModalLabel">Créer une demande</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('MaterielModal')"
          ></button>
        </div>

        <form class="mb-3" @submit.prevent="sendMat()">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Materiels :</label>
              <div
                class="form-check"
                v-for="materiel in materiels"
                :key="materiel.id"
              >
                <input
                  class="form-check-input"
                  type="checkbox"
                  :value="materiel.titre"
                  :id="materiel.id"
                  @change="ajoutMateriel(materiel.id)"
                />
                <label class="form-check-label">
                  {{ materiel.titre }}
                </label>
              </div>
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
  data() {
    return {
      add: false,
      Materiels: [],
      id: "",
      Mat: [],
      fiche: {
        rebriques: [],
        titre: "",
        id: "",
      },
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
    };
  },
  props: {
    ajouterMateriel: Object,
    categories: Array,
    oldDemande: Object,
    edit: Boolean,
    idDemande: Number,
    idCategorie: Number,
    materiels: Array,
    M: Array,
  },

  created() {},
  emits: ["addDemande"],
  mounted() {},
  methods: {
    addDemande() {
      this.$emit("addMateriel", this.oldDemande);
      this.resetModal1();
      this.hideModal("MaterielModal");
    },
    resetModal1() {
      $(".m").val("");
    },
    ajoutMateriel(id) {
      let x = -1;
      for (var i = 0; i < this.Mat.length; i++) {
        if (this.Mat[i] == id) {
          x = i;
        }
      }
      if (x != -1) {
        this.Mat.splice(x, 1);
      } else if (x == -1) {
        this.Mat.push(id);
      }
      this.$emit("Mat", this.Mat);
    },
    sendMat() {
      this.$emit("add", true);
      this.hideModal("MaterielModal");
    },
  },
};
</script>

<style></style>
