<template>
    <div style>
        <div class="content container">
            <div class="pt-3 pb-3 container-fluid">
                <b-overlay
                    v-if="show"
                    :show="show"
                    class="d-inline-block"
                    style="height:500px;width:100%"
                ></b-overlay>
                <div v-if="!show">
                    <b-container class="bv-example-row py-0">
                        <b-row class="text-center">
                            <b-col cols="8">
                                <button
                                    type="button"
                                    class="btn btn-primary mx-1 float-start"
                                    data-bs-toggle="modal"
                                    @click="demande={}"
                                    data-bs-target="#exampleModal"
                                >Nouvelle demande</button>
                            </b-col>
                            <b-col></b-col>
                        </b-row>
                    </b-container>
                    <b-alert
                        class="mt-4"
                        :show="alert.dismissCountDown"
                        dismissible
                        :variant="alert.variant"
                        @dismissed="alert.dismissCountDown = 0"
                    >
                        <p>{{ alert.msg }}</p>
                    </b-alert>

                    <formDemande
                        @addDemande="addDemande"
                        :oldDemande="demande"
                    />
                    <!-- <b-card> -->
                    <showDemandes
                        @deleteDemande="deleteDemande"
                        :demandes="DemandeCreationClub"
                        @fetchDemande="fetchDemandeCreationClub"
                        @updateDemande="updateDemande"
                        :pagination="pagination"
                    />
                    <!-- </b-card> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import showDemandes from './show.vue';
import formDemande from './form.vue';
// import search from '../search.vue';

export default {
    components: {
        showDemandes,
        formDemande,
        //     search
    },
    data() {
        return {
            DemandeCreationClub: [],
            demande: {},
            pagination: {},
            edit: false,
            search: "",
            show: true,
            alert: {
                dismissCountDown: 0,
                variant: "",
                msg: "",
            },
            myid:1
        }
    },
    created() {
        document.title = 'Employe';
        this.fetchDemandeCreationClub();
        if (this.$route.params.add == 1) {
            this.alert.variant = "success";
            this.alert.msg = "Employé ajouté avec succès"
            this.alert.dismissCountDown = 5;
        } else if (this.$route.params.edit == 1) {
            this.alert.variant = "warning";
            this.alert.msg = "Employé modifié avec succès"
            this.alert.dismissCountDown = 5;

        } else if (this.$route.params.add == 2) {
            this.alert.variant = this.$route.params.alert.variant;
            this.alert.msg = this.$route.params.alert.msg;
            this.alert.dismissCountDown = 5;
        }
    },
    methods: {
        fetchDemandeCreationClub(page_url = "/api/dcc/"+this.myid) {
            let vm = this;
            let headersi = new Headers();
            // headersi.append('auth', 5);
            fetch(page_url, {
                method: 'GET',
            })
                .then(res => res.json())
                .then(res => {
                    if(res.constructor!==Array){
                        this.DemandeCreationClub = res.data;
                    }
                    else{
                        this.DemandeCreationClub = [];
                    }
                    this.show = false;
                    vm.makePagination(res);
                })
                .catch(err => console.log(err))
        },
        makePagination(meta) {
            this.pagination = {
                current_page: meta.current_page,
                current_page_url: 'http://localhost:8000/api/employe?page=' + meta.current_page,
                last_page: meta.last_page,
                next_page_url: meta.next_page_url,
                prev_page_url: meta.prev_page_url
            };
        },
        deleteDemande(id) {
            let headersi = new Headers();
            headersi.append('auth', 5);
            if (confirm('Delete employe ' + id)) {
                this.show = true;
                fetch('api/dcc/' + id, { method: 'delete', headers: headersi })
                    .then(res => {
                        this.fetchDemandeCreationClub();
                        this.alert.variant = "danger";
                        this.alert.msg = "Demande suprimée avec succès"
                        this.alert.dismissCountDown = 5;
                    })
                    .then(data => {
                    })
                    .catch(err => console.log(err));
            }
        },
        resetModal1() {
            this.employe = {};
        },
        addDemande(demande) {
            let headersi = new Headers();
            headersi.append('auth', 5);
            headersi.append('Content-Type', 'application/json');
            demande.responsableClubId=1;
            this.show = true;
            if (!this.edit) {
                fetch('api/dcc/', {
                    method: 'post',
                    body: JSON.stringify(demande),
                    headers: headersi
                })
                    .then(res => res.json())
                    .then(data => {
                        if (data.success == false) {
                            this.alert.variant = "danger";
                            let err = '';
                            for (const property in data.data) {
                                err += data.data[property]+'\n\n';
                            }
                            console.log(err);
                            this.alert.msg = `
                            ${err}`;
                            this.alert.dismissCountDown = 5;

                        } else {
                            this.alert.variant = "success";
                            this.alert.msg = "Employé ajouté avec succès"
                            this.alert.dismissCountDown = 5;
                        }
                        this.fetchDemandeCreationClub();

                    }
                    )
                    .catch(err => console.log(err))
            } else {
                fetch('api/dcc/' + demande.id, {
                    method: 'put',
                    body: JSON.stringify(demande),
                    headers: headersi
                })
                    .then(res => res.json())
                    .then(data => {
                        this.fetchDemandeCreationClub();
                        this.edit = false;
                        this.alert.variant = "warning";
                        this.alert.msg = "Employé modifié avec succès"
                        this.alert.dismissCountDown = 5;

                    }
                    )
                    .catch(err => console.log(err))
            }
        },
        updateDemande(demande) {
            this.edit = true;
            this.demande = demande;
        },
        searchEmploye(search) {
            this.search = search;
            this.fetchDemandeCreationClub();
        }

    }
}
</script>

<style>
.btun {
    color: white !important;
    background-color: #d32f2f !important;
}
</style>
