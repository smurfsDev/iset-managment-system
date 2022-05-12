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
          <showStudents @acceptStudent="acceptStudent" :Students="Students"
            @fetchStudent="fetchStudents" @declineStudent="declineStudent" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showStudents from "./show.vue";

export default {
  components: {
    showStudents,
  },
  data() {
    return {
      Students: [],
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
    this.fetchStudents();
  },
  methods: {
    fetchStudents(page_url = "http://127.0.0.1:8000/api/s/") {
      let vm = this;
      this.$http.get(page_url)
      .then((res) => {
        this.Students = res.data.data;
        this.show = false;
        vm.makePagination(res.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/s?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    acceptStudent(id) {
      this.$http.post("http://localhost:8000/api/s/accept/" + id)
      .then(() => {
        this.fetchStudents();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "Student acceptée avec succès";
        this.alert.dismissCountDown = 5;
      });
    },
    declineStudent(id) {
      this.$http.post("http://localhost:8000/api/s/refuse/" + id)
      .then(() => {
        this.fetchStudents();
        this.edit = false;
        this.alert.variant = "warning";
        this.alert.msg = "Student non acceptée avec succès";
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
