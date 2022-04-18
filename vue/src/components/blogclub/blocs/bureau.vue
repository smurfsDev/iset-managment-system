<template>
  <article id="bureau">
    <!-- Three columns of text below the carousel -->
    <h2 class="pb-2 border-bottom">Nos membres de bureau</h2>
    <div class="row row-cols-1 row-cols-lg-3 align-items-stretch g-4 py-5" v-for="(item, index) in boards" :key="index">
      <div class="col-lg-4">
        <img
          class="bd-placeholder-img rounded-circle"
          width="140"
          height="140"
          v-bind:src="item.image"
          aria-label="Placeholder: 140x140"
          preserveAspectRatio="xMidYMid slice"
          focusable="false"
        />

        <h2>{{item.nom}}</h2>
        <p>{{item.post}}</p>
      </div>
      <!-- /.col-lg-4 -->
      
      <!-- /.col-lg-4 -->

      
      <!-- /.col-lg-4 -->
    </div>
    <!-- /.row -->
  </article>
</template>

<script>
export default {
  name: "bureau",
  data() {
    return {
      boards: []
    }
  },
  created() {
    this.$http.get('http://localhost:8000/api/1/boards/getAll').then(response => {
      if (response.data.data != undefined) {
        this.boards = response.data.data
      }
    })
  },
};
</script>
