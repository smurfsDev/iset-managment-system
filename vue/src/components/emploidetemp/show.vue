<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="EmploideTemps.length == 0"
    >
      <h3>il y'a aucune Emploi de Temp</h3>
    </div>
    <b-card
      class="my-2"
      v-for="EmploideTemp in EmploideTemps"
      :key="EmploideTemp.id"
    >
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'success',
          title: '',
          placement: 'topright',
        }"
      ></b-icon>

      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="EmploideTemp"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col> Titre :{{ EmploideTemp.title }} </b-col>
              </b-row>
              <b-row>
                <b-col> Classe : {{ EmploideTemp.classes.abreviation }} </b-col>
              </b-row>
              <b-row v-if="EmploideTemp.file != null">
                <b-col>
                  <img :src="EmploideTemp.file" alt="" width="%50" />
                </b-col>
              </b-row>
            </b-row>

            <b-button
              variant="danger"
              v-if="$store.getters.isAdmin || $store.getters.isChefDepartement"
              v-on:click="Delete(EmploideTemp.id)"
            >
              Delete</b-button
            >
            <b-button
              v-if="!$store.getters.isAdmin"
              :class="[{ disabled: EmploideTemp.reponse }]"
              variant="warning"
              v-on:click="Update(EmploideTemp)"
            >
              Update</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="EmploideTemps.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchEmploideTemp(pagination.prev_page_url)"
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
            @click="fetchEmploideTemp(pagination.next_page_url)"
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
    EmploideTemps: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
      currPage: 1,
    };
  },

  emits: ["deleteEmploideTemp", "updateEmploideTemp", "fetchEmploideTemp"],
  methods: {
    Delete(id) {
      this.$emit("deleteEmploideTemp", id);
    },
    Update(EmploideTemp) {
      this.$emit("updateEmploideTemp", EmploideTemp);
      this.showModal("EmploideTempModal");
    },
    fetchEmploideTemp(url) {
      this.$emit("fetchEmploideTemp", url);
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
