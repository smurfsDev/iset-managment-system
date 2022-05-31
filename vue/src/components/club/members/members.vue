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
          @sendMail="sendMail"
          :members="members"
          :pagination="pagination"
          @fetchMember="fetchMembers"
          @deleteMember="deleteMember"
          @deleteMemberGroup="deleteMemberGroup"
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
      myid: 5,
    };
  },
  created() {
    this.fetchMembers();
  },
  methods: {
    fetchMembers(page_url = "http://127.0.0.1:8000/api/members/") {
      let vm = this;
      this.$http.get(page_url, {
        method: "GET",
      })
        .then((res) => res.data)
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
          "http://localhost:8000/api/members/" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteMember(id) {
      if (confirm("Supprimer document " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/members/" + id)
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
    deleteMemberGroup(id) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/members/" + id)
          .then(() => {
            this.fetchMembers();
            this.alert.variant = "danger";
            this.alert.msg = "Member suprimée avec succès";
            this.alert.dismissCountDown = 5;
          })
          .then(() => {})
          .catch((err) => console.log(err));
    },
    sendMail(id){
            this.$http.post("http://127.0.0.1:8000/api/members/mail/"+id,{
                sujet:this.sujet,
                message:this.message,
            }).then(res => {
                this.hideModal('MailModal');
                this.alert.variant = "success";
                this.alert.msg = "Mail envoyée avec succès";
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
