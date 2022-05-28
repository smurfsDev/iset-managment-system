const gererTypeCat = () =>
  import(
    "../../components/chefDepartment/TypeCat/GererTypeCat.vue"
  );
export default [
  {
    path: "/gererTypeCat",
    component: gererTypeCat,
    name: "gererTypeCat",
    meta: {requiresAuth  :true}

  },
];
