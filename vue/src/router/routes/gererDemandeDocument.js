const GererDemandeDocument = () =>
  import(
    "../../components/chefDepartment/demandedocument/Gererdemandedocument.vue"
  );
export default [
  {
    path: "/Gererdemandedocument",
    component: GererDemandeDocument,
    name: "Gererdemandedocument",
    meta: {requiresAuth  :true}

  },
];
