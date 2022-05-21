<template>

  <div>
    
    <div class="card card-body my-5 py-5 text-center" v-if="avis.length==0">
    <div class="mb-3">
      <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#postModal">add post</button>
    </div>
      <h3>il y'a aucun avis</h3>

    </div>
    <div class="add" v-if="avis.length>0">
     <button style="text-decoration: none;" data-bs-toggle="modal" data-bs-target="#postModal" class="btn btn-success">Add Post <i class="fa-solid fa-plus"></i></button>

    </div>
 <b-card class="my-2 mb-3 ml-3 mr-3" v-for="avi in avis" :key="avi.id">

 <div class="head">
   <b style="font-weight:bold">Post #000{{avi.id}}</b>
    <span class="badge badge-danger p-1 m-2">chef departement</span>
    </div>
 <small class="form-text ml-1">&nbspWe'll send At {{avi.created_at}}</small>
   <div class="text-end">
     <a :href="'/detailsavis/'+avi.id" class="btn btn-primary"><i class="fa-solid fa-info-circle"></i></a>
</div>

 </b-card>


<!-- Modal -->
<div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="" @submit.prevent="postAvis">
    <div>
       <label for="#title" class="mb-3" style="font-weight:bold;">Title</label> <input type="text" name="title" id="title" class="form-control" v-model="form.title" placeholder="Title">
    </div>
    <div>
    <label for="#desc" style="font-weight:bold;" class="mb-3">Description</label> 
    <textarea name="content" id="content" v-model="form.content" class="form-control mb-3" cols="30" rows="10" placeholder="send Avis...">
    </textarea>

<label for="#image" style="font-weight:bold;">image</label>
<input type="file" @change="image" name="image" id="image">
    </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
</form>
      </div>
    
    </div>
  </div>
</div>

</div>
</template>



<script>


export default {

  data() {
    return {
     form:{
             title:"",
            content:"",
            image:""
            } ,
      id: "",
      avis:[],
    };
  },
  methods:{
     fetchAvis() {
      this.$http.get('http://localhost:8000/api/avis/show').then(response => this.avis= response.data);
    },
        postAvis(){
            this.$http.post('http://127.0.0.1:8000/api/avis/add',{
                'title':this.form.title,
                'content':this.form.content,
                'image':this.form.image
            });

        },
        image(e){
          console.log(e.target.files[0]);
          this.form.image=e.target.files[0].name;
        }
  },
      mounted() {
       
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