const register = () => import("../../components/auth/register.vue");

export default [
  {
    path: "/register",
    component: register,
    name: "register",
    meta: {notLoggedIn: true}

  },
];
