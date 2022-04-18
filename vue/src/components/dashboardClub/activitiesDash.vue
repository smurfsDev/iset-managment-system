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
            <h1 class="h2">Ajouter une activité</h1>
          
            <div class="btn-toolbar mb-2 mb-md-0"></div>
          </div>
         
          <form @submit.prevent="createActivity" v-if="update==false" >
            <div class="mb-3">
              <label for="longDesc" class="form-label"
                >Saisir un activité</label
              >
              <input
                type="textarea"
                class="form-control"
                v-model="paragraphe"
                required
                
              />
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
          </form>

          <form @submit.prevent="submitEdit" v-if="update==true" >
            <div class="mb-3">
              <label for="paragraphe" class="form-label"
                >Saisir un activité</label
              >
              <input
                type="textarea"
                class="form-control"
        
                v-model="paragraphe"
                required
                
              />
            </div>
            <button type="submit" class="btn btn-warning">Edit</button>
          </form>

          <div v-if="created==true">
            <h2>Les activités</h2>
            <ul class="list-group list-group" v-for="(item, index) in activities" :key="index">
              <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-0 me-auto">
                 
                  <p>{{item.paragraphe}}</p>
                  
                </div><br>
                <div class="badge bg-warning rounded-pill btn btn-sm" @click="updateParagraphe(item.id)" ><b-icon-pen></b-icon-pen></div>
                <div class="badge bg-danger rounded-pill btn btn-sm" @click="deleteParagraphe(item.id)" ><b-icon-trash></b-icon-trash></div>
              </li>
            </ul>
            
            
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
  name: "activitiesDash",
  components: {
    sidebarDash, 
  },
  data(){
      return {
            activities:[],
            paragraphe: "",
            created : false,
            update : false,
            id : null,
            
      }
  },
  created(){
    this.$http.get('http://localhost:8000/api/1/activities/getAll').then(response => {
       
        if (response.data.data!=undefined){
          this.activities = response.data.data
         
          this.created=true
        }
          
        })
  },
  methods: {
    createActivity(){
      let newActivity = {
        paragraphe: this.paragraphe,
        idClub: 1
      }
      this.$http.post("http://localhost:8000/api/1/activities/create", newActivity).then(() => {
        
        this.created = true;
        this.$http.get('http://localhost:8000/api/1/activities/getAll').then(response => {
       
          this.activities = response.data.data
         
        })
        
        
       alert("Activité ajouté");
       this.paragraphe = "";
      });
      
  },
  deleteParagraphe(id){
   
   
    this.$http.delete("http://localhost:8000/api/1/activities/delete/"+id).then(() => {
      
      this.activities = this.activities.filter(item => item.id != id)
      this.paragraphe = "";
      this.update= false;
      this.create= true;
      alert("Activité supprimée");
    })
    this.$http.get('http://localhost:8000/api/1/activities/getAll').then(response => {
       
          this.activities = response.data.data
         
        })
 
  },
  updateParagraphe(id){
   
    this.update = true;
    this.id = id;
    for (var i = 0; i < this.activities.length; i++) {
      if (this.activities[i].id == id) {
        this.paragraphe = this.activities[i].paragraphe;
      }
    }
   
    
  },
  submitEdit(){
    let newActivity = {
      id: this.id,
      paragraphe: this.paragraphe,
      idClub: 1
    }
    this.$http.put("http://localhost:8000/api/1/activities/update/"+this.id, newActivity).then(() => {
      
       this.created = true;
     
      this.activities = this.activities.filter(item => item.id != this.id)
      this.activities.push(newActivity)
      
     alert("Activité modifiée");
     this.update = false;
     this.paragraphe = "";
    });
    
  
  }
  
  },
  
    
};
</script>
