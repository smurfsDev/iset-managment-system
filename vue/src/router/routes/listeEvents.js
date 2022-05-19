const listeEvents = () =>
  import(
    "../../components/etudiant/demandeAdhesionEvent/listeEvent.vue"
  );

export default [
  {
    path: "/listeEvents",
    component: listeEvents,
    name: "listeEvents",
  },
];
