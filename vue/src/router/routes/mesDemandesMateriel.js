const MesdemandeMateriel = () =>
  import("../../components/club/demandeMateriel/showMesDemandes.vue");

export default [
  {
    path: "/MesDemandesMateriel",
    component: MesdemandeMateriel,
    name: "MesdemandeMateriel",
    meta: { 
      requiresAorC: true },
  },
];
