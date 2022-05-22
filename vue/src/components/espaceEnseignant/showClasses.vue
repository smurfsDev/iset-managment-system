<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Classes.length == 0"
    >
      <h3>il y'a aucune Classe</h3>
    </div>
    <b-card class="my-2" v-for="Classe in Classes" :key="Classe.id">
      <md-tabs style="height: auto;!important">
        
        <md-tab
          id="tab-home"
          style="height: auto;!important"
          md-label="Classe"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                
              </b-row>
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
          id="tab"
          style="height: auto;!important"
          md-label="Matiere"
        >
       
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                
              </b-row>
              <b-row v-for="m in Classe.matiere" :key="m.id">
                <b-col>
                  
                  Matiere : {{ m.nom }} 
                  Coefficient: {{ m.coefficient}}
                  Semestre: {{m.semestre}}
                </b-col>
                
              </b-row>
             
            </b-row>
           
          </div>
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
      
      this.$router.push({ name:"consulterMatiere", params:{
            id: id,
          }});
    }
  },
};
</script>

<style></style>
