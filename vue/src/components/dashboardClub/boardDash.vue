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
            <h1 class="h2">Ajouter un membre de bureau</h1>
          
            <div class="btn-toolbar mb-2 mb-md-0"></div>
          </div>
          <form @submit.prevent="createMember" v-if="ajout == true">
            <div class="mb-3">
              <label for="name" class="form-label"
                >Saisir le nom</label
              >
              <input
                type="text"
                class="form-control"
                v-model="nom"
                required
                
              />
            </div>
             <div class="mb-3">
              <label for="post" class="form-label"
                >Saisir le poste</label
              >
              <input
                type="text"
                class="form-control"
                v-model="post"
                required
                
              />
            </div>
            <div class="mb-3">
              <label for="bgImg" class="form-label"
                >Un photo</label
              >
              <input
                type="file"
                class="form-control"
                name="image"
                @change="convert64"
                ref="file"
                required
                
              />
            </div>

            <button type="submit" class="btn btn-success">Submit</button>
          </form>
          
          
          <form @submit.prevent="submitEdit" v-if="update==true">
            <div class="mb-3">
              <label for="nom" class="form-label"
                >Saisir un nom</label
              >
              <input
                type="text"
                class="form-control"
                v-model="nom"
                required
                
              />
            </div>
            <div class="mb-3">
              <label for="post" class="form-label"
                >Saisir un post</label
              >
              <input
                type="text"
                class="form-control"
                v-model="post"
                required
                
              />
            </div>
            <div class="mb-3">
              <label for="image" class="form-label"
                >Photo</label
              >
              <input
                type="file"
                class="form-control"
                name="image"
                @change="convert64"
                ref="file"
                required
                
              />
            </div>

            <button type="submit" class="btn btn-warning">Edit</button>
          </form>

          <br /> <br />
          <div class="row">
          <div class="card column" style="width: 18rem;"  v-for="(item, index) in boards" :key="index" >
            <img v-bind:src="item.image" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">{{item.nom}}</h5>
              
                <p class="card-text">{{item.post}}</p>
                
              <button  class="btn btn-danger" @click="deleteBoard(item.id)"><b-icon-trash></b-icon-trash></button>
              <button  class="btn btn-warning" @click="updateBoard(item.id)"><b-icon-pen></b-icon-pen></button>
            </div>
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
  name: "boardDash",
  components: {
    sidebarDash, 
  },


  data() {
    return {
      id: null,
      nom: "",
      post: "",
      image: "",
      srcImage: "",
      ajout: false,
      update: false,
      boards:[],
     
    };
  },
  created(){
     this.ajout = true;
    this.$http.get('http://localhost:8000/api/1/boards/getAll').then(response => {
      
      
        this.boards = response.data.data;
         this.ajout = true;
     
        
    
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
       
        this.image = reader.result;
        
        
      };
      reader.readAsDataURL(file);
 
    },
    createMember() {
    
      var reader = new FileReader();
     
      reader.onloadend = () => {
       
      //  this.$http.get('http://localhost:8000/api/1/boards/getAll').then(response => {

           //this.board = response.data.data
            this.image = reader.result;
            let newBoard = {
            nom: this.nom,
            post: this.post,
            image: this.image,
            idClub : 1
          }
        
        
          this.$http.post("http://localhost:8000/api/1/boards/create",newBoard)
          .then (() => {
            
             this.ajout=true
             this.$http.get('http://localhost:8000/api/1/boards/getAll').then(response => {
              this.boards = response.data.data
             })
             this.nom = "";
              this.post = "";
              this.image = "";

             //this.boards.push(newBoard)
            alert('Membre de bureau ajouté! ')
          })
          .catch(error => {
          console.log('error', error);
          })
          
        
       /* })
        .catch(error => {
          console.log(error)
        })*/
      

      
         }
         reader.readAsDataURL(this.srcImage);
  },
  deleteBoard(id){
    this.$http.delete("http://localhost:8000/api/1/boards/delete/"+id).then(() => {
      
      alert('Membre de bureau supprimé! ')
      this.boards = this.boards.filter(item => item.id != id)
      this.ajout=true
      this.update=false
    })
  },
  updateBoard(id){
    this.update=true
    this.ajout=false
    this.id = id
   
    for (let i = 0; i < this.boards.length; i++) {
      if (this.boards[i].id == id) {
        this.nom = this.boards[i].nom
        this.post = this.boards[i].post
        this.image = this.boards[i].image
      }
    }
  },
  submitEdit(){

    
    let updateBoard = {
            nom: this.nom,
            post: this.post,
            image: this.image,
            idClub : 1
      }


      this.$http.put("http://localhost:8000/api/1/boards/update/"+this.id,updateBoard).then(() => {
       
        alert('Membre de bureau modifié! ')
       this.boards = this.boards.filter(item => item.id != this.id)
       this.$http.get('http://localhost:8000/api/1/boards/getAll').then(response => {
              this.boards = response.data.data
             })
        //this.boards.push(updateBoard)
        this.update=false
        this.ajout=true
        this.nom="" 
        this.post=""
        
      
      })

  }
  
  }
};
</script>
