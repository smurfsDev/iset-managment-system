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
          <h5 class="modal-title" id="ClasseModalLabel">Ajouter une matiere</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('ClasseModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addMatiere">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Nom du matiere:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Nom matiere"
                v-model="oldMatiere.nom"
                required="required"
              />
              
            </div>
          </div>
          <label>Nom enseignant :</label>
              <select name="idEnseignant" v-model="idEns" class="form-control" required="required">
                  <option 
                    v-for="ens in Enseignant"
                    :key="ens.id"
                    :value="ens.id"
                  >
                    {{ ens.name }}
                  </option>
              </select>
              <label>Semestre: </label>
              <select name="oldMatiere.semestre" v-model="oldMatiere.semestre" class="form-control" required="required">
                  <option 
                 
                    :value="1"
                  >
                    1
                  </option>
                  <option 
                    :value="2"
                  >
                    2
                  </option>
              </select><br>
              <label>Coefficient: </label>
              <input type="number"  v-model="oldMatiere.coefficient" >
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
    oldMatiere: Object,
   // Enseignant: Object,
    edit: Boolean,
  },
  data() {
    return {
      Enseignant: [],
      idEns: null
    }
  },
  emits: ["addMatiere"],
  mounted() {},
  created() {
    this.$http.get("http://localhost:8000/api/enseignant/approuved")
    .then((res)=>{
        
        this.Enseignant = res.data

    })
  },
  methods: {
    addMatiere() {
      this.$emit("addMatiere", this.oldMatiere, this.idEns);
      this.resetModal1();
     
      this.hideModal("ClasseModal");
    },
    resetModal1() {
      $(".dcc").val("");
    }
  },
};
</script>

<style></style>
