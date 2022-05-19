<template>
  <div>
    <mailS @sendMail="sendMail" :use="use"></mailS>
    <div class="row col-12">
      <div class="col-6 mt-1 mb-2">
        <button type="button" class="btn btn-danger" @click="deletteRecords">Delete</button>
      </div>
      <div class="col-6 mt-1 mb-2 ">
        <button type="button" class="btn btn-primary mx-1 float-end" data-bs-toggle="modal" data-bs-target="#MailModal" @click="initModal()">Send Email</button>
      </div>
    </div>
        
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="members.length == 0"
    >
      <h3>il y'a aucun member</h3>
    </div>
  <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>
                    <input type="checkbox" v-model="multipleSelect" @change="selectAll">
                </th>
                <th>Name</th>
                <th>Nom Club</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="member in members" :key="member.id">
                <td>
                    <input type="checkbox" v-model="use" @change="selectSingle(member.user.id)" :value="member.id">
                </td>
                <td>{{ member.user.name }}</td>
                <td> {{ member.club.demande_creation_club.nomClub }}</td>
                <td>{{ member.user.email }}</td>
                <td>
                    <div class="btn-group" role="group">
                        <button class="btn btn-danger" @click="Delete(member.id)"><i class="fas fa-trash-alt"></i></button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    <nav class="row" v-if="members.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchMember(pagination.prev_page_url)"
            class="btn btun page-link"
            :class="[
              !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
            ]"
            >Precedent</a
          >
        </li>
        <li class="page-item">
          <a class="page-link text-dark" href="#">{{
            pagination.current_page + "/" + pagination.last_page
          }}</a>
        </li>
        <li
          :class="[{ disabled: !pagination.next_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchMember(pagination.next_page_url)"
            :class="[
              !pagination.next_page_url ? 'disabled' : 'link-primary btun',
            ]"
            class="btun btn page-link"
            >Suivant</a
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import mailS from "./mail.vue";
export default {
  emits: ["senMail"],
  components: {
    mailS
  },
   data() {
            return {
                multipleSelect:false,
                use:[],

            }
        },
    props:{
        members:Array,
        pagination: Object,
    },
    emits:['fetchMember','deleteMember'],
    methods:{
        initModal() {
          this.showModal("MailModal");
        },
        Delete(id){
            this.$emit("deleteMember",id);
        },
        fetchMember(url){
            this.$emit("fetchMember",url);
        },
        selectAll(){
                if(this.multipleSelect==true){
                    this.use=[]
                    for(var i = 0; i < this.members.length;i++){
                        this.use.push(this.members[i].id)
                        console.log(this.use )
                    }
                }
                else{
                    this.use=[]
                }
            },
        selectSingle(id){
            if(this.members.length==this.use.length){
                this.multipleSelect=true;
            }
            else{
                this.multipleSelect=false;
                for(var i = 0; i < this.use.length;i++){
                    if(this.use[i]==id){
                      this.use.push(id);
                        break;
                    }   
                }
            }
        },
        deletteRecords(){
                if(this.use.length!==0){
                    if(window.confirm("Are you sure you want to delete")){
                        for(var i=0;i<this.use.length;i++){
                           this.Delete(this.use[i]);
                        }
                    }
                }
        },
         sendMail(id){
            this.$emit("sendMail",id);
        },
    },
}
</script>

<style>

</style>