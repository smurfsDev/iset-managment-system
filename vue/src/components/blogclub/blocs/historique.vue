<template>
  <div class="p-4 mb-3 bg-light rounded">
    <h3 class="mb-0">Historique</h3>
    <div class="mb-1 text-muted">Nov 11</div>
    <p class="mb-auto">Fondateur: {{fondator.name}} </p>
  </div>
</template>

<script>
export default {
  props: {
    id: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      fondator:{}
    };
  },
  created() {
    this.$http.get('http://localhost:8000/api/pc/fondator/'+this.id).then(response => {
        this.fondator = response.data;
       
      });
  },
  name: "historique",
};
</script>
