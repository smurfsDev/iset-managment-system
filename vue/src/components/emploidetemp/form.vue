<template>
  <div
    class="modal fade"
    id="EmploideTempModal"
    tabindex="-1"
    aria-labelledby="EmploideTempModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="EmploideTempModalLabel">
            Ajouté une Emploi de Temp
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('EmploideTempModal')"
          ></button>
        </div>
        <form class="mb-3" @submit.prevent="addEmploideTemp">
          <div class="modal-body">
            <div class="form-group mb-2">
              <label>Titre:</label>
              <input
                type="text"
                class="border-0 dcc form-control"
                placeholder="Titre"
                v-model="oldEmploideTemp.titre"
                required="required"
              />

              <label>class:</label>
              <div class="form-group">
                <select name="classe" v-model="oldEmploideTemp.classe">
                  <option
                    v-for="classe in classes"
                    :key="classe.id"
                    :value="classe.id"
                  >
                    {{ classe.abreviation }}
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
              @click="hideModal('EmploideTempModal')"
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
    oldEmploideTemp: Object,
    edit: Boolean,
  },
  data() {
    return {
      classes: [],
    };
  },
  emits: ["addEmploideTemp"],
  mounted() {
    this.fetchClasses();
  },
  methods: {
    addEmploideTemp() {
      this.$emit("addEmploideTemp", this.oldEmploideTemp);
      this.resetModal1();
      this.hideModal("EmploideTempModal");
    },
    convert64(e) {
      var file = e.target.files[0];
      this.srcImage = file;
      var reader = new FileReader();
      reader.onloadend = () => {
        this.oldEmploideTemp.file = reader.result;
      };
      reader.readAsDataURL(file);
    },
    resetModal1() {
      $(".dcc").val("");
    },
    fetchClasses() {
      let page_url = "http://127.0.0.1:8000/api/classe";
      this.$http
        .get(page_url)
        .then((res) => {
          this.classes = res.data.data;
        })
        .finally(() => {
          this.show = false;
        });
    },
  },
};
</script>

<style>
</style>
