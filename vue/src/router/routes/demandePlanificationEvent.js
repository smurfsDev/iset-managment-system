const demandeEvent = () =>
  import(
    "../../components/club/demandeEvenement/demandeEvent.vue"
  );

export default [
  {
    path: "/demandeEvent",
    component: demandeEvent,
    name: "demadeEvent",
    meta: { requiresResponsable: true},
  },
];
