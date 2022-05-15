<template>
  <div class="container">
    <h1>Mes demande d'adhesion des évenements</h1>
    <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
      @dismissed="alert.dismissCountDown = 0">
      <p>{{ alert.msg }}</p>
    </b-alert>
    <main>
      
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      demandes: [],
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
    }
  },
  created() {
    this.getDemandes()
    if (this.$route.params.nomEvent != null) {
      this.alert.variant = "success";
      this.alert.msg = "Demande d'adhesion au évenement envoyée avec succès";
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    getDemandes() {
      this.$http.get("http://localhost:8000/api/demandeAdhesionEvent/get").then(response => {
          console.log(response.data.data);
            this.demandes = response.data.data;
            this.demandes.forEach(demande => {
          demande.nomEvent = demande.club.nom;
          demande.status = demande.status == 0 ? "En attente" : demande.status == 1 ? "Acceptée" : "Refusée";
        })
        // this.demandes = response.data.data
        // console.log(this.demandes);
      //   this.demandes.forEach(demande => {
      //     console.log(demande.idEvent);
      //     this.$http.get("http://localhost:8000/api/demandeEvent/getOne/" + demande.idEvent).then(response => {
      //      // console.log(response.data.data);
      //       demande.event = response.data.data;
      //     });
      //     demande.status = demande.status == 0 ? "En attente" : demande.status == 1 ? "Acceptée" : "Refusée";
      //   })
      //   console.log(this.demandes);
       })
    },
    handleAction(action, data) {
      if (action == 'delete') {
        if (confirm("Suprimer demande ?")) {
          this.$http.delete("http://localhost:8000/api/demandeAdhesionEvent/delete/" + data.id).then(() => {
            this.alert.variant = "danger";
            this.alert.msg = "Demande d'adhesion supprimée !";
            this.alert.dismissCountDown = 5;
            this.getDemandes()
          })
        }
      }
    }
  },
  computed: {
    parameters() {
      return {
        data: this.demandes,
        text: {
          perPageText: 'Afficher :entries lignes',
          infoText: 'Affichage de :first a :last de :total lignes',
          infoTextFiltered: 'Affichage de :first a :last de :total lignes(filtrée de :total)',
          nextButtonText: 'Suiva',
          previousButtonText: 'Prec',
          paginationSearchText: 'Aller vers page',
          searchText: 'recherche',
          downloadButtonText: 'TELECHARGER',
          emptyTableText: 'Aucun demande trouvée'
        },
        actionMode: "single",
        actions: ['delete']
        ,
        columns: [
          {
            key: "nomEvent",
            title: "Nom Event",
          },
          {
            key: "status",
            title: "Status",

          },
          {
            key: "nomClub",
            title: "Nom Club",
          }
        ]
      };
    }
  },
}
</script>

<style>
</style>