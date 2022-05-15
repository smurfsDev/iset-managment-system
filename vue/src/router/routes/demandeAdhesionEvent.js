const demandeAdhesionEvent = () =>
  import(
    "../../components/etudiant/demandeAdhesionEvent/demandeAdhesionEvent.vue"
  );

export default [
  {
    path: "/demandeAdhesionEvent",
    component: demandeAdhesionEvent,
    name: "demandeAdhesionEvent",
    meta: { requiresEtudiant: true},
  },
];
