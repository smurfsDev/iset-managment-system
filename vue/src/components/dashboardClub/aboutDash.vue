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
            <h1 class="h2">Ajouter une longue description</h1>
          
            <div class="btn-toolbar mb-2 mb-md-0"></div>
          </div>
         
          <form @submit.prevent="createDescription" v-if="update==false" >
            <div class="mb-3">
              <label for="longDesc" class="form-label"
                >Saisir une longue description</label
              >
              <input
                type="textarea"
                class="form-control"
                v-model="longDescription"
                required
                
              />
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
          </form>

          <form @submit.prevent="submitEdit" v-if="update==true" >
            <div class="mb-3">
              <label for="longDesc" class="form-label"
                >Saisir une longue description</label
              >
              <input
                type="textarea"
                class="form-control"
        
                v-model="longDescription"
                required
                
              />
            </div>
            <button type="submit" class="btn btn-warning">Edit</button>
          </form>

          <div v-if="created==true">
            <h2>Longue description</h2>
            <ol class="list-group list-group" v-for="(item, index) in abouts" :key="index">
              <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-0 me-auto">
                  <div class="fw-bold">Subheading</div>
                  <p>{{item.longDescription}}</p>
                  
                </div><br>
                <div class="badge bg-warning rounded-pill btn btn-sm" @click="updateDescription(item.id)" ><b-icon-pen></b-icon-pen></div>
                <div class="badge bg-danger rounded-pill btn btn-sm" @click="deleteDescription(item.id)"><b-icon-trash></b-icon-trash></div>
              </li>
            </ol>
            
            
          </div>
          
          

          <br /> <br />
          
          
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
  name: "aboutDash",
  components: {
    sidebarDash, 
  },
  data(){
      return {
            abouts:[],
            longDescription: "",
            created : false,
            update : false,
            id : null,
            
      }
  },
  created(){
    this.$http.get('http://localhost:8000/api/1/about/getAll').then(response => {
        // console.log(response.data.data);
        if (response.data.data!=undefined){
          this.abouts = response.data.data
          console.log(this.abouts)
          this.created=true
        }
          
        })
  },
  methods: {
    createDescription(){
      let newAbout = {
        longDescription: this.longDescription,
        idClub: 1
      }
      this.$http.post("http://localhost:8000/api/1/about/create", newAbout).then(response => {
        //this.longDescription = "";
        console.log("post")
        console.log(response.data)
        this.created = true;
        
        this.abouts.push(newAbout)
        
       alert("Description ajoutée");
      });
      
  },
  deleteDescription(id){
    console.log(id)
    this.$http.delete("http://localhost:8000/api/1/about/delete/"+id).then(response => {
      console.log(response.data)
      this.abouts = this.abouts.filter(item => item.id != id)
      alert("Description supprimée");
    })
    this.$http.get('http://localhost:8000/api/1/about/getAll').then(response => {
       
          this.abouts = response.data.data
          console.log(this.abouts)
        })
 
  },
  updateDescription(id){
    console.log(id)
    this.update = true;
    this.id = id;
    for (var i = 0; i < this.abouts.length; i++) {
      if (this.abouts[i].id == id) {
        this.longDescription = this.abouts[i].longDescription;
      }
    }
    console.log(this.longDescription)
    
  },
  submitEdit(){
    let newAbout = {
      longDescription: this.longDescription,
      idClub: 1
    }
    this.$http.put("http://localhost:8000/api/1/about/update/"+this.id, newAbout).then(response => {
      //this.longDescription = "";
      console.log("post")
      console.log(response.data)
      this.created = true;
      
      this.abouts = this.abouts.filter(item => item.id != this.id)
      this.abouts.push(newAbout)
      
     alert("Description modifiée");
     this.update = false;
     this.longDescription = "";
    });
    
  
  }
  
  },
  
    
};
</script>
