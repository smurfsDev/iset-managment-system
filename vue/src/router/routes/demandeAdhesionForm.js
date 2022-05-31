const demandeAdhesionForm = () =>
  import("../../components/etudiant/demandeAdhesionClub/form.vue");

export default [
  {
    path: "/demandeAdhesionForm",
    component: demandeAdhesionForm,
    name: "demandeAdhesionForm",
    meta: { requiresEtudiant: true }
  }
];
