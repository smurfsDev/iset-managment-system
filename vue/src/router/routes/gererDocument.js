const GererDocument = () =>
  import(
    "../../components/chefDepartment/document/GererDocuments.vue"
  );
export default [
  {
    path: "/GererDocument",
    component: GererDocument,
    name: "GererDocument",
    meta: {requiresAuth  :true}

  },
];
