const GererTypeCat = () =>
  import(
    "../../components/chefDepartment/TypeCat/GererTypeCat.vue"
  );
export default [
  {
    path: "/GererTypeCat",
    component: GererTypeCat,
    name: "GererTypeCat",
    meta: {requiresAuth  :true}

  },
];
