const demandePlannificationEvent = () =>
  import("../../components/admin/demandePlannificationEvent/demandePlannificationEvent.vue");

export default [
  {
    path: "/demandePlannificationEvent",
    component: demandePlannificationEvent,
    name: "demandePlannificationEventAdmin",
    meta: { requiresAdmin: true },
  },
];
