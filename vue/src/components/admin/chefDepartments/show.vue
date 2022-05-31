<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="ChefDepartements.length == 0"
    >
      <h3>il y'a aucune chefDepartement</h3>
    </div>
    <b-card class="my-2" v-for="chefDepartement in ChefDepartements" :key="chefDepartement.id">
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'success',
          title: 'chefDepartement accepted',
          placement: 'topright',
        }"
        v-if="chefDepartement.status == 1"
        icon="check-square"
        scale="2"
        variant="success"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'danger',
          title: 'chefDepartement not accepted',
          placement: 'topright',
        }"
        v-if="chefDepartement.status == 2"
        icon="x-square"
        scale="2"
        variant="danger"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'warning',
          title: 'chefDepartement pending',
          placement: 'topright',
        }"
        v-if="chefDepartement.status == 0"
        icon="x-square"
        scale="2"
        variant="warning"
      ></b-icon>
      <md-tabs style="height: auto;!important">
        <md-tab
          style="height: auto;!important"
          id="tab-home"
          md-label="chefDepartement"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Nom  : {{ chefDepartement.name }} 
                </b-col>
              </b-row>
              <b-row>
                <b-col>Email : {{ chefDepartement.email }}</b-col>
              </b-row>
            </b-row>
            <b-button
              variant="success"
              :class="[{ disabled: chefDepartement.roles[0].pivot.status == 1 }]"
              v-on:click="Accept(chefDepartement.id)"
              >Accepter</b-button
            >
            <b-button
              variant="danger"
              :class="[{ disabled: chefDepartement.roles[0].pivot.status == 2 }]"
              v-on:click="Decline(chefDepartement.id)"
              >Refuser</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="ChefDepartements.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchChefDepartement(pagination.prev_page_url)"
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
            @click="fetchChefDepartement(pagination.next_page_url)"
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
    ChefDepartements: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
    };
  },
  emits: ["acceptChefDepartement", "declineChefDepartement", "fetchChefDepartement"],
  methods: {
    Accept(id) {
      this.$emit("acceptChefDepartement", id);
    },
    Decline(id) {
      this.$emit("declineChefDepartement", id);
    },
    fetchChefDepartement(url) {
      this.$emit("fetchChefDepartement", url);
    },
  },
};
</script>

<style></style>
