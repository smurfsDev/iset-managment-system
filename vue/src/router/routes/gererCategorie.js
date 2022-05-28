const GererCategorie = () =>
  import(
    "../../components/chefDepartment/categoriedocument/GererCategorie.vue"
  );
export default [
  {
    path: "/GererCategorie",
    component: GererCategorie,
    name: "GererCategorie",
    meta: {requiresAuth  :true}

  },
];
