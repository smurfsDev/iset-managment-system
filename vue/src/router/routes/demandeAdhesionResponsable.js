const demandeAdhesionResponsable = () =>
  import(
    "../../components/club/demandeAdhesion/demandeAdhesion.vue"
  );

export default [
  {
    path: "/club/demandeAdhesionResponsable",
    component: demandeAdhesionResponsable,
    name: "demandeAdhesionResponsable",
    meta: { requiresEtudiant: true},
  },
];
