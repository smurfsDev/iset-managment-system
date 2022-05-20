<template>
  <div>
   <h1>Bonjourr </h1>
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
            
              <b-col></b-col>
            </b-row>
          </b-container>
          <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <showClasses @deleteClasse="deleteClasse" :Classes="Classes"
            @fetchClasse="fetchClasse" @updateClasse="updateClasse" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showClasses from "./showClasses.vue";

// import search from '../search.vue';

export default {
  components: {
    showClasses
    //     search
  },
  data() {
    return {
      Classe: {},
      Classes: [],
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
    };
  },
  created() {
    // console.log(typeof $);
    document.title = "Classe";
    this.fetchClasse();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Classe ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Classe modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchClasse(
      page_url = "http://127.0.0.1:8000/api/matiere/"
    ) {
      let vm = this;
      this.$http.get(page_url)
      .then((res)=> {
        console.log(res.data);
        this.Classes = res.data;
        this.show = false;
        vm.makePagination(res.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/Classe?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteClasse(id) {
      if (confirm("Delete Classe " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/Classe/" + id)
        .then(() => {
          this.fetchClasse();
            this.alert.variant = "danger";
            this.alert.msg = "Classe suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    resetModal1() {
      this.Classe = {};
    },
    addClasse(Classe) {
      
      this.show = true;
      if (!this.edit) {
       this.$http.post('http://localhost:8000/api/Classe',
        (Classe))
        .then((data) => {
          data = data.data;
          if (data.success == false) {
              this.alert.variant = "danger";
              let err = "";
              for (const property in data.data) {
                err += data.data[property] + "\n\n";
              }
              console.log(err);
              this.alert.msg = `
                            ${err}`;
              this.alert.dismissCountDown = 5;
            } else {
              this.alert.variant = "success";
              this.alert.msg = "Classe ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchClasse();
        });
      } else {
        this.$http.put("http://localhost:8000/api/Classe/" + Classe.id,
        (Classe))
        .then(() => {
          this.fetchClasse();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Classe modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    updateClasse(Classe) {
      this.edit = true;
      this.Classe = Classe;
    },
    searchEmploye(search) {
      this.search = search;
      this.fetchClasse();
    },
    initModal() {
      this.Classe = {};
      this.showModal("ClasseModal");
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
