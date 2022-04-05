<template>
  <div>
    <header
      class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow"
    >
      <a class="navbar-brand col-md-3 col-lg-9 me-0 px-3" href="/blogClub"
        >Club name</a
      >

      <div class="navbar-nav">
        <div class="nav-item text-nowrap">
          <a class="nav-link px-3" href="#">Sign out</a>
        </div>
      </div>
    </header>

    <div class="container-fluid">
      <sidebarDash />
      <div class="row">
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <div
            class="
              d-flex
              justify-content-between
              flex-wrap flex-md-nowrap
              align-items-center
              pt-3
              pb-2
              mb-3
              border-bottom
            "
          >
            <h1 class="h2">En tete</h1>
          
            <div class="btn-toolbar mb-2 mb-md-0"></div>
          </div>
          <form @submit.prevent="createHeader">
            <div class="mb-3">
              <label for="petiteDesc" class="form-label"
                >Saisir une petite description</label
              >
              <input
                type="text"
                class="form-control"
                v-model="petiteDescription"
              />
            </div>
            <div class="mb-3">
              <label for="bgImg" class="form-label"
                >Image d'arrière plan pour l'entete du blog</label
              >
              <input
                type="file"
                class="form-control"
                name="backgroundImage"
                @change="convert64"
                ref="file"
              />
            </div>

            <button type="submit" class="btn btn-success">Submit</button>
          </form>
          <canvas
            class="my-4 w-100"
            id="myChart"
            width="900"
            height="380"
          ></canvas>
         <getHeader /> 
          <h2>Section title</h2>
        </main>
      </div>
    </div>
  </div>
</template>

<script>

import sidebarDash from "./sidebarDash.vue";

export default {
  name: "headerDash",
  components: {
    sidebarDash,
  },
  data() {
    return {
      petiteDescription: "",
      backgroundImage: "",
      srcImage: "",
    };
  },
  methods: {
    convert64(e) {
      //console.log(this.$refs.file.files[0]);
      //this.backgroundImage = this.$refs.file.files[0];

      /*var file = e.target.files[0];
          var bitmap = fs.readFileSync(file);

          console.log(new Buffer(bitmap).toString('base64'));*/

      var file = e.target.files[0];
      this.srcImage = file;
      console.log(this.srcImage);
      
      var reader = new FileReader();
      console.log(1);
      reader.onloadend = () => {
        //  let test = reader.result
        this.backgroundImage = reader.result;
        
        console.log(this.backgroundImage);
      };
      reader.readAsDataURL(file);
      
      
    },
    createHeader() {
      

      var reader = new FileReader();
      console.log(1);
      reader.onloadend = () => {
       
        this.backgroundImage = reader.result;
        let newHeader = {
        petiteDescription: this.petiteDescription,
        backgroudImage: this.backgroundImage,
        idClub : 1
      }
     
      this.$http.post("http://localhost:8000/api/1/header/create",newHeader)
      .then (response => {
        console.log(response)
        alert('Header ajouté! ')
      })
      .catch(error => {
      console.log('error', error);
      })
      };
      reader.readAsDataURL(this.srcImage);

    },
  },
};
</script>
