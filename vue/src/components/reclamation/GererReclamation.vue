<template>
  <div>
    <formReclamation
      @addReclamation="addReclamation"
      :oldReclamation="Reclamation"
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
                  v-if="!$store.getters.isAdmin"
                  type="button"
                  class="btn btn-primary mx-1 float-start"
                  data-bs-toggle="modal"
                  @click="initModal()"
                  data-bs-target="#ReclamationModal"
                >
                  Nouvelle Reclamation
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
          <showReclamations
            @setReponse="setReponse"
            @deleteReclamation="deleteReclamation"
            :Reclamations="Reclamations"
            @fetchReclamation="fetchReclamation"
            @updateReclamation="updateReclamation"
            :pagination="pagination"
          />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showReclamations from "./show.vue";
import formReclamation from "./form.vue";

export default {
  components: {
    showReclamations,
    formReclamation,
 
  },
  data() {
    return {
      Reclamation: {},
      Reclamations: [],
      pagination: {},
      edit: false,
      search: "",
      show: true,
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      myid: 1,
    };
  },

  created() {
    window.title = "Reclamation";
    this.fetchReclamation();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Reclamation ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Reclamation modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },

  methods: {
    fetchReclamation(page_url = "http://127.0.0.1:8000/api/reclamation/") {
      let vm = this;
      this.$http
        .get(page_url)
        .then((res) => {
          this.Reclamations = res.data.data;
          this.show = false;
          vm.makePagination(res.data);
        })
        .finally(() => {
          this.show = false;
        });
    },

    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/reclamation?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },

    deleteReclamation(id) {
      if (confirm("Supprimer Reclamation " + id)) {
        this.show = true;
        this.$http
          .delete("http://localhost:8000/api/reclamation/" + id)
          .then(() => {
            this.fetchReclamation();
            this.alert.variant = "danger";
            this.alert.msg = "Reclamation suprimée avec succès";
            this.alert.dismissCountDown = 5;
          });
      }
    },

    resetModal1() {
      this.Reclamation = {};
    },

    addReclamation(Reclamation) {
      this.show = true;
      if (!this.edit) {
        this.$http
          .post("http://localhost:8000/api/reclamation", Reclamation)
          .then((data) => {
            data = data.data;
            if (data.success == false) {
              this.alert.variant = "danger";
              let err = "";
              for (const property in data.data) {
                err += data.data[property] + "\n\n";
              }
              console.log(err);
              this.alert.msg = `
                            ${err}`;
              this.alert.dismissCountDown = 5;
            } else {
              this.alert.variant = "success";
              this.alert.msg = "Reclamation ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchReclamation();
          });
      } else {
        this.$http
          .put(
            "http://localhost:8000/api/reclamation/" + Reclamation.id,
            Reclamation
          )
          .then(() => {
            this.fetchReclamation();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Reclamation modifié avec succès";
            this.alert.dismissCountDown = 5;
          });
      }
    },
    updateReclamation(Reclamation) {
      this.edit = true;
      this.Reclamation = Reclamation;
    },

    initModal() {
      this.Reclamation = {};
      this.showModal("ReclamationModal");
    },
    setReponse(id, reponse) {
      this.$http
        .put("http://127.0.0.1:8000/api/reclamation/reponse/" + id, {
          reponse: reponse,
        })
        .then((res) => res.data)
        .then(() => {
          this.fetchReclamation();
          this.alert.variant = "warning";
          this.alert.msg = "Reponse envoyée avec succès";
          this.alert.dismissCountDown = 5;
        })
        .catch((err) => console.log(err));
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
