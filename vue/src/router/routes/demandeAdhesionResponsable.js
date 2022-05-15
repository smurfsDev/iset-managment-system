const demandeAdhesionResponsable = () =>
  import(
    "../../components/club/demandeAdhesion/demandeAdhesion.vue"
  );

export default [
  {
    path: "/demandeAdhesionResponsable",
    component: demandeAdhesionResponsable,
    name: "demandeAdhesionResponsable",
    meta: { requiresEtudiant: true},
  },
];
