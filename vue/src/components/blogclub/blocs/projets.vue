<template>
  <article id="projets">
    <div class="blog-post" id="custom-cards">
      <h2 class="pb-2 border-bottom">Nos projets</h2>

      <div class="row row-cols-1 row-cols-lg-3 align-items-stretch g-4 py-5">
        <div class="column"  v-for="(item, index) in projects" :key="index">
          <img :src="item.affiche" alt="...">
          
            
         
        </div>

        </div>
    </div>
  </article>
</template>

<script>
export default {
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  name: "projets",
  data() {
    return{
      projects: []
    }
  },
  created(){
    this.$http.get('http://localhost:8000/api/pc/projects/'+this.id).then(response => {
        this.projects = response.data;
       
    })
  },
 

};
</script>
