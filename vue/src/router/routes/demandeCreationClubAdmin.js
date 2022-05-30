const demandeCreationClub = () =>
  import("../../components/admin/demandeCreationClub/demandeCreationClub.vue");

export default [
  {
    path: "/demandeCreationClubAdmin",
    component: demandeCreationClub,
    name: "demandeCreationClubAdmin",
    meta: { requiresAdmin: true },
  },
];
