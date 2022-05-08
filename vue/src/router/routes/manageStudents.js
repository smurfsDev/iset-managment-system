const manageStudents = () =>
  import("../../components/chefDepartment/manageStudents/manageStudents.vue");
export default [
  {
    path: "/cd/manageStudents",
    component: manageStudents,
    name: "manageStudentsAdmin",
    meta: { requiresChefDepartement: true },
  },
];
