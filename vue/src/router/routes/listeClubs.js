const listeClubs = () =>
  import(
    "../../components/etudiant/demandeAdhesionClub/listeClubs.vue"
  );

export default [
  {
    path: "/listeClubs",
    component: listeClubs,
    name: "listeClubs",
  },
];
