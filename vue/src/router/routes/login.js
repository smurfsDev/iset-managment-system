const login = () => import("../../components/auth/login.vue");

export default [
  {
    path: "/login",
    component: login,
    name: "login",
    meta: {notLoggedIn: true}
  },
];
