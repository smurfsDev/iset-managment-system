<template>
    <div class="container w-100">
        <div class="card card-body my-5 py-5 text-center" v-if="events.length == 0">
            <h3>il y'a aucun évenement</h3>
        </div>
        <b-card class="my-2" v-for="event in events" :key="event.id">
            <md-tabs style="height: auto;!important">
                <md-tab id="tab-home" style="height: auto;!important" md-label="event">
                     <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
                        @dismissed="alert.dismissCountDown = 0">
                        <p>{{ alert.msg }}</p>
                    </b-alert>

                    <div class="bv-example-row text-center">
                        <b-row class="mb-2">
                            <b-row>
                                                           
                                <b-col>
                                    Nom du Evenement : {{ event.nomEvent }} <br>Date evenement :
                                    {{ event.dateEvent }}
                                </b-col>
                            </b-row>
                            <b-row>
                                <b-col>Description : {{ event.description }} <br></b-col>
                            </b-row>
                            <b-row>
                                <b-col>Organisé par : {{ event.club.nom }} <br></b-col>
                            </b-row>
                            
                        </b-row>
                        <b-button variant="success" 
                            @click="demandeAdhesion(event.id,event.nom,event.nomEvent,event.dateEvent)"
                            
                        >
                            S'inscrire</b-button>
                        
                    </div>
                </md-tab>
            </md-tabs>
        </b-card>
        <nav class="row" v-if="events.length != 0">
            <ul class="pagination w-auto mx-auto">
                <li :class="[{ disabled: !pagination.prev_page_url }]" class="page-item">
                    <a @click="fetchclub(pagination.prev_page_url)" class="btn btun page-link" :class="[
                        !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
                    ]">Precedent</a>
                </li>
                <li class="page-item">
                    <a class="page-link text-dark" href="#">{{
                            pagination.current_page + "/" + pagination.last_page
                    }}</a>
                </li>
                <li :class="[{ disabled: !pagination.next_page_url }]" class="page-item">
                    <a @click="fetchclub(pagination.next_page_url)" :class="[
                        !pagination.next_page_url ? 'disabled' : 'link-primary btun',
                    ]" class="btun btn page-link">Suivant</a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script>
export default {

    data() {
        return {
            events: [],
            pagination: {},
            show: true,
            alert: {
                dismissCountDown: 0,
                variant: "",
                msg: "",
            },
           // inscrire : false,
           
        };
    },
    created() {
        this.fetchEvents();
    },
    methods: {
        fetchEvents(
            page_url = "http://127.0.0.1:8000/api/demandeEvent/getApprouve"
        ) {
            let vm = this;
            this.$http.get(page_url)
                .then((res) => {
                    console.log(res.data.data.data)
                    this.events= res.data.data.data;

                  
                    this.show = false;
                    console.log(res.data.data)
                    vm.makePagination(res.data.data);
                    //console.log(this.events.data);
                   
                });
                
               

        },
        makePagination(meta) {
            this.pagination = {
                current_page: meta.current_page,
                current_page_url:
                    "http://localhost:8000/api/demandeEvent/getApprouve?page=" + meta.current_page,
                last_page: meta.last_page,
                next_page_url: meta.next_page_url,
                prev_page_url: meta.prev_page_url,
            };
        },
        demandeAdhesion(id,nomClub,nomEvent,dateEvent){
            console.log(this.events);
            confirm("Voulez-vous vraiment vous inscrire à l'évenement " + nomEvent + " ?");
            this.$router.push({
                name: 'demandeAdhesionEventForm',
                params: {
                    id: id,
                    nomClub: nomClub,
                    nomEvent : nomEvent,
                    dateEvent : dateEvent 
                }
                
                
            });
            console.log(id,nomClub,nomEvent,dateEvent);

            // this.$http.post("http://localhost:8000/api/demandeAdhesionEvent/create/"+id)
            // .then(res => {
            //     console.log(res)
                
            //     this.alert.msg = "Votre demande a été envoyé avec succès";
            //     console.log(res.data.message)
            //     console.log(this.alert.msg)
            //     this.alert.variant = "success";
            //     alert("Votre demande a été envoyé avec succès");
              
            //     this.fetchEvents();
            //     this.inscrire = false;
            //     console.log(this.inscrire)
            // })
        },
        // deleteDemande(id){
        //     console.log(id)
        //     this.$http.delete("http://localhost:8000/api/demandeAdhesionEvent/delete/"+id)
        //     .then(res => {
        //         this.alert.msg = res.data.message;
        //         this.alert.variant = "success";
        //         alert("Votre demande a été annulée avec succès");
        //          this.fetchEvents();
        //         this.inscrire = true;
        //     })
        // }
    }

}
</script>

<style>
</style>