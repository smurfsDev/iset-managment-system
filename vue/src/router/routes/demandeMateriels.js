const demandeMateriel = () =>
  import("../../components/club/demandeMateriel/demandeMateriel.vue");

export default [
  {
    path: "/demandeMateriel",
    component: demandeMateriel,
    name: "demandeMateriel",
    meta: { requiresAuth: true },

  },
];
