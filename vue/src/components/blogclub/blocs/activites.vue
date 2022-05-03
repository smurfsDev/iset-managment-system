<template>
  <div id="activites">
    <article class="blog-post">
      <h2 class="blog-post-title">Nos activités</h2>
      <br />
      <ul >
        <li v-for="(item, index) in tasks" :key="index">{{item.paragraphe}}</li>
        
      </ul>
    </article>
  </div>
</template>

<script>
export default {
  name: "activites",
  data(){
    return {
      tasks: []
    }
  },
  created(){
    this.$http.get('http://localhost:8000/api/1/activities/getAll').then(response => {
          this.tasks = response.data.data
        })
  }
};
</script>
