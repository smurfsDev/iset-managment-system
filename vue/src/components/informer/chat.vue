<template>
  <div class="container w-100 py-4">
    <!-- form containing one field for messages -->
    <chatComponents
      @fetchThem="fetchMyMessages"
      :messages="msg"
      :userId="myId"
    ></chatComponents>
    <userComponents
      @fetchMyMessages="fetchMyMessages"
      @messages="submitMessage"
    ></userComponents>
  </div>
</template>

<script>
import chatComponents from "./chatComponents.vue";
import userComponents from "./userComponents.vue";

export default {
  components: {
    chatComponents,
    userComponents,
  },
  data() {
    return {
      userId: 0,
      msg: [],
      myId: 0,
    };
  },
  create() {
    this.fetchMessages();
  },
  mounted() {
    console.log("Component mounted.");
  },
  methods: {
    submitMessage(messages, id) {
      this.msg = messages;
      this.userId = id;
    },
    fetchMyMessages(id) {
      this.myId = id != null ? id : this.myId;
      this.$http
        .get("http://localhost:8000/api/message/M/" + this.myId)
        .then((response) => {
          this.msg = response.data;
        })
        .catch(() => {
          this.msg = [];
        });
    },
  },
};
</script>

<style>
</style>