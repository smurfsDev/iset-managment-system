<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Categories.length == 0"
    >
      <h3>il y'a aucune Categorie</h3>
    </div>
    <b-card class="my-2" v-for="Categorie in Categories" :key="Categorie.id">
     
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
                  Title : {{ Categorie.title }} 
                </b-col>
              </b-row>
        
              
            </b-row>
            <b-button
              variant="danger"
              v-if="$store.getters.isAdmin||$store.getters.isChefDepartement"
              :class="[{ disabled: $store.getters.StateUser.id != Categorie.idResponsable }]"
              v-on:click="Delete(Categorie.id)"
            >
              Delete</b-button
            >
            <b-button
              v-if="$store.getters.isAdmin||$store.getters.isChefDepartement"
              :class="[{ disabled: $store.getters.StateUser.id != Categorie.idResponsable }]"
              variant="warning"
              v-on:click="Update(Categorie)"
            >
              Update</b-button
            >
          </div>
  </md-tab>
  </md-tabs>
    </b-card>
    <nav class="row" v-if="Categories.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchCategorie(pagination.prev_page_url)"
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
            @click="fetchCategorie(pagination.next_page_url)"
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
    Categories: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
      currPage: 1,
    };
  },
  emits: ["deleteCategorie", "updateCategorie", "fetchCategorie"],
  methods: {
    Delete(id) {
      this.$emit("deleteCategorie", id);
    },
    Update(Categorie) {
      this.$emit("updateCategorie", Categorie);
      this.showModal("CategorieModal");
    },
    fetchCategorie(url) {
      this.$emit("fetchCategorie", url);
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
