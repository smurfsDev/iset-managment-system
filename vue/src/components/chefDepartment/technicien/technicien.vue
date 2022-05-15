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
          <showTechnicien @acceptTechnicien="acceptTechnicien" :Techniciens="Technicien"
            @fetchTechnicien="fetchTechnicien" @declineTechnicien="declineTechnicien" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showTechnicien from "./show.vue";

export default {
  components: {
    showTechnicien,
  },
  data() {
    return {
      Technicien: [],
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
    this.fetchTechnicien();
  },
  methods: {
    fetchTechnicien(page_url = "http://127.0.0.1:8000/api/technicien/") {
      let vm = this;
      this.$http.get(page_url)
      .then((res) => {
        this.Technicien = res.data.data;
        this.show = false;
        vm.makePagination(res.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/technicien?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    acceptTechnicien(id) {
      this.$http.post("http://localhost:8000/api/technicien/accept/" + id)
      .then(() => {
        this.fetchTechnicien();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "Technicien acceptée avec succès";
        this.alert.dismissCountDown = 5;
      });
    },
    declineTechnicien(id) {
      this.$http.post("http://localhost:8000/api/technicien/refuse/" + id)
      .then(() => {
        this.fetchTechnicien();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "Technicien non acceptée avec succès";
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
