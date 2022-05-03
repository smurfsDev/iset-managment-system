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
            <h1 class="h2">Ajouter En tete</h1>
          
            <div class="btn-toolbar mb-2 mb-md-0"></div>
          </div>
          <form @submit.prevent="createHeader" v-if="ajout==false">
            <div class="mb-3">
              <label for="petiteDesc" class="form-label"
                >Saisir une petite description</label
              >
              <input
                type="text"
                class="form-control"
                v-model="petiteDescription"
                required
                :disabled="ajout"
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
                required
                :disabled="ajout"
              />
            </div>

            <button :disabled="ajout" type="submit" class="btn btn-success">Submit</button>
          </form>
          
          
          <form @submit.prevent="submitEdit" v-if="update==true">
            <div class="mb-3">
              <label for="petiteDesc" class="form-label"
                >Saisir une petite description</label
              >
              <input
                type="text"
                class="form-control"
                v-model="petiteDescription"
                required
                
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
                required
                
              />
            </div>

            <button type="submit" class="btn btn-success">Edit</button>
          </form>

          <br /> <br />
          <div class="card" style="width: 18rem;" v-if="ajout==true || update==true">
            <img v-bind:src="backgroundImage" class="card-img-top" alt="...">
            <div class="card-body">
             
              <p class="card-text">{{petiteDescription}}</p>
              <button  class="btn btn-danger" @click="deleteHeader(id)"><b-icon-trash></b-icon-trash></button>
              <button  class="btn btn-warning" @click="updateHeader(id)"><b-icon-pen></b-icon-pen></button>
            </div>
        </div>
          
          <canvas
            class="my-4 w-100"
            id="myChart"
            width="900"
            height="380"
          ></canvas>
          <div>
      
    </div>
         
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
      id: null,
      petiteDescription: "",
      backgroundImage: "",
      srcImage: "",
      ajout: false,
      update: false,
     
    };
  },
  created(){
    this.$http.get('http://localhost:8000/api/1/header/getAll').then(response => {
      
      if (response.data.data!=undefined){
         this.petiteDescription = response.data.data[0].petiteDescription
        this.backgroundImage = response.data.data[0].backgroudImage
        this.ajout=true
        this.id = response.data.data[0].id
      }
    })
    .catch(error => {
      console.log(error)
    })
  },
  methods: {
    convert64(e) {

      var file = e.target.files[0];
      this.srcImage = file;
     
      
      var reader = new FileReader();
      
      reader.onloadend = () => {
       
        this.backgroundImage = reader.result;
        
       
      };
      reader.readAsDataURL(file);
 
    },
    createHeader() {
    
      var reader = new FileReader();
       reader.onloadend = () => {
      
        this.$http.get('http://localhost:8000/api/1/header/getAll').then(response => {
        
          
         if (response.data.data!=undefined){
           alert('Vous avez déjà un header')
           
            this.ajout=true
            this.id = response.data.data[0].id
         } else {
          this.backgroundImage = reader.result;
            let newHeader = {
            petiteDescription: this.petiteDescription,
            backgroudImage: this.backgroundImage,
            idClub : 1
          }
          
        
        
          this.$http.post("http://localhost:8000/api/1/header/create",newHeader)
          .then (() => {
            this.$http.get('http://localhost:8000/api/1/header/getAll').then(response => {
              this.petiteDescription = response.data.data[0].petiteDescription
              this.backgroundImage = response.data.data[0].backgroudImage
             })
             this.ajout=true
            alert('Header ajouté! ')
          })
          .catch(error => {
            alert("Une erreur c'est produite ! Veuillez réessayer s'il vous plait !")
          })
            }
        
        })
        .catch(error => {
          console.log("erreur")
        })
      

      
         }
         reader.readAsDataURL(this.srcImage);
  },
  deleteHeader(id){
    this.$http.delete("http://localhost:8000/api/1/header/delete/"+id).then(() => {
      
      alert('Header supprimé! ')
      this.ajout=false
      this.update=false
      this.petiteDescription=""
      this.backgroundImage=""
    })
  },
  updateHeader(id){
    this.update=true
    this.id = id
    
  },
  submitEdit(){

    
    let updateHeader = {
            id: this.id,
            petiteDescription: this.petiteDescription,
            backgroudImage: this.backgroundImage,
            idClub : 1
      }


      this.$http.put("http://localhost:8000/api/1/header/update/"+this.id,updateHeader).then(
       
        alert('Header modifié! ')
       
      )

  }
  
  }
};
</script>
