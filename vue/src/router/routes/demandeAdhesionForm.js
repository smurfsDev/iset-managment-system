const demandeAdhesionForm = () =>
  import("../../components/etudiant/demandeAdhesionClub/form.vue");

export default [
  {
    path: "/etudiant/demandeAdhesionForm",
    component: demandeAdhesionForm,
    name: "demandeAdhesionForm",
    meta: { requiresEtudiant: true }
  }
];
