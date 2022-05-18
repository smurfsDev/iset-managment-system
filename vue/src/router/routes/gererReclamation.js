const GererReclamation = () =>
  import(
    "../../components/reclamation/GererReclamation.vue"
  );
export default [
  {
    path: "/GererReclamation",
    component: GererReclamation,
    name: "GererReclamation",
    //meta: {requiresAuth:true}
    meta: {requiresAuth  :true}

  },
];
