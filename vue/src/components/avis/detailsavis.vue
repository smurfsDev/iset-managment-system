<template>

  <div>
    
    <div class="card card-body my-5 py-5 text-center" v-if="avis.length==0">
    <div class="mb-3">
      <a href="/avis" class="btn btn-primary">add post</a>
    </div>
      <h3>il y'a aucun avis</h3>

    </div>
 <b-card v-else class="my-2" :key="avis.id">
 <div class="head">
    <a href="/listavis" class="badge btn-primary"> <i class="fa-solid fa-list"></i></a> <b style="font-weight:bold">Post #000{{avis.id}}</b>
    <span class="badge btn-danger p-1 m-2">chef departement</span>
    </div>
 <small class="form-text ml-1">&nbspWe'll send At {{avis.created_at}}</small>
  <div>
    <b>Title:</b>
    <div class="alert alert-success">
{{avis.title}}
    </div>
    </div>
    <b>Description:</b>
    <div class="alert alert-info">
      {{avis.content}}
  </div>
 <div class="text-end">
 <!-- Button trigger modal -->
    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fa-solid fa-edit"></i></button>
        <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal"><i class="fa-solid fa-trash"></i></button>

 </div>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Post</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="" @submit.prevent="updateAvis">
    <div>
       <label for="title" class="mb-3" style="font-weight:bold;">Title</label>
        <input type="text" name="title" id="title" class="form-control" v-model="avis.title" placeholder="Title">
    </div>
    <div>
    <label for="desc" style="font-weight:bold;" class="mb-3">Description</label> 
    <textarea name="content" id="content" v-model="avis.content" class="form-control mb-3" cols="30" rows="10" placeholder="send Avis..." >
    </textarea>

    </div>
         <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary" @click="hideModal('exampleModal')">Save changes</button>
      </div>
</form>
      </div>
   
    </div>
  </div>
</div>
<!--delete modal-->





<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Post</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>Are you sure you want to remove this Post?</p>
      </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <form action="" @submit.prevent="deleteAvis">
         <button type="submit" class="btn btn-danger" @click="hideModal('deleteModal')">delete</button>
        </form>
      </div>
    </div>
  </div>
</div>

 </b-card>

</div>
</template>



<script>
import listeavis from './listavis.vue';

export default {

  data() {
    return {
     form:{
             title:"",
            content:"",
            } ,
      id: "",
      avis:[]
    };
  },
  methods:{
     fetchAvis() {
      this.$http.get('http://localhost:8000/api/avis/details/'+this.id).then(response => this.avis= response.data);
    },
    updateAvis(){
      this.$http.post('http://localhost:8000/api/avis/update/'+this.id,this.avis)
    },
    deleteAvis(){
        this.$http.post('http://localhost:8000/api/avis/delete/'+this.id);
        this.$router.push({name:'listavis'});
    },
 

  },
      mounted() {
          this.id=this.$route.params.id;
            this.fetchAvis();
        }
}
  </script>

  <style>
          .head{
        display:flex;
        align-items:center;
    }
  
  </style>