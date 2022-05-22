<template>
  <div>
    <div class="card card-body my-5 py-5 text-center" v-if="Notes.length == 0">
      <h3>il y'a aucune Note</h3>
    </div>
    <b-card class="my-2" >
      <md-tabs style="height: auto;!important">
        <md-tab id="tab-home" style="height: auto;!important" md-label="Note">
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row v-for="Note in Notes" :key="Note.id">
                <label :for="'for' + Note.id" class="col-sm-4 col-form-label">
                Matiere :  {{Note.matiere.nom}} Coefficient : {{Note.matiere.coefficient}}
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
          <h1>
            Moyenne (NonFinal) : {{ myMoy }} 
          </h1>
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="Notes.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchNote(pagination.prev_page_url)"
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
            @click="fetchNote(pagination.next_page_url)"
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
  props: {
    Notes: Array,
    pagination: Object,
  },
  emits: ["fetchNote"],
  methods: {
    fetchNote(url) {
      this.$emit("fetchNote", url);
    },
    
  },
  computed: {
    myMoy() {
      let somme = 0;
      let nb = 0;
      for (let i = 0; i < this.Notes.length; i++) {
        somme += this.Notes[i].note*this.Notes[i].matiere.coefficient;
        nb+= this.Notes[i].matiere.coefficient;
      }
      console.log(somme,nb);
      // show only two decimal places
      return (somme/nb).toFixed(2);
    },
  },
};
</script>

<style></style>
