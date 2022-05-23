<template>
  <div>
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div class="pt-4 mt-4" v-if="!show">
          <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <showChefDepartements @acceptChefDepartement="acceptChefDepartement" :ChefDepartements="ChefDepartments"
            @fetchChefDepartement="fetchChefDepartments" @declineChefDepartement="declineChefDepartement" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showChefDepartements from "./show.vue";

export default {
  components: {
    showChefDepartements,
  },
  data() {
    return {
      ChefDepartments: [],
      pagination: {},
      show: true,
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
    };
  },
  created() {
    document.title = "Chef Departments";
    this.fetchChefDepartments();
  },
  methods: {
    fetchChefDepartments(page_url = "http://127.0.0.1:8000/api/cd/") {
      let vm = this;
      this.$http.get(page_url)
      .then((res) => {
        this.ChefDepartments = res.data.data;
        this.show = false;
        vm.makePagination(res.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/cd?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    acceptChefDepartement(id) {
      this.$http.post("http://localhost:8000/api/cd/accept/" + id)
      .then(() => {
        this.fetchChefDepartments();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "ChefDepartement acceptée avec succès";
        this.alert.dismissCountDown = 5;
      }).catch((err) => {
        this.alert.variant = "danger";
        this.alert.msg = err.response.data.msg;
        this.alert.dismissCountDown = 5;
      });
    },
    declineChefDepartement(id) {
      this.$http.post("http://localhost:8000/api/cd/refuse/" + id)
      .then(() => {
        this.fetchChefDepartments();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "ChefDepartement non acceptée avec succès";
        this.alert.dismissCountDown = 5;
      });
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
