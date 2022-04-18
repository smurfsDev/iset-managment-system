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

              <b-row>
                <b-col
                  >Date d'emploi : {{ demande.dateEmploi }} Date de remise :
                  {{ demande.dateDeRemise }}</b-col
                >
              </b-row>
            </b-row>

            <b-row>
              Materiels :
              <b-row v-for="mat in demande.materiel" :key="mat.id">
                <div>
                  <div role="group" class="row" style="align-items: center">
                    <div class="col-md-4 row">
                      <div class="col-9">
                        <p class="text-left">
                          <strong>{{ mat.titre }}</strong>
                        </p>
                      </div>
                      <div class="col-3">
                        <span style="text-align: centre">Qt</span>
                      </div>
                    </div>
                    <div class="col-md-5">
                      <input
                        type="number"
                        min="0"
                        class="form-control"
                        v-model="mat.pivot.quantité"
                        name="quantité"
                        style="
                          background-color: rgb(236, 239, 241);
                          border: 0px !important;
                        "
                      />
                    </div>
                    <div class="col-md-3">
                      <div class="input-group-append">
                        <b-button
                          type="button"
                          variant="success"
                          @click="
                            setQuantity(mat.id, demande.id, mat.pivot.quantité)
                          "
                        >
                          <svg
                            viewBox="0 0 16 16"
                            width="1em"
                            height="1em"
                            focusable="false"
                            role="img"
                            aria-label="check"
                            xmlns="http://www.w3.org/2000/svg"
                            fill="currentColor"
                            class="bi-check b-icon bi"
                          >
                            <g
                              transform="translate(8 8) scale(2 2) translate(-8 -8)"
                            >
                              <path
                                d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"
                              ></path>
                            </g>
                          </svg>
                        </b-button>
                        <b-button
                          type="button"
                          variant="danger"
                          @click="deleteMateriel(mat.id, demande.id)"
                        >
                          <svg
                            viewBox="0 0 16 16"
                            width="1em"
                            height="1em"
                            focusable="false"
                            role="img"
                            aria-label="trash fill"
                            aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg"
                            fill="currentColor"
                            class="bi-trash-fill b-icon bi"
                          >
                            <g>
                              <path
                                d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"
                              ></path>
                            </g>
                          </svg>
                        </b-button>
                      </div>
                    </div>
                  </div>
                </div>
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
      quantite: 1,
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
    setQuantity(idMateriel, idDemande, quantite) {
      let headersi = new Headers();
      headersi.append("Content-Type", "application/json");
      fetch("http://127.0.0.1:8000/api/m/Q/" + idMateriel + "/" + idDemande, {
        method: "put",
        body: JSON.stringify({ quantité: quantite }),
        headers: headersi,
      })
        .then((res) => res.json())
        .then((data) => {
          if (data.attached == true) {
            this.alert.variant = "success";
            this.alert.msg = "quantité attachée avec succès";
            this.alert.dismissCountDown = 5;
          } else {
            this.alert.variant = "danger";
            this.alert.msg = "quantité détachée avec succès";
            this.alert.dismissCountDown = 5;
          }
        })
        .catch((err) => console.log(err));
    },
    //delete materiel
    deleteMateriel(idMateriel, idDemande) {
      fetch("http://127.0.0.1:8000/api/m/" + idMateriel + "/" + idDemande, {
        method: "delete",
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
