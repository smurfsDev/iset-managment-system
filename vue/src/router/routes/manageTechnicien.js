const manageTechnicien = () =>
  import("../../components/chefDepartment/technicien/technicien.vue");

export default [
  {
    path: "/manageTechnicien",
    component: manageTechnicien,
    name: "manageTechnicien",
    meta: { requiresChefDepartement: true },
  },
];
