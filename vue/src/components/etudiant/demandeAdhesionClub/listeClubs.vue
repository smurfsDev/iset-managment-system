<template>
    <div class="container w-100">
        <div class="card card-body my-5 py-5 text-center" v-if="clubs.length == 0">
            <h3>il y'a aucun club</h3>
        </div>
        <b-card class="my-2" v-for="club in clubs" :key="club.id">
            <md-tabs style="height: auto;!important">
                <md-tab id="tab-home" style="height: auto;!important" md-label="club">
                    <div class="bv-example-row text-center">
                        <b-row class="mb-2">
                            <b-row>
                                <b-col>
                                    Nom du club : {{ club.nomClub }} Date création :
                                    {{ club.dateCreation }}
                                </b-col>
                            </b-row>
                            <b-row>
                                <b-col>Activité : {{ club.dateCreation }}</b-col>
                            </b-row>
                            <b-row>
                                <b-col>
                                    President : {{ club.president }} Vice president :
                                    {{ club.vicePresident }}
                                    <br />
                                    <img :src="club.logo" alt="logo" width="250" />
                                </b-col>
                            </b-row>
                        </b-row>
                        <b-button variant="info" 
                            @click="demandeAdhesion(club.id)"
                        >
                            S'inscrire</b-button>
                    </div>
                </md-tab>
            </md-tabs>
        </b-card>
        <nav class="row" v-if="clubs.length != 0">
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
            clubs: [],
            pagination: {},
            show: true,
            alert: {
                dismissCountDown: 0,
                variant: "",
                msg: "",
            },
        };
    },
    created() {
        this.fetchclub();
    },
    methods: {
        fetchclub(
            page_url = "http://127.0.0.1:8000/api/dac/"
        ) {
            let vm = this;
            this.$http.get(page_url)
                .then((res) => {
                    this.clubs = res.data.data;
                    this.show = false;
                    vm.makePagination(res.data);
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
        demandeAdhesion(id){
            this.$router.push({
                name: 'demandeAdhesionForm',
                params: {
                    id: id
                }
            });
        }
    }

}
</script>

<style>
</style>