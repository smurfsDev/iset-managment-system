<template>
  <div>
    <formDemandeDocument @addDemandeDocument="addDemandeDocument" :oldDemandeDocument="DemandeDocument" />
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
              <b-col cols="8">
                <button 
                v-if="!($store.getters.isAdmin)"
                type="button" class="btn btn-primary mx-1 float-start" data-bs-toggle="modal"
                  @click="initModal()" data-bs-target="#DemandeDocumentModal">
                  Nouvelle Demande Document
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
          <showDemandeDocuments @setReponse="setReponse" @deleteDemandeDocument="deleteDemandeDocument" :DemandeDocuments="DemandeDocuments"
            @fetchDemandeDocument="fetchDemandeDocument" @updateDemandeDocument="updateDemandeDocument" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showDemandeDocuments from "./show.vue";
import formDemandeDocument from "./form.vue";

export default {
  components: {
    showDemandeDocuments,
    formDemandeDocument,

  },
  data() {
    return {
      DemandeDocument: {},
      DemandeDocuments: [],
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
    
    window.title = "DemandeDocument";
    this.fetchDemandeDocument();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "DemandeDocument ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "DemandeDocument modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },

  methods: {
    fetchDemandeDocument(
      page_url = "http://127.0.0.1:8000/api/DemandeDocument/"
    ) {
      let vm = this;
      this.$http.get(page_url)
      .then((res)=> {
        this.DemandeDocuments = res.data.data;
        this.show = false;
        vm.makePagination(res.data);
      })
      .finally(()=> {
        this.show = false;
      });
    },

    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/DemandeDocument?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },

    deleteDemandeDocument(id) {
      if (confirm("Delete DemandeDocument " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/DemandeDocument/" + id)
        .then(() => {
          this.fetchDemandeDocument();
            this.alert.variant = "danger";
            this.alert.msg = "DemandeDocument suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },

    resetModal1() {
      this.DemandeDocument = {};
    },

    addDemandeDocument(DemandeDocument) {
      
      this.show = true;
      if (!this.edit) {
       this.$http.post('http://localhost:8000/api/DemandeDocument',
        (DemandeDocument))
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
              this.alert.msg = "DemandeDocument ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchDemandeDocument();
        });
      } else {
        this.$http.put("http://localhost:8000/api/DemandeDocument/" + DemandeDocument.id,
        (DemandeDocument))
        .then(() => {
          this.fetchDemandeDocument();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "DemandeDocument modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    updateDemandeDocument(DemandeDocument) {
      this.edit = true;
      this.DemandeDocument = DemandeDocument;
    },
    
    initModal() {
      this.DemandeDocument = {};
      this.showModal("DemandeDocumentModal");
    },
    //
    setReponse(id,reponse) {
      this.$http.put("http://127.0.0.1:8000/api/DemandeDocument/reponse/" + id,{reponse:reponse})
        .then((res) => res.data)
        .then(() => {
          this.fetchDemandeDocument();
            this.alert.variant = "warning";
            this.alert.msg = "Reponse envoyée avec succès";
            this.alert.dismissCountDown = 5;
        })
        .catch((err) => console.log(err));
    },
    //
  },
};
</script>

<style>
.btun {
  color: white !important;
  background-color: #d32f2f !important;
}
</style>
