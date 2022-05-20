<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Classes.length == 0"
    >
      <h3>il y'a aucune Classe</h3>
    </div>
    <b-card class="my-2" v-for="Classe in Classes" :key="Classe.id">
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'success',
          title: 'Classe accepted',
          placement: 'topright',
        }"
        v-if="Classe.status == 1"
        icon="check-square"
        scale="2"
        variant="success"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'danger',
          title: 'Classe not accepted',
          placement: 'topright',
        }"
        v-if="Classe.status == 2"
        icon="x-square"
        scale="2"
        variant="danger"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'warning',
          title: 'Classe pending',
          placement: 'topright',
        }"
        v-if="Classe.status == 0"
        icon="x-square"
        scale="2"
        variant="warning"
      ></b-icon>
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
                  Nom  : <button class="btn white" v-on:click="addmat(Classe.id)"> {{ Classe.nom }} </button>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  Abbreveation : {{ Classe.abreviation }} 
                </b-col>
              </b-row>
            </b-row>
            <b-button
              variant="danger"
              v-on:click="Delete(Classe.id)"
            >
              Delete</b-button
            >
            <b-button
              variant="warning"
              v-on:click="Update(Classe)"
            >
              Update</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="Classes.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchClasse(pagination.prev_page_url)"
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
            @click="fetchClasse(pagination.next_page_url)"
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
    Classes: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
    };
  },
  emits: ["deleteClasse", "updateClasse", "fetchClasse"],
  methods: {
    Delete(id) {
      this.$emit("deleteClasse", id);
    },
    Update(Classe) {
      this.$emit("updateClasse", Classe);
      this.showModal("ClasseModal");
    },
    fetchClasse(url) {
      this.$emit("fetchClasse", url);
    },
    addmat(id) {
      
      this.$router.push({ name:"GererMatiere", params:{
            id: id,
          }});
    }
  },
};
</script>

<style></style>
