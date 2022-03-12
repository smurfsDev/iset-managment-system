<template>
    <div>
        <div class="card card-body my-5 py-5 text-center" v-if="demandes.length == 0">
            <h3>il y'a aucun demande</h3>
        </div>
        <!-- <main>
            <data-table v-bind="parameters" @actionTriggered="handleAction" />
        </main>-->
        <!-- <div
            class="modal fade"
            id="ficheModal"
            tabindex="-1"
            aria-labelledby="demandeModalLabel"
            aria-hidden="true"
        >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="demandeModalLabel">Les fiches</h5>
                        <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                        ></button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group mb-2">
                            <b-row class="jutify-content-center mb-2">
                                <b-col v-if="fiches.length == 0" cols="10">Aucune fiche de paie</b-col>
                            </b-row>
                            <b-row
                                v-show="fiches.length != 0"
                                v-for="fiche in fiches"
                                :key="fiche.id"
                                class="justify-content-center mb-2"
                            >
                                <b-col cols="10">
                                    <b-row>
                                        <b-col>date : {{ fiche.date }}</b-col>
                                    </b-row>
                                    <b-row>
                                        <b-col>
                                            <h6>
                                                <b>Rebriques:</b>
                                            </h6>
                                        </b-col>
                                    </b-row>
                                    <b-row>
                                        <b-col cols="9">total : {{ fiche.total }}</b-col>
                                        <b-col cols="3">
                                            <b-button
                                                variant="success"
                                                v-on:click="getPdf(id, fiche.id)"
                                            >Afficher</b-button>
                                        </b-col>
                                    </b-row>
                                </b-col>
                                <hr />
                            </b-row>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button
                            type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal"
                        >Fermer</button>
                    </div>
                </div>
            </div>
        </div> -->
        <b-card v-for="demande in demandes" :key="demande.id">
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
                        <b-button variant="danger" v-on:click="Delete(demande.id)">Delete</b-button>
                        <b-button
                            variant="warning"
                            v-on:click="Update(demande)"
                        >Update</b-button>
                    </b-container>
                </md-tab>
            </md-tabs>
        </b-card>
        <nav class="row">
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
