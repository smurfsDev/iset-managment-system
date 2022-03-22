<template>
  <div class="container">
    
    <navbar />
    <main class="container">
      <acceuil />
     
      <div class="row g-5">
    <div class="col-md-8">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">
        
      </h3>
      <QuiSommesNous />
      
    </div><br> <br>
    <historique />
      </div>
    </main>
  </div>
</template>

<script>
import navbar from "./blocs/navbar.vue" 
import acceuil from "./blocs/acceuil.vue"
import QuiSommesNous from "./blocs/QuiSommesNous.vue"
import historique from "./blocs/historique.vue"

export default {
  name: "blogClub",
  components:{
      navbar,acceuil,QuiSommesNous, historique
  }
};
</script>
<style>
 @import '../../assets/css/blog.css';
 
   
 
   
</style>