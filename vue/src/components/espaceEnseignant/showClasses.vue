<template>
  <div class="w-100">
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Classes.length == 0"
    >
      <h3>il y'a aucune Classe</h3>
    </div>

    <b-card class="my-2" v-for="Classe in Classes" :key="Classe.id">
      <md-tabs class="text-danger" style="height: auto;!important">
        <md-tab
          class="text-dark"
          id="tab-home"
          style="height: auto;!important"
          md-label="Classe"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Classe : {{ Classe.nom }} 
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  Abbreveation classe : {{ Classe.abreviation }} 
                </b-col>
              </b-row>
            </b-row>
           
          </div>
        </md-tab>
        <md-tab
          class="text-dark"
          id="tab-matieres"
          style="height: auto;!important"
          md-label="Matieres"
        >

          <md-tabs id="mdtbs" style="height: auto;!important">
            <md-tab
              v-for="matiere in Classe.matieres"
              :key="matiere.id"
              :id="'tab-matieres' + matiere.id"
              style="height: auto;!important"
              :md-label="matiere.nom"
            >
              <!-- input mark foreach student -->
              <div
                class="form-group row"
                v-for="note in matiere.notes"
                :key="note.id"
              >
                <label
                  :for="'for' + note.id"
                  class="col-sm-4 col-form-label"
                  >{{ note.student.name }}</label
                >

                <div class="col-sm-8 row">
                  <div role="group" class="row" style="align-items: center">
                    <div class="col-md-9">
                      <input
                        :id="'for' + note.id"
                        class="form-control"
                        type="number"
                        style="
                          background-color: rgb(236, 239, 241);
                          border: 0px !important;
                        "
                        min="0"
                        max="20"
                        v-model="note.note"
                      />
                    </div>
                    <div class="col-md-3">
                      <div class="input-group-append">
                        <b-button type="button" variant="success"
                          @click="setNote(matiere.id, note.student.id, note.note)"
                          >save</b-button
                        >
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </md-tab>
          </md-tabs>
        </md-tab>
      </md-tabs>
    </b-card>
    
    <nav class="row" v-if="Classes.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchClasse(pagination.prev_page_url)"
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
            @click="fetchClasse(pagination.next_page_url)"
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
export default {
  emits: ["alertIt"],
  props: {
    Classes: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
    };
  },

  emits: ["deleteClasse", "updateClasse", "fetchClasse"],
  methods: {
    Delete(id) {
      this.$emit("deleteClasse", id);
    },
    Update(Classe) {
      this.$emit("updateClasse", Classe);
      this.showModal("ClasseModal");
    },
    fetchClasse(url) {
      this.$emit("fetchClasse", url);
    },
    addmat(id) {
      this.$router.push({
        name: "consulterMatiere",
        params: {
          id: id,
        },
      });
    },
    setNote(idMat,idStudent,Note){
      this.$http.post(
        "http://localhost:8000/api/note/",{
          student_id: idStudent,
          matiere_id: idMat,
          note: Note
        }
      ).then(response => {
        this.alertIt({
          variant: "success",
          msg: "Note ajouté avec succès",
          dismissCountDown: 3,

        });
      });
    },
    fetchNote(idMat,idStudent){
      this.$http.get(
        "http://localhost:8000/api/note/"+idMat+"/"+idStudent
      ).then(response => {
        console.log(response.data.note);
        return response.data.note;
      });
    },
    alertIt(alert) {
      this.$emit("alertIt", alert);
    },
  },
};
</script>

<style></style>
