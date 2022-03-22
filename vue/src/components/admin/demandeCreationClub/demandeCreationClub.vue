<template>
    <div>
        <div class="content container">
            <div class="pt-3 pb-3 container-fluid">
                <b-overlay
                    v-if="show"
                    :show="show"
                    class="d-inline-block"
                    style="height: 500px; width: 100%"
                ></b-overlay>
                <div class="pt-4 mt-4" v-if="!show">
                    <b-alert
                        class="mt-4"
                        :show="alert.dismissCountDown"
                        dismissible
                        :variant="alert.variant"
                        @dismissed="alert.dismissCountDown = 0"
                    >
                        <p>{{ alert.msg }}</p>
                    </b-alert>

                    <!-- <b-card> -->
                    <showDemandes
                        @acceptDemande="acceptDemande"
                        :demandes="DemandeCreationClub"
                        @fetchDemande="fetchDemandeCreationClub"
                        @declineDemande="declineDemande"
                        :pagination="pagination"
                    />
                    <!-- </b-card> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import showDemandes from "./show.vue";

export default {
    components: {
        showDemandes,
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
            myid: 1,
        };
    },
    created() {
        // console.log(typeof $);
        document.title = "Demande";
        this.fetchDemandeCreationClub();
    },
    methods: {
        fetchDemandeCreationClub(
            page_url = "http://127.0.0.1:8000/api/dcc/admin"
        ) {
            let vm = this;
            // let headersi = new Headers();
            // headersi.append('auth', 5);
            fetch(page_url, {
                method: "GET",
            })
                .then((res) => res.json())
                .then((res) => {
                    if (res.constructor !== Array) {
                        this.DemandeCreationClub = res.data;
                    } else {
                        this.DemandeCreationClub = [];
                    }
                    this.show = false;
                    vm.makePagination(res);
                })
                .catch((err) => console.log(err));
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
        deleteDemande(id) {
            let headersi = new Headers();
            headersi.append("auth", 5);
            if (confirm("Delete document " + id)) {
                this.show = true;
                fetch("http://localhost:8000/api/dcc/" + id, {
                    method: "delete",
                    headers: headersi,
                })
                    .then(() => {
                        this.fetchDemandeCreationClub();
                        this.alert.variant = "danger";
                        this.alert.msg = "Demande suprimée avec succès";
                        this.alert.dismissCountDown = 5;
                    })
                    .then(() => { })
                    .catch((err) => console.log(err));
            }
        },
        resetModal1() {
            this.document = {};
        },
        acceptDemande(id) {
            let headersi = new Headers();
            headersi.append("auth", 5);
            headersi.append("Content-Type", "application/json");
            fetch("http://localhost:8000/api/dcc/a/" + id, {
                method: "put",
                body: JSON.stringify({adminId:this.myid}),
                headers: headersi,
            })
                .then((res) => res.json())
                .then(() => {
                    this.fetchDemandeCreationClub();
                    this.edit = false;
                    this.alert.variant = "warning";
                    this.alert.msg = "Demande acceptée avec succès";
                    this.alert.dismissCountDown = 5;
                })
                .catch((err) => console.log(err));
        },
        declineDemande(id) {
            let headersi = new Headers();
            headersi.append("auth", 5);
            headersi.append("Content-Type", "application/json");
            fetch("http://localhost:8000/api/dcc/d/" + id, {
                method: "put",
                body: JSON.stringify({adminId:this.myid}),
                headers: headersi,
            })
                .then((res) => res.json())
                .then(() => {
                    this.fetchDemandeCreationClub();
                    this.edit = false;
                    this.alert.variant = "warning";
                    this.alert.msg = "Demande non acceptée avec succès";
                    this.alert.dismissCountDown = 5;
                })
                .catch((err) => console.log(err));
        }
    },
};
</script>

<style>
.btun {
    color: white !important;
    background-color: #d32f2f !important;
}
</style>
