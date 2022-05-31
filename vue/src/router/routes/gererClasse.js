const GererClasse = () =>
  import(
    "../../components/chefDepartment/manageClasses/GererClasses.vue"
  );
export default [
  {
    path: "/GererClasse",
    component: GererClasse,
    name: "GererClasse",
    meta: { requiresChefDepartement: true},
  },
];
