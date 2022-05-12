const GererClasse = () =>
  import(
    "../../components/chefDepartment/manageClasses/GererClasses.vue"
  );
export default [
  {
    path: "/cd/GererClasse",
    component: GererClasse,
    name: "GererClasse",
    meta: { requiresChefDepartement: true},
  },
];
