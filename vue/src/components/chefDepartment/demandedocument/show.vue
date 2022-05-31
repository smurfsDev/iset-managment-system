<template>
  <div>
    <formDocument @fetchThem="fetchDemandeDocument" :idDoc="idDoc" />
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="DemandeDocuments.length == 0"
    >
      <h3>il y'a aucune Demande Document</h3>
    </div>
    <b-card
      class="my-2"
      v-for="DemandeDocument in DemandeDocuments"
      :key="DemandeDocument.id"
    >
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'success',
          title: '',
          placement: 'topright',
        }"
        v-if="DemandeDocument.file != null"
        icon="check-square"
        scale="2"
        variant="success"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'danger',
          title: '',
          placement: 'topright',
        }"
        v-if="DemandeDocument.file == null"
        icon="x-square"
        scale="2"
        variant="danger"
      ></b-icon>
      
      <md-tabs style="height: auto;!important">
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="Demande   Document"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col> Titre :{{ DemandeDocument.titre }} </b-col>
              </b-row>
              <b-row>
                <b-col> Description : {{ DemandeDocument.description }} </b-col>
              </b-row>

              <b-row>
                <b-col> Categorie : {{ DemandeDocument.categorie.title}} </b-col>
              </b-row>
              <div
                v-if="
                  $store.getters.isAdmin || $store.getters.isChefDepartement
                "
                role="group"
                class="row"
                style="align-items: center"
              >
                <div class="col-md-4 row">
                  <strong>Reponse :</strong>
                </div>
                <div class="col-md-5">
                  <textarea
                    v-model="DemandeDocument.reponse"
                    class="form-control"
                    name="reponse"
                    style="
                      background-color: rgb(236, 239, 241);
                      border: 0px !important;
                    "
                  />
                </div>
                <div class="col-md-3">
                  <div class="input-group-append">
                    <b-button
                      type="button"
                      variant="warning"
                      v-if="DemandeDocument.reponse != null"
                      @click="
                        setReponse(DemandeDocument.id, DemandeDocument.reponse)
                      "
                      >Envoyer</b-button
                    >
                    <b-button
                      type="button"
                      variant="success"
                      v-else
                      @click="
                        setReponse(DemandeDocument.id, DemandeDocument.reponse)
                      "
                      >Ajouter Reponse</b-button
                    >
                  </div>
                </div>
              </div>
              <b-col v-else class="text-danger">
                Reponse :
                {{
                  DemandeDocument.reponse
                    ? DemandeDocument.reponse
                    : "Pas de reponse"
                }}
              </b-col>
            </b-row>

            <b-button
              variant="danger"
              v-if="$store.getters.isStudent"
              v-on:click="Delete(DemandeDocument.id)"
            >
              Supprimer</b-button
            >
            <b-button
              :class="[{ disabled: DemandeDocument.file }]"
              v-if="!$store.getters.isAdmin"
              variant="warning"
              v-on:click="Update(DemandeDocument)"
            >
              Modifier</b-button
            >
           <b-button
               v-if="$store.getters.isAdmin || $store.getters.isChefDepartement"
              variant="success"
              v-on:click="AddFile(DemandeDocument.id)"
            >
              Fichier</b-button
            >
          </div>
        </md-tab>
        <md-tab
          id="tab-doc"
          style="height: auto;!important"
          md-label="Document"
        >
          <div class="bv-example-row text-center">
            <div v-if="DemandeDocument.file==null">Aucune document disponible</div>
           <pdfs :ref="'pdfRef'+DemandeDocument.titre" v-if="checkType(DemandeDocument.file)" :source="makeFile(DemandeDocument.file)" />
                  	<img v-if="checkType(DemandeDocument.file)==2" :src="(DemandeDocument.file)" />
           </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="DemandeDocuments.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchDemandeDocument(pagination.prev_page_url)"
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
            @click="fetchDemandeDocument(pagination.next_page_url)"
            :class="[
              !pagination.next_page_url ? 'disabled' : 'link-primary btun',
            ]"
            class="btun btn page-link"
            >Suivant</a
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import pdfs from 'vue-pdf-embed/dist/vue2-pdf-embed'
import formDocument from "./formDocument.vue";
export default {
  props: {
    DemandeDocuments: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
      currPage: 1,
      idDoc: 0,
    };
  },
  components: {
    formDocument,
    pdfs,

  },

  emits: [
    "deleteDemandeDocument",
    "updateDemandeDocument",
    "fetchDemandeDocument",
    "setReponse",
  ],
  methods: {
    Delete(id) {
      this.$emit("deleteDemandeDocument", id);
    },

    Update(DemandeDocument) {
      this.$emit("updateDemandeDocument", DemandeDocument);
      this.showModal("DemandeDocumentModal");
    },

    fetchDemandeDocument(url) {
      this.$emit("fetchDemandeDocument", url);
    },
    makeFile(data) {
      return data;
    },
    setReponse(id, reponse) {
      this.$emit("setReponse", id, reponse);
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldDemande.logo = reader.result;
      };
      reader.readAsDataURL(file);
    },
    AddFile(id) {
      this.idDoc=id;
      this.showModal("formDocumentSend");
    },

    checkType(data){
      if(data!=null){
        if(data.indexOf('data:application/pdf') != -1){
          return 1;
        }else if(data.indexOf('data:image') != -1){
          return 2;
        }
      }
    }
  },
};
</script>

<style>
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
