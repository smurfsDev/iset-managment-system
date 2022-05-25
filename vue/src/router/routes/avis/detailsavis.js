const detailsavis= () =>
  import("../../../components/avis/detailsavis.vue");

export default [
  {
    path: "/detailsavis/:id",
    component:detailsavis,
    name: "/detailsavis/:id",
    meta: { requiresAorC: true }
  }
];
