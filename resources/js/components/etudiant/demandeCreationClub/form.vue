<template>
    <div
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Créer une demande</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                    ></button>
                </div>
                <form class="mb-3" @submit.prevent="addDemande">
                    <div class="modal-body">
                        <div class="form-group mb-2">
                            <label>Nom du club:</label>
                            <input
                                type="text"
                                class="border-0 form-control"
                                placeholder="Nom club"
                                v-model="oldDemande.nomClub"
                                required="required"
                            />
                            <label>Logo:</label>
                            <input
                                type="text"
                                class="border-0 form-control"
                                placeholder="Logo"
                                v-model="oldDemande.logo"
                                required="required"
                            />
                            <label>Date création</label>
                            <input
                                type="date"
                                class="border-0 form-control"
                                placeholder=""
                                v-model="oldDemande.dateCreation"
                                required="required"
                            />
                            <label>Activité:</label>
                            <input
                                type="text"
                                class="border-0 form-control"
                                placeholder="activite"
                                v-model="oldDemande.activite"
                                required="required"
                            />
                            <label>President:</label>
                            <input
                                type="text"
                                class="border-0 form-control"
                                placeholder="president"
                                v-model="oldDemande.president"
                                required="required"
                            />
                            <label>Vice president:</label>
                            <input
                                type="text"
                                class="border-0 form-control"
                                placeholder="Vice president"
                                v-model="oldDemande.vicePresident"
                                required="required"
                            />
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button
                            type="button"
                            class="btn btn-secondary"
                            @click="resetModal1"
                            data-bs-dismiss="modal"
                        >Fermer</button>
                        <button type="submit" class="btn btn-primary">Sauvegarder</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        oldDemande: Object,
        edit: Boolean,
    },
    emits: ['addDemande']
    ,
    mounted() {
    }
    ,
    methods: {
        addEntreprise() {
            $('#exampleModal').hide();
            $('body').removeClass('modal-open');
            $('.modal-backdrop').remove();
            this.$emit('addDemande', this.oldDemande);
            this.resetModal1();
        },
        resetModal1() {
            // document.querySelectorAll('input').forEach(e => e.value = "");
        },

    }
}
</script>

<style>
</style>
