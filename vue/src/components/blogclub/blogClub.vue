<template>
  <div class="container">
    
    <navbar />
    <acceuil />
  </div>
</template>

<script>
import navbar from "./blocs/navbar.vue" 
import acceuil from "./blocs/acceuil.vue"

export default {
  name: "blogClub",
  components:{
      navbar,acceuil
  }
};
</script>
<style>
 @import '../../assets/css/blog.css';
 
   
 
   
</style>