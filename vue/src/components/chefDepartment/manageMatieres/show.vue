<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Matieres.length == 0"
    >
      <h3>il y'a aucune Matiere</h3>
    </div>
    <b-card class="my-2" v-for="Matiere in Matieres" :key="Matiere.id">
      
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="Classe"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Nom  : {{ Matiere.nom }}
                </b-col>
              </b-row>
              
            </b-row>
            <b-button
              variant="danger"
              v-on:click="Delete(Matiere.id)"
            >
              Delete</b-button
            >
            <b-button
              variant="warning"
              v-on:click="Update(Matiere)"
            >
              Update</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="Matieres.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchMatiere(pagination.prev_page_url)"
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
            @click="fetchMatiere(pagination.next_page_url)"
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
    Matieres: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
    };
  },
  emits: ["deleteMatiere", "updateMatiere", "fetchMatiere"],
  methods: {
    Delete(id) {
      this.$emit("deleteMatiere", id);
    },
    Update(Matiere) {
      this.$emit("updateMatiere", Matiere);
      this.showModal("ClasseModal");
    },
    fetchMatiere(url) {
      this.$emit("fetchMatiere", url);
    },
  },
};
</script>

<style></style>
