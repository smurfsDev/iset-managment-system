const manageChefDepartments = () =>
  import("../../components/admin/chefDepartments/validationPanel.vue");

export default [
  {
    path: "/admin/manageChefDepartments",
    component: manageChefDepartments,
    name: "manageChefDepartmentsAdmin",
    meta: { requiresAdmin: true },
  },
];
