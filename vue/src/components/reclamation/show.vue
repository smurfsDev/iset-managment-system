<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Reclamations.length == 0"
    >
      <h3>il y'a aucune Reclamation</h3>
    </div>
    <b-card
      class="my-2"
      v-for="Reclamation in Reclamations"
      :key="Reclamation.id"
    >
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="Reclamation"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col> Titre :{{ Reclamation.titre }} </b-col>
              </b-row>
              <b-row>
                <b-col> Description : {{ Reclamation.description }} </b-col>
              </b-row>

              <b-row>
                <b-col> Type : {{ Reclamation.type.title }} </b-col>
              </b-row>

              <b-row v-if="Reclamation.file != null">
                <b-col>
                  <img :src="Reclamation.file" alt="" width="250" />
                </b-col>
              </b-row>

              <div
                v-if="
                  $store.getters.isAdmin || $store.getters.isChefDepartement
                "
                role="group"
                class="row"
                style="align-items: LEFT"
              >
                <div class="col-md-4 row">
                  <strong>REPONSE :</strong>
                </div>
                <div class="col-md-5">
                  <textarea
                    v-model="Reclamation.reponse"
                    class="form-control"
                    name="reponse"
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
                      variant="warning"
                      v-if="Reclamation.reponse != null"
                      @click="setReponse(Reclamation.id, Reclamation.reponse)"
                      >Send</b-button
                    >
                    <b-button
                      type="button"
                      variant="success"
                      v-else
                      @click="setReponse(Reclamation.id, Reclamation.reponse)"
                      >Add Reponse</b-button
                    >
                  </div>
                </div>
              </div>
              <b-col v-else class="text-danger">
                REPONSE :
                {{
                  Reclamation.reponse ? Reclamation.reponse : "Pas de reponse"
                }}
              </b-col>
            </b-row>

            <b-button
              variant="danger"
              v-if="$store.getters.isAdmin || $store.getters.isChefDepartement"
              v-on:click="Delete(Reclamation.id)"
            >
              Delete</b-button
            >
            <b-button
              v-if="!$store.getters.isAdmin"
              variant="warning"
              v-on:click="Update(Reclamation)"
            >
              Update</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="Reclamations.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchReclamation(pagination.prev_page_url)"
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
            @click="fetchReclamation(pagination.next_page_url)"
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
export default {
  props: {
    Reclamations: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
      currPage: 1,
    };
  },

  emits: [
    "deleteReclamation",
    "updateReclamation",
    "fetchReclamation",
    "setReponse",
  ],
  methods: {
    Delete(id) {
      this.$emit("deleteReclamation", id);
    },
    Update(Reclamation) {
      this.$emit("updateReclamation", Reclamation);
      this.showModal("ReclamationModal");
    },
    fetchReclamation(url) {
      this.$emit("fetchReclamation", url);
    },
    makeFile(data) {
      return data;
    },
    setReponse(id, reponse) {
      this.$emit("setReponse", id, reponse);
    },
  },
};
</script>

<style>
.app-header {
  padding: 16px;
  box-shadow: 0 2px 8px 4px rgba(0, 0, 0, 0.1);
  background-color: #555;
  color: #ddd;
}

.app-content {
  padding: 24px 16px;
}

.right {
  float: right;
}
</style>
