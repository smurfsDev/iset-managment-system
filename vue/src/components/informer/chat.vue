<template>
  <div class="container w-100 py-4">
    <!-- form containing one field for messages -->
        <chatComponents></chatComponents>  
        <userComponents></userComponents>  
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
    mounted() {
        console.log('Component mounted.')
    },
  data() {
    return {
    };
  },
  methods: {
    
  },
}
</script>

<style>

</style>