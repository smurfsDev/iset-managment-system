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
                                <b-col>Organisé par : {{ event.club.demande_creation_club.nomClub }} <br></b-col>
                            </b-row>
                            
                        </b-row>
                        <b-button variant="success" 
                            @click="demandeAdhesion(event.id,event.nomEvent,event.club.demande_creation_club.nomClub,event.dateEvent)"
                            
                        >
                            S'inscrire</b-button>
                        
                    </div>
                </md-tab>
            </md-tabs>
        </b-card>
        <nav class="row" v-if="events.length != 0">
            <ul class="pagination w-auto mx-auto">
                <li :class="[{ disabled: !pagination.prev_page_url }]" class="page-item">
                    <a @click="fetchEvents(pagination.prev_page_url)" class="btn btun page-link" :class="[
                        !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
                    ]">Precedent</a>
                </li>
                <li class="page-item">
                    <a class="page-link text-dark" href="#">{{
                            pagination.current_page + "/" + pagination.last_page
                    }}</a>
                </li>
                <li :class="[{ disabled: !pagination.next_page_url }]" class="page-item">
                    <a @click="fetchEvents(pagination.next_page_url)" :class="[
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
                    
                    this.events= res.data.data.data;
  
                  
                    this.show = false;
                
                    
                    vm.makePagination(res.data.data);
                   
                   
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
        demandeAdhesion(id,nomEvent,nomClub,dateEvent){
            
            this.$router.push({
                name: 'demandeAdhesionEventForm',
                params: {
                    id: id,
                    nomClub: nomClub,
                    nomEvent : nomEvent,
                    dateEvent : dateEvent 
                }
                
                
            });
           

            
        },
        
    }

}
</script>

<style>
</style>