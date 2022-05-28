<template>
  <div>
    <formCategorie @addCategorie="addCategorie" :oldCategorie="Categorie" />
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
              <b-col cols="8">
                <button 
                  v-if="$store.getters.isAdmin||$store.getters.isChefDepartement"
                  type="button" class="btn btn-primary mx-1 float-start" data-bs-toggle="modal"
                  @click="initModal()" data-bs-target="#CategorieModal">
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
          <showCategories @deleteCategorie="deleteCategorie" :Categories="Categories"
            @fetchCategorie="fetchCategorie" @updateCategorie="updateCategorie" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showCategories from "./show.vue";
import formCategorie from "./form.vue";

export default {
  components: {
    showCategories,
    formCategorie,
  
  },
  data() {
    return {
      Categorie: {},
      Categories: [],
      pagination: {},
      edit: false,
      search: "",
      show: true,
      alert: {
          dismissCountDown: 0,
          variant: "",
          msg: "",
      },
      myid: 1,
      idCategorie: "",
    };
  },

  created() {
    window.title = "Categorie";
    this.fetchCategorie();

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
    fetchCategorie(
      page_url = "http://127.0.0.1:8000/api/Categorie/"
    ) {
      let vm = this;
      this.$http.get(page_url)
      .then((res)=> {
        this.Categories = res.data.data.data;
        this.show = false;
        vm.makePagination(res.data.data);
      })
      .finally(()=> {
        this.show = false;
      });
    },

    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/Categorie?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },

    deleteCategorie(id) {
      if (confirm("Delete Categorie " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/Categorie/" + id)
        .then(() => {
          this.fetchCategorie();
            this.alert.variant = "danger";
            this.alert.msg = "Categorie suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },

    resetModal1() {
      this.Categorie = {};
    },

    addCategorie(Categorie) {
      
      this.show = true;
      if (!this.edit) {
       this.$http.post('http://localhost:8000/api/Categorie',
        (Categorie))
        .then((data) => {
          data = data.data;
          if (data.success == false) {
              this.alert.variant = "danger";
              let err = "";
              for (const property in data.data) {
                err += data.data[property] + "\n\n";
              }
              console.log(err);
              this.alert.msg = `${err}`;
              this.alert.dismissCountDown = 5;
            } else {
              this.alert.variant = "success";
              this.alert.msg = "Categorie ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchCategorie();
        });
      } else {
        this.$http.put("http://localhost:8000/api/Categorie/" + Categorie.id,
        (Categorie))
        .then(() => {
          this.fetchCategorie();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Categorie modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },

    updateCategorie(Categorie) {
      this.edit = true;
      this.Categorie = Categorie;
    },

    searchDOC(search) {
      this.search = search;
      this.fetchCategorie();
    },
    
    initModal() {
      this.Categorie = {};
      this.showModal("CategorieModal");
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
