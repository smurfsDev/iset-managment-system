const GererMatieres = () =>
  import(
    "../../components/chefDepartment/manageMatieres/GererMatieres.vue"
  );
export default [
  {
    path: "/GererMatieres",
    component: GererMatieres,
    name: "GererMatiere",
    meta: { requiresChefDepartement: true},
  },
];
