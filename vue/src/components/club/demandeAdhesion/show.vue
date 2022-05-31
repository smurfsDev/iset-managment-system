<template>
  <div>
    <div
      class="card card-body bg-white my-5 py-5 text-center"
      v-if="demandes.length == 0"
    >
      <h3>il y'a aucune demande</h3>
    </div>
    <b-card class="my-2" v-for="demande in demandes" :key="demande.id">
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="demande"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Nom  : {{ demande.user.name }} 
                </b-col>
              </b-row>
              <b-row>
                <b-col>Message : {{ demande.message }}</b-col>
              </b-row>
            </b-row>
            <b-button
              variant="success"
              :class="[{ disabled: demande.status == 1 }]"
              v-on:click="Accept(demande.id)"
              >Accepter</b-button
            >
            <b-button
              variant="danger"
              :class="[{ disabled: demande.status == 2 }]"
              v-on:click="Decline(demande.id)"
              >Refuser</b-button
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
  emits: ["acceptDemande", "declineDemande", "fetchDemande"],
  methods: {
    Accept(id) {
      this.$emit("acceptDemande", id);
    },
    Decline(id) {
      this.$emit("declineDemande", id);
    },
    fetchDemande(url) {
      this.$emit("fetchDemande", url);
    },

  },
};
</script>

<style></style>
