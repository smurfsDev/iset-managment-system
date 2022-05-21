const listavis= () =>
  import("../../../components/avis/listavis.vue");

export default [
  {
    path: "/listavis",
    component:listavis,
    name: "listavis",
    meta: { requiresAorC: true }
  }
];
