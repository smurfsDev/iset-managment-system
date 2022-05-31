<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Documents.length == 0"
    >
      <h3>Il y'a aucune Document</h3>
    </div>
    <b-card class="my-2" v-for="Document in Documents" :key="Document.id">
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="Document"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Title :<a :download="Document.nom" :href="Document.file">
                    {{ Document.nom }}
                  </a>
                </b-col>
              </b-row>
              <b-row v-if="Document.class">
                <b-col> Class : {{ Document.class.abreviation }} </b-col>
              </b-row>
              <b-row>
                <b-col> Categorie : {{ Document.categorie.title }} </b-col
                >
              </b-row>
            </b-row>
            <b-button
              variant="danger"
              v-if="$store.getters.isChefDepartement || $store.getters.isAdmin"
              :class="[
                {
                  disabled:
                    $store.getters.StateUser.id != Document.idResponsable,
                },
              ]"
              v-on:click="Delete(Document.id)"
            >
              Supprimer</b-button
            >
            <b-button
              v-if="$store.getters.isChefDepartement || $store.getters.isAdmin"
              :class="[
                {
                  disabled:
                    $store.getters.StateUser.id != Document.idResponsable,
                },
              ]"
              variant="warning"
              v-on:click="Update(Document)"
            >
              Modifier</b-button
            >
          </div>
        </md-tab>
        <md-tab
          id="tab-homea"
          style="height: auto;!important"
          md-label="Fichier"
        >
          <b-row>
            <b-col>
              <pdfs
                :ref="'pdfRef' + Document.name"
                v-if="checkType(Document.file)"
                :source="makeFile(Document.file)"
              />
              <img v-if="checkType(Document.file) == 2" :src="Document.file" />
            </b-col>
          </b-row>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="Documents.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchDocument(pagination.prev_page_url)"
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
            @click="fetchDocument(pagination.next_page_url)"
            :class="[
              !pagination.next_page_url ? 'disabled' : 'link-primary btun',
            ]"
            class="btun btn page-link"
            >Suivant</a
          >
        </li>
      </ul>
    </nav>
    <div class="form-group"></div>
  </div>
</template>

<script>
import pdfs from "vue-pdf-embed/dist/vue2-pdf-embed";

export default {
  components: {
    pdfs,
  },
  props: {
    Documents: Array,
    pagination: Object,
    oldDocument: Object,
  },

  mounted() {},
  data() {
    return {
      id: "",
      currPage: 1,
    };
  },
  emits: ["deleteDocument", "updateDocument", "fetchDocument"],
  methods: {
    Delete(id) {
      this.$emit("deleteDocument", id);
    },
    Update(Document) {
      this.$emit("updateDocument", Document);
      this.showModal("DocumentModal");
    },
    fetchDocument(url) {
      this.$emit("fetchDocument", url);
    },

    makeFile(data) {
      return data;
    },

    checkType(data) {
      if (data != null) {
        if (data.indexOf("data:application/pdf") != -1) {
          return 1;
        } else if (data.indexOf("data:image") != -1) {
          return 2;
        }
      }
    },
  },
};
</script>

<style>
.vue-pdf-embed > div {
  margin-bottom: 8px;
  box-shadow: 0 2px 8px 4px rgba(0, 0, 0, 0.1);
}

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
