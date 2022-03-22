const demandeCreationClub = () =>
  import(
    "../../components/etudiant/demandeCreationClub/demandeCreationClub.vue"
  );

export default [
  {
    path: "/etudiant/demandeCreationClub",
    component: demandeCreationClub,
    name: "demandeCreationClub",
  },
];
