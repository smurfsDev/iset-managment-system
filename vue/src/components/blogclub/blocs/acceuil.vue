<template>
  <div id="acceuil">
    <br />
    <div class="row p-4 p-md-5 mb-4 text-white rounded bg-dark" >
      <div class="col-md-6 px-0">
        <h1 class="display-4 fst-italic">Acceuil</h1>
        
        <p class="lead my-3">
          {{header.petiteDescription}}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "acceuil",
  data() {
    return {
      header: {
        petiteDescription: "",
        backgroundImage: ""
      }
    }
    
  },
  created() {
    
    
      
      this.$http.get('http://localhost:8000/api/1/header/getAll').then(response => {
        
        this.header.petiteDescription = response.data.data[0].petiteDescription;
        this.header.backgroundImage = response.data.data[0].backgroudImage;
       
      });
    
  }
  
};
</script>
