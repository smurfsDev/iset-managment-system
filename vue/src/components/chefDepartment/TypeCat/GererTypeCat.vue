<template>
  <div>
    <formTypecat @addTypecat="addTypecat" :oldTypecat="Typecat" />
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
                  @click="initModal()" data-bs-target="#TypecatModal">
                  Nouvelle Typecat
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
          <showTypecats @deleteTypecat="deleteTypecat" :Typecats="Typecats"
            @fetchTypecat="fetchTypecat" @updateTypecat="updateTypecat" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showTypecats from "./show.vue";
import formTypecat from "./form.vue";

export default {
  components: {
    showTypecats,
    formTypecat,
  
  },
  data() {
    return {
      Typecat: {},
      Typecats: [],
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
      idTypecat: "",
    };
  },

  created() {
    window.title = "Typecat";
    this.fetchTypecat();

    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Typecat ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Typecat modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },

  methods: {
    fetchTypecat(
      page_url = "http://127.0.0.1:8000/api/Typecat/"
    ) {
      let vm = this;
      this.$http.get(page_url)
      .then((res)=> {
        this.Typecats = res.data.data.data;
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
          "http://localhost:8000/api/Typecat?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },

    deleteTypecat(id) {
      if (confirm("Delete Typecat " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/Typecat/" + id)
        .then(() => {
          this.fetchTypecat();
            this.alert.variant = "danger";
            this.alert.msg = "Typecat suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },

    resetModal1() {
      this.Typecat = {};
    },

    addTypecat(Typecat) {
      
      this.show = true;
      if (!this.edit) {
       this.$http.post('http://localhost:8000/api/Typecat',
        (Typecat))
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
              this.alert.msg = "Typecat ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchTypecat();
        });
      } else {
        this.$http.put("http://localhost:8000/api/Typecat/" + Typecat.id,
        (Typecat))
        .then(() => {
          this.fetchTypecat();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Typecat modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },

    updateTypecat(Typecat) {
      this.edit = true;
      this.Typecat = Typecat;
    },

    searchDOC(search) {
      this.search = search;
      this.fetchTypecat();
    },
    
    initModal() {
      this.Typecat = {};
      this.showModal("TypecatModal");
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
