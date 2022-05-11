const showDemande = () =>
  import(
    "../../components/club/demandeEvenement/showDemande.vue"
  );

export default [
  {
    path: "/club/demandeEvent",
    component: showDemande,
    name: "showDemande",
    meta: { requiresResponsable: true},
  },
];
