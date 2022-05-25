<template>
  <div class="container w-100 py-4">
    <!-- form containing one field for messages -->
        <chatComponents  :messages="msg" :userId="userId"></chatComponents>  
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
          userId:0,
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
    submitMessage(messages,id) {
      this.msg = messages;
      this.userId = id;
    },
    

    
  },
}
</script>

<style>

</style>