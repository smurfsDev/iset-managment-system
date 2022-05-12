<template>
<div class="w-100">

<div class="col-md-6" align="right">
       <input type="button" class="btn btn-success btn-xs" @click="openModel" value="Ajouter demande d'évenement" />
      </div>
       
        <div class="card card-body my-5 py-5 text-center" v-if="DemandeEvent.length == 0">
            <h3>il y'a aucune demande</h3>
        </div>
        <b-card class="my-2 w-100" v-for="demande in DemandeEvent" :key="demande.id">
            <md-tabs>
                <md-tab id="tab-home" md-label="demande">
                    <div class="bv-example-row text-center">
                        <b-row class="mb-2">
                            <b-row>
                                <b-col>Nom évenement : {{ demande.nomEvent }}</b-col>
                            </b-row>
                            <b-row>
                                <b-col> Date évenement : {{ demande.dateEvent }}</b-col>
                            </b-row>
                            <b-row>
                                <b-col>Description : {{ demande.description }} </b-col>
                            </b-row>
                           
                        </b-row>
                                <button type="button" class="btn btn-warning btn-xs " @click="openEditModal(demande)" value="Ajouter demande d'évenement"> <b-icon-pen></b-icon-pen></button>
                                <button type="button" class="btn btn-danger btn-xs " @click="deleteDemande(demande.id)" value="Ajouter demande d'évenement"> <b-icon-trash></b-icon-trash></button>
   
                                
                     
                        </div>
                </md-tab>
            </md-tabs>
        </b-card>
         <div v-if="myModel">
    <transition name="model">
     <div class="modal-mask">
      <div class="modal-wrapper">
       <div class="modal-dialog">
        <div class="modal-content">
         <div class="modal-header">
          <button type="button" class="close" @click="myModel=false"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">{{ dynamicTitle }}</h4>
         </div>
         <div class="modal-body">
         
             <form @submit.prevent="ajouterDemande">
                <div class="form-group">
                    <label>Entrer le nom d'évenement</label>
                    <input type="text" class="form-control" v-model="nomEvent" required />
                </div>
                <div class="form-group">
                    <label>Entrer la date de l'évenement</label>
                    <input type="date" class="form-control" v-model="dateEvent" required />
                </div>
                <div class="form-group">
                    <label>Entrer la description de l'évenement</label>
                    <input type="text" class="form-control" v-model="description" required />
                </div>
                <br />
                <div align="center">
                    <input type="hidden" v-model="hiddenId" />
                    <input type="submit" class="btn btn-success btn-xs" v-model="actionButton"/>
                    <input type="button" class="btn btn-danger btn-xs" @click="myModel=false" value="Cancel" />
                </div>
            </form>
         </div>
         </div>
        </div>
       </div>
      </div>
    
    </transition>
   </div>
        <div>
        <h1>Demande d'événement</h1>

        <button type="button" class="btn btn-primary" @click="ajouterDemande">Ajouter une demande</button>
        <b-list-group v-for="(item, index) in DemandeEvent" :key="index">
           <b-list-group-item  variant="warning">{{item.nomEvent}} </b-list-group-item>
        </b-list-group>
    </div>
    </div>
    
</template>

<script>
export default {
    props: { 
        items: Array,
    },
    name: "showDemande",
    data() {
        return {
            id: "",
            DemandeEvent: [],
            allData:'',
            myModel:false,
            actionButton:'Insert',
            dynamicTitle:'Add Data',
            nomEvent:'',
            dateEvent:'',
            description:'',
            newDemande:{},
            hiddenId:'',
            idDemande:""

        };
    },
    created() {

        this.$http.get("http://localhost:8000/api/demandeEvent/getAll")
        .then((response) => {
           // console.log(response.data.data);
            this.DemandeEvent = response.data.data; 

            // this.DemandeEvent.push(res.data.data); 
            // console.log(this.DemandeEvent);
        })
    },
    methods: {
        updateDemande(id) {
            let updatedEvent={
                
                nomEvent:this.nomEvent,
                dateEvent:this.dateEvent,
                description:this.description
            }
            this.$http.put("http://localhost:8000/api/demandeEvent/update/"+id,updatedEvent)
            .then(() => {
              //  console.log(response.data.data);
               // this.DemandeEvent = response.data.data;

                this.myModel=false;
                this.$http.get("http://localhost:8000/api/demandeEvent/getAll")
                                .then((response) => {
                                
                                    this.DemandeEvent = response.data.data; 

                                })
            })
             

        },
        ajouterDemande() {
            
            console.log(this.actionButton);
            if (this.actionButton == "Insert"){
            this.newDemande = {
                            nomEvent: this.nomEvent,
                            dateEvent: this.dateEvent,
                            description: this.description,
                        };
                    let idClub = this.DemandeEvent[0].clubId
                        
                        this.$http.post("http://localhost:8000/api/demandeEvent/create/"+idClub, this.newDemande)
                        .then((response) => {
                        
                            this.DemandeEvent.push(response.data.data);
                            this.nomEvent = "";
                            this.dateEvent = "";
                            this.description = "";
                            this.myModel = false;
                            this.$http.get("http://localhost:8000/api/demandeEvent/getAll")
                                .then((response) => {
                                
                                    this.DemandeEvent = response.data.data; 

                                })

                        })  
                        }else{
                            this.updateDemande(this.idDemande);
                        }
            

    },
     openModel:function(){
       
        this.actionButton = "Insert";
        this.dynamicTitle = "Add Data";
        this.myModel = true;
        },
        deleteDemande(id){
            confirm("Are you sure you want to delete this record?");

            this.$http.delete("http://localhost:8000/api/demandeEvent/delete/"+id)
            .then((response) => {
                console.log(response.data);
                this.$http.get("http://localhost:8000/api/demandeEvent/getAll")
                    .then((response) => {
                    
                        this.DemandeEvent = response.data.data; 

                    })
                })
        },
        openEditModal:function(demande){
            this.actionButton = "Edit";
            this.dynamicTitle = "Update Event";
            this.myModel = true;
            console.log(demande);
            this.idDemande = demande.id;
            this.nomEvent = demande.nomEvent;
            this.dateEvent = demande.dateEvent;
            this.description = demande.description;


        }


    }
}
</script>
<style>

.modal-mask {
     position: fixed;
     z-index: 9998;
     top: 0;
     left: 0;
     width: 100%;
     height: 100%;
     background-color: rgba(0, 0, 0, .5);
     display: table;
     transition: opacity .3s ease;
   }

    .modal-wrapper {
     display: table-cell;
     vertical-align: middle;
   }
</style>
