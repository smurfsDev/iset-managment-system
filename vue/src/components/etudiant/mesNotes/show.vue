<template>
  <div>
    <div class="card card-body my-5 py-5 text-center" v-if="Notes.length == 0">
      <h3>il y'a aucune Note</h3>
    </div>
    <b-card class="my-2">
      <md-tabs style="height: auto;!important">
        <md-tab id="tab-home" style="height: auto;!important" md-label="Note">
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row v-for="Note in Notes" :key="Note.id">
                <label :for="'for' + Note.id" class="col-sm-4 col-form-label">
                  Matiere : {{ Note.matiere.nom }} Coefficient :
                  {{ Note.matiere.coefficient }}
                </label>

                <div class="col-sm-8 my-2 row">
                  <div role="group" class="row" style="align-items: center">
                    <div class="col-md-9">
                      <input
                        readonly
                        :id="'for' + Note.id"
                        class="form-control"
                        type="number"
                        style="
                          background-color: rgb(236, 239, 241);
                          border: 0px !important;
                        "
                        min="0"
                        max="20"
                        v-model="Note.note"
                      />
                    </div>
                  </div>
                </div>
              </b-row>
            </b-row>
            <h1>Moyenne (NonFinal) : {{ myMoy }}</h1>
            <button @click="generatePDF()" class="btn btn-info">
              Telechargers relevé
            </button>
            <button
              type="button"
              class="btn btn-primary mx-1"
              data-bs-toggle="modal"
              @click="showModal('demandeModal')"
              data-bs-target="#demandeModal"
            >
              Voir relevé
            </button>
          </div>
        </md-tab>
      </md-tabs>
    </b-card>

    <div
      style="width: 100%; margin-left: 10%"
      class="modal fade"
      id="demandeModal"
      tabindex="-1"
      aria-labelledby="demandeModalLabel"
      aria-hidden="true"
    >
      <div class="modal-xl">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="demandeModalLabel">Relevé de note</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              @click="hideModal('demandeModal')"
            ></button>
          </div>
          <div class="modal-body">
            <printable
              :userName="$store.getters.StateUser.name"
              :Clazz="$store.getters.StateUser.name"
              :notes="Notes"
              :numBul="
                parseInt(Math.random(1, 9999)) +
                '' +
                $store.getters.token.substring(0, 10)
              "
            />
          </div>
        </div>
      </div>
    </div>

    <vue-html2pdf
      :show-layout="false"
      :float-layout="true"
      :enable-download="true"
      :paginate-elements-by-height="1400"
      :filename="$store.getters.StateUser.name"
      :pdf-quality="2"
      :manual-pagination="false"
      pdf-format="a4"
      :pdf-margin="10"
      pdf-orientation="portrait"
      pdf-content-width="800px"
      ref="html2Pdf"
    >
      <section slot="pdf-content">
        <printable
          :userName="$store.getters.StateUser.name"
          :Clazz="$store.getters.StateUser.name"
          :notes="Notes"
          :numBul="numBul"
        />
      </section>
    </vue-html2pdf>
  </div>
</template>

<script>
import printable from "./bultin.vue";
import VueHtml2pdf from "vue-html2pdf";
export default {
  props: {
    Notes: Array,
    pagination: Object,
  },
  data() {
    return {
      numBul:
        parseInt(Math.random(1, 9999)) +
        "" +
        this.$store.getters.token.substring(0, 10),
    };
  },
  components: {
    printable,
    VueHtml2pdf,
  },
  emits: ["fetchNote"],
  methods: {
    fetchNote(url) {
      this.$emit("fetchNote", url);
    },
    generatePDF() {
      this.$http.post("http://localhost:8000/api/bultin", {
        student_id: this.$store.getters.StateUser.id,
        moyenne: this.myMoy,
        numero_bultin: this.numBul,
      });
      this.$refs.html2Pdf.generatePdf();
    },
  },
  computed: {
    myMoy() {
      let somme = 0;
      let nb = 0;
      for (let i = 0; i < this.Notes.length; i++) {
        somme += this.Notes[i].note * this.Notes[i].matiere.coefficient;
        nb += this.Notes[i].matiere.coefficient;
      }
      return (somme / nb).toFixed(2);
    },
  },
};
</script>

<style></style>
