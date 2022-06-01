<template>

  <div>
    
    <div class="card card-body my-5 py-5 text-center" v-if="avis.length==0">
    <div class="mb-3">
      <button class="btn btn-success" v-b-modal.modal-1>add post</button>
    </div>
      <h3>il y'a aucun avis</h3>

    </div>
    <div class="add" v-if="avis.length>0">
    <div v-if="success.length>0" class="alert alert-success">{{success}}</div>
     <button style="text-decoration: none;" v-b-modal.modal-1 class="btn btn-success">Add Post <i class="fa-solid fa-plus"></i></button>

    </div>
 <b-card class="my-2 mb-3 ml-3 mr-3" v-for="avi in avis" :key="avi.id">

 <div class="head">
   <b style="font-weight:bold">Post #000{{avi.id}}</b>
    <span class="badge badge-danger p-1 m-2">{{avi.from_id}} </span>
    </div>
 <small class="form-text ml-1">&nbspWe'll send At {{avi.created_at}}</small>
   <div class="text-end">
     <a :href="'/detailsavis/'+avi.id" class="btn btn-primary"><i class="fa-solid fa-info-circle"></i></a>
</div>
<div>


</div>


 </b-card>


<!--modal v2-->
<div>
  <b-modal id="modal-1" title="BootstrapVue">
    <div v-if="error.length>0" class="alert alert-danger">{{error}}</div>
     <div v-if="success.length>0" class="alert alert-success">{{success}}</div>
        
        <form action="" @submit.prevent="postAvis" enctype="multipart/form-data">
    <div>
       <label for="#title" class="mb-3" style="font-weight:bold;">Title</label> <input type="text" name="title" id="title" class="form-control" v-model="form.title" placeholder="Title">
    </div>
    <div>
    <label for="#desc" style="font-weight:bold;" class="mb-3">Description</label> 
    <textarea name="content" id="content" v-model="form.content" class="form-control mb-3" cols="30" rows="10" placeholder="send Avis...">
    </textarea>

      <label>Image</label>
              <input
                type="file"
                class="form-control"
                @change="convert64"
                ref="file"
                name="image"
                id="image"
              />
    </div>
        <div class="modal-footer">
        <button type="submit" class="btn btn-primary" >Apply</button>
      </div>
</form>
    
  </b-modal>
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
            image:"",
            } ,
      id: "",
      avis:[],
      pagination: {},
      error:"",
      success:""
    };
  },
  methods:{
      fetchavisx(
            page_url = "http://127.0.0.1:8000/api/avis/show"
        ) {
            let vm = this;
            this.$http.get(page_url)
                .then((res) => {
                    this.clubs = res.data.data;
                    this.show = false;
                    vm.makePagination(res.data);
                });
        },
     fetchAvis() {
      this.$http.get('http://localhost:8000/api/avis/show').then(response => this.avis= response.data);
    },
        postAvis(){

          if(this.form.title==""){
          this.error="field of title required";
          this.success=""
          }
          else if(this.form.title.length >50){
            this.error="title is so long ";
            this.success=""
          }
          else if(this.form.content==""){
            this.error="field of description required";
            this.success=""
          }
          else if(this.form.image==""){
            this.error="choose picture please";
            this.success=""
          }
          else{
           this.$http.post('http://127.0.0.1:8000/api/avis/add',{
               'title':this.form.title,
               'content':this.form.content,
                'image':this.form.image
            });
            this.error=""
            this.success="new post send with successfuly"
          }
           
 
        },
        convert64(e) {
         var file = e.target.files[0];
           console.log(file);
           var reader = new FileReader();
            reader.onloadend = () => {
             this.form.image = reader.result;
      };
      reader.readAsDataURL(file);
    },    
     makePagination(meta) {
            this.pagination = {
                current_page: meta.current_page,
                current_page_url:
                    "http://localhost:8000/api/avis/show?page=" + meta.current_page,
                last_page: meta.last_page,
                next_page_url: meta.next_page_url,
                prev_page_url: meta.prev_page_url,
            };
        },

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