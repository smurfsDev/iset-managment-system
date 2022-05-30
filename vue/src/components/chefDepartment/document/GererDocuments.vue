<template>
  <div>
    <formDocument @addDocument="addDocument" :oldDocument="Document" />
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay
          v-if="show"
          :show="show"
          class="d-inline-block"
          style="height: 500px; width: 100%"
        ></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row>
              <b-col>
                <b-form-group
                  label="Search"
                  label-for="search"
                  label-cols="2"
                  label-align="right"
                >
                  <b-form-input
                    id="search"
                    v-model="search"
                    placeholder="Search"
                    @keyup.enter="fetchDocument()"
                  />
                  Categorie
                  <b-form-select v-model="cat" @change="fetchDocument()">
                    <option selected="selected" value="all">Tous</option>
                    <option
                      v-for="option in categories"
                      :value="option.id"
                      :key="option.id"
                    >
                      {{ option.title }}
                    </option>
                  </b-form-select>
                  Class
                  <b-form-select v-model="cla" @change="fetchDocument()">
                    <option selected value="all">Tous</option>
                    <option
                      v-for="option in classes"
                      :value="option.id"
                      :key="option.id"
                    >
                      {{ option.nom }}
                    </option>
                  </b-form-select>
                  <button class="btn btn-info" @click="fetchDocument()">
                    Search
                  </button>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row class="text-center">
              <b-col cols="8">
                <button
                  v-if="
                    $store.getters.isChefDepartement || $store.getters.isAdmin  
                  "
                  type="button"
                  class="btn btn-primary mx-1 float-start"
                  data-bs-toggle="modal"
                  @click="initModal()"
                  data-bs-target="#DocumentModal"
                >
                  Ajouté Document
                </button>
              </b-col>
              <b-col></b-col>
            </b-row>
          </b-container>
          <b-alert
            class="mt-4"
            :show="alert.dismissCountDown"
            dismissible
            :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0"
          >
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <showDocuments
            @deleteDocument="deleteDocument"
            :Documents="Documents"
            @fetchDocument="fetchDocument"
            @updateDocument="updateDocument"
            :pagination="pagination"
          />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showDocuments from "./show.vue";
import formDocument from "./form.vue";

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
      show: true,
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      myid: 1,
      categories: [],
      classes: [],
      search: "",
      cat: "all",
      cla: "all",
    };
  },
  created() {
    document.title = "Document";
    this.fetchDocument();
    this.fetchCategories();
    this.fetchClasses();

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
    fetchDocument(link = "http://127.0.0.1:8000/api/Document/search") {
      let vm = this;
      this.$http
        .post(link, {
          search: this.search,
          categorie: this.cat,
          class: this.cla,
        })
        .then((res) => {
          this.Documents = [];
          this.Documents = res.data.data;
          this.show = false;
          vm.makePagination(res.data);
        })
        .catch(() => {
          this.Documents = [];
        })
        .finally(() => {
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
        this.$http
          .delete("http://localhost:8000/api/Document/" + id)
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
        this.$http
          .post("http://localhost:8000/api/Document", Document)
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
        this.$http
          .put("http://localhost:8000/api/Document/" + Document.id, Document)
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
    fetchCategories() {
      let page_url = "http://127.0.0.1:8000/api/Categorie/all";
      this.$http
        .get(page_url)
        .then((res) => {
          this.categories = res.data;
        })
        .finally(() => {
          this.show = false;
        });
    },

    fetchClasses() {
      let page_url = "http://127.0.0.1:8000/api/Classe/all";
      this.$http
        .get(page_url)
        .then((res) => {
          this.classes = res.data;
        })
        .finally(() => {
          this.show = false;
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
