<template>
  <div>
    <formEmploideTemp
      @addEmploideTemp="addEmploideTemp"
      :oldEmploideTemp="EmploideTemp"
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
                  v-if="!$store.getters.ischefdepartement"
                  type="button"
                  class="btn btn-primary mx-1 float-start"
                  data-bs-toggle="modal"
                  @click="initModal()"
                  data-bs-target="#EmploideTempModal"
                >
                  Nouvelle Emploie de Temps
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
          <showEmploideTemps
       
            @deleteEmploideTemp="deleteEmploideTemp"
            :EmploideTemps="EmploideTemps"
            @fetchEmploideTemp="fetchEmploideTemp"
            @updateEmploideTemp="updateEmploideTemp"
            :pagination="pagination"
          />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showEmploideTemps from "./show.vue";
import formEmploideTemp from "./form.vue";

export default {
  components: {
    showEmploideTemps,
    formEmploideTemp,

  },
  data() {
    return {
      EmploideTemp: {},
      EmploideTemps: [],
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
  
    window.title = "EmploideTemp";
    this.fetchEmploideTemp();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "EmploideTemp ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "EmploideTemp modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },

  methods: {
    fetchEmploideTemp(page_url = "http://127.0.0.1:8000/api/EDT/") {
      let vm = this;
      this.$http
        .get(page_url)
        .then((res) => {
          this.EmploideTemps = res.data.data;
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
          "http://localhost:8000/api/EDT?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },

    deleteEmploideTemp(id) {
      if (confirm("Delete Emploi de Temp " + id)) {
        this.show = true;
        this.$http
          .delete("http://localhost:8000/api/EDT/" + id)
          .then(() => {
            this.fetchEmploideTemp();
            this.alert.variant = "danger";
            this.alert.msg = "Emploi de Temp suprimée avec succès";
            this.alert.dismissCountDown = 5;
          });
      }
    },

    resetModal1() {
      this.EmploideTemp = {};
    },

    addEmploideTemp(EmploideTemp) {
      this.show = true;
      if (!this.edit) {
        this.$http
          .post("http://localhost:8000/api/EDT", EmploideTemp)
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
              this.alert.msg = "Emploi de Temp ajouté avec succès";
              this.alert.dismissCountDown = 5;
            }
            this.fetchEmploideTemp();
          });
      } else {
        this.$http
          .put(
            "http://localhost:8000/api/EDT/" + EmploideTemp.id,
            EmploideTemp
          )
          .then(() => {
            this.fetchEmploideTemp();
            this.edit = false;
            this.alert.variant = "warning";
            this.alert.msg = "Emploi de Temp modifié avec succès";
            this.alert.dismissCountDown = 5;
          });
      }
    },
    updateEmploideTemp(EmploideTemp) {
      this.edit = true;
      this.EmploideTemp = EmploideTemp;
    },

    initModal() {
      this.EmploideTemp = {};
      this.showModal("EmploideTempModal");
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
