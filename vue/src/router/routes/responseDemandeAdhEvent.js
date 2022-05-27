const responseDemandeAdhEvent = () =>
  import("../../components/club/ResponseDemandeAdhesionEvent/responseDemandeAdhEvent.vue");

export default [
  {
    path: "/responseDemandeAdhEvent",
    component: responseDemandeAdhEvent,
    name: "responseDemandeAdhEvent",
    meta: { 
        requiresStudent: true },
  },
];
