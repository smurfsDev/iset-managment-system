const listeClubs = () =>
  import(
    "../../components/etudiant/demandeAdhesionClub/listeClubs.vue"
  );

export default [
  {
    path: "/etudiant/listeClubs",
    component: listeClubs,
    name: "listeClubs",
  },
];
