const gererCategorieMateriel = () =>
  import("../../components/admin/categorieMateriel/categorieMateriel.vue");
export default [
  {
    path: "/gererCategorieMateriel",
    component: gererCategorieMateriel,
    name: "gererCategorieMateriel",
    meta: { requiresAorC: true },
  },
];
