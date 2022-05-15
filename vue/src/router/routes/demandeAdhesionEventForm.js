const demandeAdhesionEventForm = () =>
  import("../../components/etudiant/demandeAdhesionEvent/form.vue");

export default [
  {
    path: "/demandeAdhesionForm",
    component: demandeAdhesionEventForm,
    name: "demandeAdhesionEventForm",
    meta: { requiresEtudiant: true }
  }
];
