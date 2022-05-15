const demandePlannificationEvent = () =>
  import("../../components/admin/demandePlannificationEvent/demandePlannificationEvent.vue");

export default [
  {
    path: "/admin/demandePlannificationEvent",
    component: demandePlannificationEvent,
    name: "demandePlannificationEventAdmin",
    meta: { requiresAdmin: true },
  },
];
