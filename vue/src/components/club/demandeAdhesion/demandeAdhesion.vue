<template>
    <div class="container">
        <show :demandes="demandes" @acceptDemande="acceptDemande" @declineDemande="declineDemande"
            :pagination="pagination" />
    </div>
</template>

<script>
import show from './show.vue';
export default {
    components: {
        show,
    },
    data() {
        return {
            demandes: [],
            pagination: {},
            alert: {
                dismissCountDown: 0,
                variant: "",
                msg: "",
            },

        }
    },
    created() {
        this.fetchDemandes();
    },
    methods: {
        fetchDemandes(
            page_url = "http://127.0.0.1:8000/api/dac/responsable"
        ) {
            let vm = this;
            this.$http.get(page_url).then(response => {
                this.demandes = response.data.data[0].demande_adhesion_club;
                vm.makePagination(response.data);
            });
        },
        makePagination(meta) {
            this.pagination = {
                current_page: meta.current_page,
                current_page_url:
                    "http://localhost:8000/api/dcc?page=" + meta.current_page,
                last_page: meta.last_page,
                next_page_url: meta.next_page_url,
                prev_page_url: meta.prev_page_url,
            };
        },
        acceptDemande(id) {
            this.$http.put("http://localhost:8000/api/dac/responsable/a/" + id)
                .then(() => {
                    this.fetchDemandes();
                    this.edit = false;
                    this.alert.variant = "warning";
                    this.alert.msg = "Demande acceptée avec succès";
                    this.alert.dismissCountDown = 5;
                });
        },
        declineDemande(id) {
            this.$http.put("http://localhost:8000/api/dac/responsable/d/" + id)
                .then(() => {
                    this.fetchDemandes();
                    this.edit = false;
                    this.alert.variant = "warning";
                    this.alert.msg = "Demande non acceptée";
                    this.alert.dismissCountDown = 5;
                });
        }

    }

}
</script>

<style>
</style>