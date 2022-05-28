<template>
  <div>
    <formCategorie :edit="edit" :err="err" @fetchCategorie="fetchCategorieMateriel"  :oldCategorie="Categorie" />
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
              <b-col cols="8">
                <button type="button" class="btn btn-primary mx-1 float-start" data-bs-toggle="modal"
                  @click="initModal()" data-bs-target="#CategorieModal"
                  :class="haveClub? 'disabled' : ''"
                  >
                  Nouvelle Categorie
                </button>
              </b-col>
              <b-col></b-col>
            </b-row>
          </b-container>
          <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <showCategories @deleteCategorie="deleteCategorie" :Categories="CategorieMateriel"
            @fetchCategorie="fetchCategorieMateriel" @updateCategorie="updateCategorie" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showCategories from "./show.vue";
import formCategorie from "./form.vue";
// import search from '../search.vue';

export default {
  components: {
    showCategories,
    formCategorie,
    //     search
  },
  data() {
    return {
      CategorieMateriel: [],
      Categorie: {},
      pagination: {},
      edit: false,
      search: "",
      show: true,
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      err:'',

    };
  },
  created() {
    // console.log(typeof $);
    document.title = "Categorie";
    this.fetchCategorieMateriel();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Categorie ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Categorie modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchCategorieMateriel(
      page_url = "http://127.0.0.1:8000/api/c/",alert={
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
    ) {
      let vm = this;
      this.$http.get(page_url)
      .then((res)=> {
        this.CategorieMateriel = res.data.data;
        this.show = false;
        this.edit=false;
        this.alert=alert
        vm.makePagination(res.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/c?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteCategorie(id) {
      if (confirm("Delete document " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/c/" + id)
        .then(() => {
          this.fetchCategorieMateriel();
            this.alert.variant = "danger";
            this.alert.msg = "Categorie suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    resetModal1() {
      this.document = {};
    },
  
    updateCategorie(Categorie) {
      this.edit = true;
      this.Categorie = Categorie;
    },
    searchEmploye(search) {
      this.search = search;
      this.fetchCategorieMateriel();
    },
    initModal() {
      this.Categorie = {};
      this.showModal("CategorieModal");
    },
  },
  computed: {
    haveClub() {
      // check if at least one CategorieMateriel have status 1
      return this.CategorieMateriel.some((CategorieMateriel) => {
        return CategorieMateriel.status == 1;
      });
    },
  },
};
</script>

<style>
.btun {
  color: white !important;
  background-color: #d32f2f !important;
}
</style>
