const chat = () =>
  import("../../components/informer/chat.vue");

export default [
  {
    path: "/chat",
    component: chat,
    name: "chat",
    meta: { requiresAuth: true }
  }
];