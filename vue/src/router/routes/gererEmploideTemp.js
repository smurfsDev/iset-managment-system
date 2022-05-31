const GererEmploideTemp = () =>
  import("../../components/emploidetemp/GererEmploideTemp.vue");
export default [
  {
    path: "/GererEmploideTemp",
    component: GererEmploideTemp,
    name: "GererEmploideTemp",
    meta: { requiresAuth: true },
  },
];
