const MesDemandesSalle = () =>
  import("../../components/club/demandeSalle/showMesDemandes.vue");

export default [
  {
    path: "/MesDemandesSalle",
    component: MesDemandesSalle,
    name: "MesDemandesSalle",
    meta: { 
      requiresAorC: true },
  },
];
