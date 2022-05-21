const detailsavis= () =>
  import("../../../components/avis/detailsavis.vue");

export default [
  {
    path: "/detailsavis/:id",
    component:detailsavis,
    name: "detailsavis",
    meta: { requiresAorC: true }
  }
];
