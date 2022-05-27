const dashboard = () =>
  import("../../components/dashboard/Dashboard.vue");
export default [
  {
    path: "/dashboards",
    component: dashboard,
    name: "dashboards",
  }
];
