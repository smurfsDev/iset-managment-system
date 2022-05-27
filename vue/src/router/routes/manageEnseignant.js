const manageEnseignant = () =>
  import("../../components/chefDepartment/enseignant/enseignant.vue");

export default [
  {
    path: "/manageEnseignant",
    component: manageEnseignant,
    name: "manageEnseignant",
    meta: { requiresChefDepartement: true },
  },
];
