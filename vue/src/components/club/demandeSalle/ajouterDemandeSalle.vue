<template>
  <div
    class="modal fade"
    id="demandeSalleModal"
    tabindex="-1"
    aria-labelledby="demandeSalleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="demandeSalleModal">Créer une demande</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('demandeSalleModal')"
          ></button>
        </div>

        <form class="mb-3" @submit.prevent="addDemande">
          <div class="modal-body">
            <div class="form-group mb-2">
                <label>Departement :</label>
              <div class="form-group">
                <select name="idDepatement"  required="required" @change ="fetchSalles(DepId)" v-model="DepId">
                  <option 
                    v-for="departement in departements"
                    :key="departement.id"
                    :value="departement.id" 
                  >
                    {{ departement.titre }}
                  </option>
                </select>
              </div>
              <label>Salle :</label>
              <div class="form-group">
                <select name="idSalle"  required="required" v-model="oldDemande.idSalle">
                  <option
                    v-for="salle in salles"
                    :key="salle.id"
                    :value="salle.id"
                  >
                    {{ salle.titre }}
                  </option>
                </select>
              </div>
              <label>Date Emploi :</label>
              <input
                type="date"
                class="border-0 dcc form-control"
                placeholder
                v-model="oldDemande.dateEmploi"
                required="required"
              />
              <label>Date de Remise :</label>
              <input
                type="date"
                class="border-0 dcc form-control"
                placeholder
                v-model="oldDemande.dateDeRemise"
                required="required"
              />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('demandeSalleModal')"
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
        DepId: 0,
      salles: [],
      departements: [],
    }
    },
  props: {
    oldDemande: Object,
    edit: Boolean,
  },
  emits: ["addDemande"],
  mounted() {},
  created() {
    this.fetchDepartements();
    },
  methods: {
      fetchSalles(idDep){
      fetch("http://localhost:8000/api/Salle/"+ idDep, {
          method: "GET"
      })
        .then((response) => response.json())
        .then((data) => {
          this.salles = data.data;
        });
    },
    fetchDepartements(){
      fetch("http://localhost:8000/api/Departement")
        .then((response) => response.json())
        .then((data) => {
          this.departements = data.data;
        });
    },
    addDemande() {
      this.$emit("addDemande", this.oldDemande);
      this.resetModal1();
      this.hideModal("demandeSalleModal");
    },
    resetModal1() {
      $(".demandeSalle").val("");
    },
  },
};
</script>

<style></style>
