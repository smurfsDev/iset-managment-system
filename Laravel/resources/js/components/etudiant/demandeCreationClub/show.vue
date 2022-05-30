<template>
    <div >
        <div class="card card-body my-5 py-5 text-center" v-if="demandes.length == 0">
            <h3>il y'a aucune demande</h3>
        </div>
        <b-card class="my-2" v-for="demande in demandes" :key="demande.id">
            <md-tabs>
                <md-tab id="tab-home" md-label="demande">
                    <b-container class="bv-example-row text-center">
                        <b-row class="mb-2">
                            <b-row>
                                <b-col>Nom du club : {{ demande.nomClub }} Date création : {{ demande.dateCreation }}</b-col>
                            </b-row>
                            <b-row>
                                <b-col>Activité : {{ demande.activite }}</b-col>
                            </b-row>
                            <b-row>
                                <b-col>President : {{ demande.president }} Vice president : {{ demande.vicePresident }}</b-col>
                            </b-row>
                        </b-row>
                        <b-button variant="danger" v-on:click="Delete(demande.id)">Supprimer</b-button>
                        <b-button
                            variant="warning"
                            v-on:click="Update(demande)"
                        >Modifier</b-button>
                    </b-container>
                </md-tab>
            </md-tabs>
        </b-card>
        <nav class="row" v-if="demandes.length!=0">
            <ul class="pagination w-auto mx-auto">
                <li :class="[{ disabled: !pagination.prev_page_url }]" class="page-item">
                    <a
                        @click="fetchDemande(pagination.prev_page_url)"
                        :class="[!pagination.prev_page_url ? 'disabled' : 'link-primary btun']"
                        class="btn page-link"
                    >Precedent</a>
                </li>
                <li class="page-item">
                    <a
                        class="page-link text-dark"
                        href="#"
                    >{{ pagination.current_page + "/" + pagination.last_page }}</a>
                </li>
                <li :class="[{ disabled: !pagination.next_page_url }]" class="page-item">
                    <a
                        @click="fetchDemande(pagination.next_page_url)"
                        :class="[!pagination.next_page_url ? 'disabled' : 'link-primary btun']"
                        class="btn page-link"
                    >Suivant</a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script>

export default {
    props: {
        demandes: Array,
        pagination: Object,
    },
    data() {
        return {
            id: ''
        }
    },
    emits: ["deleteDemande", "updateDemande", "fetchDemande"],
    methods: {
        Delete(id) {
            this.$emit("deleteDemande", id);
        },
        Update(demande) {
            this.$emit("updateDemande", demande);
            $("#exampleModal").modal('show');

        },
        fetchDemande(url) {
            this.$emit("fetchDemande", url);
        }
        }
    }
</script>

<style>
</style>
