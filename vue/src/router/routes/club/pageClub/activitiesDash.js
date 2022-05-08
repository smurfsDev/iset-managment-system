const activitiesDash = () => import("@/components/dashboardClub/activitiesDash")

export default [
  {
    path: "/activitiesDash",
    component: activitiesDash,
    name: "activitiesDash",
    meta: {requiresResponsable:true}
  },
];
