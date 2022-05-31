<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Typecats.length == 0"
    >
      <h3>il y'a aucune type reclamation</h3>
    </div>
    <b-card class="my-2" v-for="Typecat in Typecats" :key="Typecat.id">
     
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="Categorie"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
        
              <b-row>
                <b-col>
                  Titre : {{ Typecat.title }} 
                </b-col>
              </b-row>
        
              
            </b-row>
            <b-button
              variant="danger"
              v-if="$store.getters.isAdmin||$store.getters.isChefDepartement"
              v-on:click="Delete(Typecat.id)"
            >
              Supprimer</b-button
            >
            <b-button
              v-if="$store.getters.isAdmin||$store.getters.isChefDepartement"
              variant="warning"
              v-on:click="Update(Typecat)"
            >
              Modifier</b-button
            >
          </div>
  </md-tab>
  </md-tabs>
    </b-card>
    <nav class="row" v-if="Typecats.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchTypecat(pagination.prev_page_url)"
            class="btn btun page-link"
            :class="[
              !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
            ]"
            >Precedent</a>
        </li>
        <li class="page-item">
          <a class="page-link text-dark" href="#">{{
            pagination.current_page + "/" + pagination.last_page
          }}</a>
        </li>
        <li
          :class="[{ disabled: !pagination.next_page_url }]"
          class="page-item" >
          <a
            @click="fetchTypecat(pagination.next_page_url)"
            :class="[
              !pagination.next_page_url ? 'disabled' : 'link-primary btun',
            ]"
            class="btun btn page-link"
            >Suivant</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>

export default {
 
  props: {
    Typecats: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
      currPage: 1,
    };
  },
  emits: ["deleteTypecat", "updateTypecat", "fetchTypecat"],
  methods: {
    Delete(id) {
      this.$emit("deleteTypecat", id);
    },
    Update(Typecat) {
      this.$emit("updateTypecat", Typecat);
      this.showModal("TypecatModal");
    },
    fetchTypecat(url) {
      this.$emit("fetchTypecat", url);
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
