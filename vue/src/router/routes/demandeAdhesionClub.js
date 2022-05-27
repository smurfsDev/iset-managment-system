const demandeAdhesionClub = () =>
  import(
    "../../components/etudiant/demandeAdhesionClub/demandeAdhesionClub.vue"
  );

export default [
  {
    path: "/demandeAdhesionClub",
    component: demandeAdhesionClub,
    name: "demandeAdhesionClub",
    meta: { requiresEtudiant: true},
  },
];
