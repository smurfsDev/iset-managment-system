<template>
  <div>
    <formDocument @addDocument="addDocument" :oldDocument="Document" />
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
                  @click="initModal()" data-bs-target="#DocumentModal">
                  Nouvelle Document
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
          <showDocuments @deleteDocument="deleteDocument" :Documents="Documents"
            @fetchDocument="fetchDocument" @updateDocument="updateDocument" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showDocuments from "./show.vue";
import formDocument from "./form.vue";
// import search from '../search.vue';

export default {
  components: {
    showDocuments,
    formDocument,
  
  },
  data() {
    return {
      Document: {},
      Documents: [],
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
    document.title = "Document";
    this.fetchDocument();

    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Document ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Document modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchDocument(
      page_url = "http://127.0.0.1:8000/api/Document/"
    ) {
      let vm = this;
      this.$http.get(page_url)
      .then((res)=> {
        this.Documents = res.data.data;
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
          "http://localhost:8000/api/Document?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteDocument(id) {
      if (confirm("Delete Document " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/Document/" + id)
        .then(() => {
          this.fetchDocument();
            this.alert.variant = "danger";
            this.alert.msg = "Document suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    resetModal1() {
      this.Document = {};
    },
    
    addDocument(Document) {
      
      this.show = true;
      if (!this.edit) {
       this.$http.post('http://localhost:8000/api/Document',
        (Document))
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
              this.alert.msg = "Document ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchDocument();
        });
      } else {
        this.$http.put("http://localhost:8000/api/Document/" + Document.id,
        (Document))
        .then(() => {
          this.fetchDocument();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Document modifié avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    updateDocument(Document) {
      this.edit = true;
      this.Document = Document;
    },
    searchDOC(search) {
      this.search = search;
      this.fetchDocument();
    },
    initModal() {
      this.Document = {};
      this.showModal("DocumentModal");
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
