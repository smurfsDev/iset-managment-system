const consulterClasseEns = () =>
  import(
    "../../components/espaceEnseignant/consulterClasses.vue"
  );

export default [
  {
    path: "/consulterClasseEns",
    component: consulterClasseEns,
    name: "consulterClasseEns",
    meta: { requiresEnseignant: true},
  },
];
