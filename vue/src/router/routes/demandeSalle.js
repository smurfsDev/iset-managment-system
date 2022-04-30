const demandeSalle = () =>
  import("../../components/club/demandeSalle/demandeSalles.vue");

export default [
  {
    path: "/demandeSalle",
    component: demandeSalle,
    name: "demandeSalle",
  },
];
