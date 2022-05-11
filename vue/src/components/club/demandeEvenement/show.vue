<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="demandes.length == 0"
    >
      <h3>il y'a aucune demande</h3>
    </div>
    <b-card class="my-2" v-for="demande in demandes" :key="demande.id">
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'success',
          title: 'Demande accepted',
          placement: 'topright',
        }"
        v-if="demande.status == 1"
        icon="check-square"
        scale="2"
        variant="success"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'danger',
          title: 'Demande not accepted',
          placement: 'topright',
        }"
        v-if="demande.status == 2"
        icon="x-square"
        scale="2"
        variant="danger"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'warning',
          title: 'Demande pending',
          placement: 'topright',
        }"
        v-if="demande.status == 0"
        icon="x-square"
        scale="2"
        variant="warning"
      ></b-icon>
      <md-tabs style="height: auto;!important">
        <md-tab
          style="height: auto;!important"
          id="tab-home"
          md-label="demande"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Nom du évenement : {{ demande.nomEvent }}<br> Date évenement :
                  {{ demande.dateEvent }} <br> Date création : {{ demande.created_at }}
                </b-col>
              </b-row>
              <b-row>
                <b-col>Description : {{ demande.description }}</b-col>
              </b-row>
             
            </b-row>
           
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
          <a class="page-link text-dark" href="#">
            {{ pagination.current_page + "/" + pagination.last_page }}
          </a>
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
export default {
  props: {
    demandes: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
    };
  },
  emits: ["fetchDemande"],
  methods: {
   
    fetchDemande(url) {
      this.$emit("fetchDemande", url);
    },
  },
};
</script>

<style></style>
