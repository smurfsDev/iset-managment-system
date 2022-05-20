<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Categories.length == 0"
    >
      <h3>il y'a aucune categorie</h3>
    </div>
    <b-card class="my-2" >
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="categorie"
        >
          <main>
            <data-table v-bind="parameters" @actionTriggered="handleAction" />
          </main>
        </md-tab>
      </md-tabs>
    </b-card>
    <!-- <nav class="row" v-if="Categories.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchcategorie(pagination.prev_page_url)"
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
            @click="fetchcategorie(pagination.next_page_url)"
            :class="[
              !pagination.next_page_url ? 'disabled' : 'link-primary btun',
            ]"
            class="btun btn page-link"
            >Suivant</a
          >
        </li>
      </ul>
    </nav> -->
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
    handleAction(action, data) {
      if (action == 'delete') {
        if (confirm("Suprimer categorie ?")) {
          this.Delete(data.id);
        }
      }else if (action == 'edit') {
          this.Update(data);
      }
    }
  },
  computed: {
    parameters() {
      return {
        data: this.Categories,
        text: {
          perPageText: 'Afficher :entries lignes',
          infoText: 'Affichage de :first a :last de :total lignes',
          infoTextFiltered: 'Affichage de :first a :last de :total lignes(filtrée de :total)',
          nextButtonText: 'Suiva',
          previousButtonText: 'Prec',
          paginationSearchText: 'Aller vers page',
          searchText: 'recherche',
          downloadButtonText: 'TELECHARGER',
          emptyTableText: 'Aucun demande trouvée'
        },
        actionMode: "single",
        actions: ['delete','edit']
        ,
        columns: [
          {
            key: "titre",
            title: "Titre",
          }
        ]
      };
    }
  },
};
</script>

<style></style>
