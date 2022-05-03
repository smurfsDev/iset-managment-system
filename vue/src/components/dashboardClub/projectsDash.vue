<template>
  <div>
    

    <div class="container" style="padding-left: 10vh">
    
      <div class="row">
        <main>
           
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
            <h1 style="font-family:monospace">Ajouter un ancien projet</h1>
          
            <div class="btn-toolbar mb-2 mb-md-0"></div>
          </div>
          <form @submit.prevent="createProject" v-if="update==false">
           
             
            <div class="mb-3">
              <label for="bgImg" class="form-label" style="font-family:monospace"
                >Une affiche</label
              >
              <input
                type="file"
                class="form-control"
                name="affiche"
                @change="convert64"
                ref="file"
                required
                
              />
            </div>

            <button type="submit" class="btn btn-success">Submit</button>
          </form>
          
          
          <form @submit.prevent="submitEdit" v-if="update==true">
            <div class="mb-3">
              <label for="image" class="form-label" style="font-family:monospace" 
                >Affiche</label
              >
              <input
                type="file"
                class="form-control"
                name="affiche"
                @change="convert64"
                ref="file"
                required
                
              />
            </div>

            <button type="submit" class="btn btn-warning">Edit</button>
          </form>

          <br /> <br />
          <div class="row">
          <div class="card column" style="width: 18rem;"  v-for="(item, index) in projects" :key="index" >
            <img v-bind:src="item.affiche" class="card-img-top" alt="...">
            <div class="card-body">
             
                
              <button  class="btn btn-danger" @click="deleteProject(item.id)"><b-icon-trash></b-icon-trash></button>
              <button  class="btn btn-warning" @click="updateProject(item.id)"><b-icon-pen></b-icon-pen></button>
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


export default {
  name: "projectsDash",
 


  data() {
    return {
      id: null,
      affiche: "",
      srcImage: "",
      ajout: false,
      update: false,
      projects:[],
     
    };
  },
  created(){
    this.$http.get('http://localhost:8000/api/1/projects/getAll').then(response => {
      
      if (response.data.data!=null){
        this.projects = response.data.data;
        console.log(this.projects)
        this.ajout = true;
      }
        
    
    })
    .catch()
  },
  methods: {
    convert64(e) {

      var file = e.target.files[0];
      this.srcImage = file;
      
      
      var reader = new FileReader();
      
      reader.onloadend = () => {
       
        this.affiche = reader.result;
        
        
      };
      reader.readAsDataURL(file);
 
    },
    createProject() {
    
      var reader = new FileReader();
      
      reader.onloadend = () => {
        let newProject = {
             affiche: this.affiche,
                idClub : 1
         }
       this.$http.post("http://localhost:8000/api/1/projects/create",newProject)
          .then (() => {
            
             this.ajout=true
             this.$http.get('http://localhost:8000/api/1/projects/getAll').then(response => {
               this.projects = response.data.data;
             })
             
            alert('Projet ajouté! ')
            this.affiche = ""
          })
          .catch(error => {
            alert('Erreur! ')
          console.log('error', error);
          })
        // this.$http.get('http://localhost:8000/api/1/projects/getAll').then(response => {

        //     if (response.data.data!=null){
        //         this.projects = response.data.data;
                
        //         this.ajout = true;
        //     }

          
        //     let newProject = {
        //       affiche: this.affiche,
        //        idClub : 1
        //   }
        
        
        //   this.$http.post("http://localhost:8000/api/1/projects/create",newProject)
        //   .then (response => {
        //     console.log(response)
        //      this.ajout=true
        //      this.projects.push(newProject)
        //     alert('Projet ajouté! ')
        //   })
        //   .catch(error => {
        //   console.log('error', error);
        //   })
          
        
        // })
        // .catch(error => {
        //   console.log(error)
        // })
      

      
         }
         reader.readAsDataURL(this.srcImage);
  },
  deleteProject(id){
    
    this.$http.delete("http://localhost:8000/api/1/projects/delete/"+id).then(() => {
   
      alert('Projet supprimé! ')
      this.projects = this.projects.filter(item => item.id != id)
      this.ajout=false
      this.update=false
    })
  },
  updateProject(id){
    this.update=true
    this.ajout=false
    this.id = id
    
   
    for (let i = 0; i < this.projects.length; i++) {
      if (this.projects[i].id == id) {
       
        this.affiche = this.projects[i].affiche
      }
    }

  },
  submitEdit(){

    
    let updateProject = {
            id: this.id,
            affiche: this.affiche,
            idClub : 1
      }


      this.$http.put("http://localhost:8000/api/1/projects/update/"+this.id,updateProject).then(() => {
      
        alert('Projet modifié! ')
       this.projects = this.projects.filter(item => item.id != this.id)
        this.projects.push(updateProject)
        
        this.update=false
        this.ajout=true
      
      })

  }
  
  }
};
</script>
