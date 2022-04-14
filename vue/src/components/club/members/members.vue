<template>
  <div class="content container">
    <div class="pt-3 pb-3 container-fluid">
      <b-overlay
        v-if="show"
        :show="show"
        class="d-inline-block"
        style="height: 500px; width: 100%"
      ></b-overlay>
      <div v-if="!show">
        <b-alert
          class="mt-4"
          :show="alert.dismissCountDown"
          dismissible
          :variant="alert.variant"
          @dismissed="alert.dismissCountDown = 0"
        >
          <p>{{ alert.msg }}</p>
        </b-alert>
        <showMember
          :members="members"
          :pagination="pagination"
          @fetchMember="fetchMembers"
          @deleteMember="deleteMember"
        />
      </div>
    </div>
  </div>
</template>

<script>
import showMember from "./show.vue";

export default {
  components: {
    showMember,
  },
  data() {
    return {
      members: [],
      pagination: {},
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
    this.fetchMembers();
  },
  methods: {
    fetchMembers(page_url = "http://127.0.0.1:8000/api/members/") {
      let vm = this;
      fetch(page_url, {
        method: "GET",
      })
        .then((res) => res.json())
        .then((res) => {
          this.members = res.data.data;
          this.show = false;
          vm.makePagination(res.data);
        })
        .catch((err) => console.log(err));
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/members?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteMember(id) {
      let headersi = new Headers();
      if (confirm("Delete document " + id)) {
        this.show = true;
        fetch("http://localhost:8000/api/members/" + id, {
          method: "delete",
          headers: headersi,
        })
          .then(() => {
            this.fetchMembers();
            this.alert.variant = "danger";
            this.alert.msg = "Member suprimée avec succès";
            this.alert.dismissCountDown = 5;
          })
          .then(() => {})
          .catch((err) => console.log(err));
      }
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
