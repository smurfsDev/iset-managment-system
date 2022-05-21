<template>
  <div>
    
    <formMatiere @addMatiere="addMatiere" :oldMatiere="Matiere" :Enseignant="Enseignant" />
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
              <b-col cols="8">
                <button type="button" class="btn btn-primary mx-1 float-start" data-bs-toggle="modal"
                  @click="initModal()" data-bs-target="#ClasseModal">
                  Nouvelle Matiere
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
          <showMatieres @deleteMatiere="deleteMatiere" :Matieres="Matieres"
            @fetchMatiere="fetchMatiere" @updateMatiere="updateMatiere" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showMatieres from "./show.vue";
import formMatiere from "./form.vue";
// import search from '../search.vue';

export default {
  components: {
    showMatieres,
    formMatiere,
    //     search
  },
  data() {
    return {
      Matiere: {},
      Matieres: [],
      Enseignant:[],
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
      idC:null,
    };
  },
  created() {
    // console.log(typeof $);
   
    this.idC = this.$route.params.id;
    if (this.idC==null){
      this.$router.push({ name:"GererClasse"});
    }
    document.title = "Matiere";
    this.fetchMatiere();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Matiere ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Matiere modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchMatiere(
      page_url = "http://127.0.0.1:8000/api/matiere/"+this.idC
    ) {
      let vm = this;

      this.$http.get(page_url)
      .then((res)=> {
       
        this.Matieres = res.data.data.data;
        console.log(this.Matieres)
        
        // this.$http.get("http://localhost:8000/api/enseignant/approuved")
        // .then((res)=> {
        //   console.log(res.data)
        //   this.Matieres.Enseignant = res.data
          
        //   console.log(this.Matieres)
        // })
        this.show = false;
        vm.makePagination(res.data.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/matiere?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteMatiere(id) {
      if (confirm("Delete Matiere " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/matiere/delete/" + id)
        .then(() => {
          this.fetchMatiere();
            this.alert.variant = "danger";
            this.alert.msg = "Matiere suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    resetModal1() {
      this.Matiere = {};
    },
    addMatiere(Matiere, idEns) {
      
      this.show = true;
    
      Matiere.idEnseignant = idEns;
      
      if (!this.edit) {
       this.$http.post('http://localhost:8000/api/matiere/create/'+this.idC,
        (Matiere))
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
              this.alert.msg = "Matiere ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchMatiere();
        });
      } else {
        this.$http.put("http://localhost:8000/api/matiere/update/" + Matiere.id,
        (Matiere))
        .then(() => {
          this.fetchMatiere();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Matiere modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    updateMatiere(Matiere) {
      this.edit = true;
      this.Matiere = Matiere;
    },
    searchEmploye(search) {
      this.search = search;
      this.fetchMatiere();
    },
    initModal() {
      this.Matiere = {};
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
