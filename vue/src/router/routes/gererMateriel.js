const gererMateriel = () =>
  import("../../components/admin/materiel/Materiel.vue");
export default [
  {
    path: "/gererMateriel",
    component: gererMateriel,
    name: "gererMateriel",
    meta: { requiresAorCorT: true },
  },
];
