<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Materiels.length == 0"
    >
      <h3>il y'a aucune Materiel</h3>
    </div>
    <b-card class="my-2" v-for="Materiel in Materiels" :key="Materiel.id">
      
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="Materiel"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Libel : {{ Materiel.titre }} Description :
                  {{ Materiel.description }}
                </b-col>
              </b-row>
              <b-row>
                <b-col>Categorie : {{ Materiel.categorie.titre }}<br>quantité : {{ Materiel.quantité }}</b-col>
              </b-row>
              
            </b-row>
            <b-button
              variant="danger"
              v-on:click="Delete(Materiel.id)"
            >
              Supprimer</b-button
            >
            <b-button
              variant="warning"
              v-on:click="Update(Materiel)"
            >
              Modifier</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="Materiels.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchMateriel(pagination.prev_page_url)"
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
            @click="fetchMateriel(pagination.next_page_url)"
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
    Materiels: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
    };
  },
  emits: ["deleteMateriel", "updateMateriel", "fetchMateriel"],
  methods: {
    Delete(id) {
      this.$emit("deleteMateriel", id);
    },
    Update(Materiel) {
      this.$emit("updateMateriel", Materiel);
      this.showModal("MaterielModal");
    },
    fetchMateriel(url) {
      this.$emit("fetchMateriel", url);
    },
  },
};
</script>

<style></style>
