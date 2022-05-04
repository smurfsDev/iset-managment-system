const boardDash = () => import("@/components/dashboardClub/boardDash")

export default [
  {
    path: "/boardDash",
    component: boardDash,
    name: "boardDash",
    meta: {requiresResponsable:true}
  },
];
