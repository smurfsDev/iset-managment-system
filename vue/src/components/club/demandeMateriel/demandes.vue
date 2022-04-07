<template>
  <div>
    <!-- <ajouterMateriel
      @addateriel="addDemande"
      :oldDemande="demande"
      :Materiels="Materiels"
    /> -->
    <ajouterMateriel
      :idDemande="idDemande"
      :materiels="materiels"
      v-on:Mat="Matt"
      v-on:add="addMateriels"
    />
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="demandes.length == 0"
    >
      <h3>il y'a aucune demande</h3>
    </div>
    <b-card class="my-2" v-for="demande in demandes" :key="demande.id">
      <md-tabs>
        <md-tab id="tab-home" md-label="demande">
          <div class="bv-example-row text-center">
            <!-- <b-button variant="warning" v-on:click="fetchMaterielDemande(demande.id)">fetch</b-button> -->
            <b-row class="mb-2">
              <b-row>
                <b-col
                  >ID du destinateur : {{ demande.idDestinataire }} Date
                  création : {{ demande.created_at }}</b-col
                >
              </b-row>
              <!-- <b-row > Materiels :
                <b-col v-for="materiel in Mteriels" :key="materiel.id">{{materiel.titre}}</b-col>
              </b-row> -->
              <b-row>
                <b-col
                  >Date d'emploi : {{ demande.dateEmploi }} Date de remise :
                  {{ demande.dateDeRemise }}</b-col
                >
              </b-row>
            </b-row>
            <b-button
              @click="ajouterMateriel(demande.id, demande.idCategorie)"
              variant="success"
              >Ajouter materiels</b-button
            >
            <b-button variant="danger" v-on:click="Delete(demande.id)"
              >Delete</b-button
            >
            <b-button variant="warning" v-on:click="Update(demande)"
              >Update</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="demandes.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchDemande(pagination.prev_page_url)"
            class="btn btun page-link"
            :class="[
              !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
            ]"
            >Precedent</a
          >
        </li>
        <li class="page-item">
          <a class="page-link text-dark" href="#">{{
            pagination.current_page + "/" + pagination.last_page
          }}</a>
        </li>
        <li
          :class="[{ disabled: !pagination.next_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchDemande(pagination.next_page_url)"
            :class="[
              !pagination.next_page_url ? 'disabled' : 'link-primary btun',
            ]"
            class="btun btn page-link"
            >Suivant</a
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import ajouterMateriel from "./ajouterMateriel.vue";

export default {
  components: {
    ajouterMateriel,
  },
  props: {
    demandes: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
      idDemande: 0,
      idCategorie: 0,
      materiels: [],
      Mat: [],
      add: false,
      Mteriels: [],
      reAjouter: false,
    };
  },
  created() {
    // console.log(typeof $);
    this.fetchMateriels();
  },

  emits: ["deleteDemande", "updateDemande", "fetchDemande", "ajouterMateriel"],
  methods: {
    Matt(value) {
      this.Mat = value;
    },
    attachMateriel(idMateriel, idDemande) {
      fetch("http://127.0.0.1:8000/api/m/" + idMateriel + "/" + idDemande, {
        method: "post",
      })
        .then((res) => res.json())
        .then((data) => {
          if (data.attached == true) {
            this.alert.variant = "success";
            this.alert.msg = "Materiel attachée avec succès";
            this.alert.dismissCountDown = 5;
          } else {
            this.alert.variant = "danger";
            this.alert.msg = "Materiel détachée avec succès";
            this.alert.dismissCountDown = 5;
          }
        })
        .catch((err) => console.log(err));
    },
    addMateriels(value) {
      this.add = value;
      if (this.add == true) {
        for (var i = 0; i < this.Mat.length; i++) {
          this.attachMateriel(this.Mat[i], this.idDemande);
          console.log("tess" + this.Mat[i]);
        }
        this.Mat = [];
      } else console.log("zedz" + this.add);
    },
    initModal() {
      this.demande = {};
      this.showModal("MaterielModal");
    },
    Delete(id) {
      this.$emit("deleteDemande", id);
    },
    Update(demande) {
      this.$emit("updateDemande", demande);
      this.showModal("demandeMaterielModal");
    },
    ajouterMateriel(id, idCategorie) {
      this.idDemande = id;
      this.idCategorie = idCategorie;
      this.fetchMateriels();
      this.showModal("MaterielModal");
    },
    fetchDemande(url) {
      this.$emit("fetchDemande", url);
    },
    fetchMateriels(
      page_url = "http://127.0.0.1:8000/api/m/" + this.idCategorie
    ) {
      fetch(page_url, {
        method: "GET",
      })
        .then((res) => res.json())
        .then((res) => {
          this.materiels = res.data;
        })
        .catch((err) => console.log(err));
    },
    fetchMaterielDemande(id) {
      fetch("http://127.0.0.1:8000/api/m/M/" + id, {
        method: "GET",
      })
        .then((res) => res.json())
        .then((res) => {
          this.Mteriels = res.data;
        })
        .catch((err) => console.log(err));
      console.log("adzdazedazedazed");
    },
    //   addDemande(idMateriel,idDemande) {
    //   if (!this.reAjouter) {
    //     fetch('http://127.0.0.1:8000/api/m/'+idMateriel+'/'+idDemande, {
    //       method: "post",
    //     })
    //       .then(res => res.json())
    //       .then(data => {
    //           if (data.attached == true) {
    //               this.alert.variant = "success";
    //               this.alert.msg = "Materiel attachée avec succès"
    //               this.alert.dismissCountDown = 5;
    //           } else {
    //               this.alert.variant = "danger";
    //               this.alert.msg = "Materiel détachée avec succès"
    //               this.alert.dismissCountDown = 5;
    //           }
    //       }
    //     )
    //       .catch((err) => console.log(err));
    //   } else {
    //     fetch("http://localhost:8000/api/dm/" + demande.id, {
    //       method: "put",
    //       body: JSON.stringify(demande),
    //       headers: headersi,
    //     })
    //       .then((res) => res.json())
    //       .then(() => {
    //         this.fetchDemandeMateriels();
    //         this.reAjouter = false;
    //         this.alert.variant = "warning";
    //         this.alert.msg = "demande modifié avec succès";
    //         this.alert.dismissCountDown = 5;
    //       })
    //       .catch((err) => console.log(err));
    //   }
    // },
    updateDemande(demande) {
      this.reAjouter = true;
      this.demande = demande;
    },
  },
};
</script>

<style></style>
