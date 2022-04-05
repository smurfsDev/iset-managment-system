<template>
  <div id="acceuil">
    <br />
    <div class="row p-4 p-md-5 mb-4 text-white rounded bg-dark">
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
    
      console.log("created")
      //console.log(this.header)
      this.$http.get('http://localhost:8000/api/1/header/getAll').then(response => {
        console.log(response.data)
        this.header.petiteDescription = response.data.petiteDescription;
        this.header.backgroundImage = response.data.backgroudImage;
        console.log(this.header.petiteDescription)
        console.log(this.header.backgroundImage)
      });
    
  }
  
};
</script>
