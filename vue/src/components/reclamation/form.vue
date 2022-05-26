<template>
  <div
    class="modal fade"
    id="ReclamationModal"
    tabindex="-1"
    aria-labelledby="ReclamationModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="ReclamationModalLabel">Faire une Reclamation</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('ReclamationModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addReclamation">
          <div class="modal-body">
            <div class="form-group mb-2">

              <label>Titre:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Titre"
                v-model="oldReclamation.titre"
                required="required"
              />

              <label>Description:</label>
              <input
             type="text"
              class="border-0 dcc form-control"
              placeholder="Description"
              v-model="oldReclamation.description"
              required="required"
             />

               <label>Type:</label>
               <div class="form-group">
                <select name="type_cats_id" v-model="oldReclamation.type_cats_id">
                  <option
                    v-for="type in types"
                    :key="type.id"
                    :value="type.id"
                  >
                    {{ type.title }}

                  </option>
                </select>
              </div>  
          
              
            
        <label>Image:</label>
              <input
                type="file"
                class="form-control"
                name="backgroundImage"
                @change="convert64"
                ref="file"
                required
                accept="image/*"
              />
              

            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('ReclamationModal')"
            >
              Fermer
            </button>
            <button type="submit" class="btn btn-primary">Sauvegarder</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    oldReclamation: Object,
    edit: Boolean,
  },
 data() {
    return {
      types: [],
    };
  },
  emits: ["addReclamation"],
  mounted() {
        this.fetchTypeCat();

  },
  methods: {
    addReclamation() {
      this.$emit("addReclamation", this.oldReclamation);
      this.resetModal1();
      this.hideModal("ReclamationModal");
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldReclamation.file = reader.result;
      };
      reader.readAsDataURL(file);
    },
    resetModal1() {
      $(".dcc").val("");
    },
     fetchTypeCat() {
      this.$http.get("http://localhost:8000/api/reclamationCat/all").then((response) => {
        this.types = response.data;
      });
    },

  },
};

</script>

<style>
</style>
