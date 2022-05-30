const demandeCreationClub = () =>
  import("../../components/admin/demandeCreationClub/demandeCreationClub.vue");

export default [
  {
    path: "/demandeCreationClub",
    component: demandeCreationClub,
    name: "demandeCreationClubAdmin",
    meta: { requiresAdmin: true },
  },
];
