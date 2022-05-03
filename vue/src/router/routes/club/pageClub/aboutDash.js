const aboutDash = () => import("@/components/dashboardClub/aboutDash")

export default [
  {
    path: "/aboutDash",
    component: aboutDash,
    name: "aboutDash",
    meta: {requiresResponsable:true}
  },
];
