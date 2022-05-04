const dashboard = () => import("@/components/dashboardClub/dashboard.vue");

export default [
  {
    path: "/dashboard",
    component: dashboard,
    name: "dashboard",
    meta: {requiresResponsable:true}
  },
];
