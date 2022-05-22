<template>
  <div class="container w-100 py-4">
    <!-- form containing one field for messages -->
        <chatComponents  :messages="msg"></chatComponents>  
        <userComponents @messages="submitMessage"></userComponents>  
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
          // userId:0,
          msg:[],
        }
    },
    create() {
        this.fetchMessages();
    },
    mounted() {
        console.log('Component mounted.')
    },
  methods: {
    submitMessage(messages) {
      this.msg = messages;
    },
    // fetchMessages() {
    //     this.$http.get("http://localhost:8000/api/message/M/"+this.userId).then(response => {
    //         this.messages = response.data;
    //      })
    // },

    
  },
}
</script>

<style>

</style>