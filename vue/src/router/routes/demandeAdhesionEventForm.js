const demandeAdhesionEventForm = () =>
  import("../../components/etudiant/demandeAdhesionEvent/form.vue");

export default [
  {
    path: "/demandeAdhesionEventForm",
    component: demandeAdhesionEventForm,
    name: "demandeAdhesionEventForm",
    meta: { requiresEtudiant: true }
  }
];
