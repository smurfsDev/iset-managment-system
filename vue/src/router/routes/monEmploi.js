const monEmploi = () =>
  import(
    "../../components/emploidetemp/monEmploi.vue"
  );
export default [
  {
    path: "/monEmploi",
    component: monEmploi,
    name: "monEmploi",
    meta: { requiresEnseignant: true},
  },
];
