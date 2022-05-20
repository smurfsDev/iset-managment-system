<template>
  <div>
    <formMateriel
      :edit="edit"
      :err="err"
      @fetchMateriel="fetchMateriel"
      :oldMateriel="Materiel"
    />
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
            <b-row class="text-center">
              <b-col cols="8">
                <button
                  type="button"
                  class="btn btn-primary mx-1 float-start"
                  data-bs-toggle="modal"
                  @click="initModal()"
                  data-bs-target="#MaterielModal"
                >
                  Nouvelle Materiel
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
          <showMateriels
            @deleteMateriel="deleteMateriel"
            :Materiels="Materiels"
            @fetchMateriel="fetchMateriel"
            @updateMateriel="updateMateriel"
            :pagination="pagination"
          />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showMateriels from "./show.vue";
import formMateriel from "./form.vue";
// import search from '../search.vue';

export default {
  components: {
    showMateriels,
    formMateriel,
    //     search
  },
  data() {
    return {
      Materiels: [],
      Materiel: {},
      pagination: {},
      edit: false,
      search: "",
      show: true,
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      err: "",
    };
  },
  created() {
    // console.log(typeof $);
    document.title = "Materiel";
    this.fetchMateriel();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Materiel ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Materiel modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchMateriel(
      page_url = "http://127.0.0.1:8000/api/m/",
      alert = {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      }
    ) {
      let vm = this;
      this.$http.get(page_url).then((res) => {
        this.Materiels = res.data.data.data;
        this.show = false;
        this.edit = false;
        this.alert = alert;
        vm.makePagination(res.data.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/m?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteMateriel(id) {
      if (confirm("Delete document " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/m/" + id).then(() => {
          this.fetchMateriel();
          this.alert.variant = "danger";
          this.alert.msg = "Materiel suprimée avec succès";
          this.alert.dismissCountDown = 5;
        });
      }
    },
    resetModal1() {
      this.document = {};
    },

    updateMateriel(Materiel) {
      this.edit = true;
      this.Materiel = Materiel;
    },
    searchEmploye(search) {
      this.search = search;
      this.fetchMateriel();
    },
    initModal() {
      this.edit = false;
      this.Materiel = {};
      this.showModal("MaterielModal");
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
