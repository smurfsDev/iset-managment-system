import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import blogClub from "./routes/blogClub";
import demandeCreationClub from "./routes/demandeCreationClub";
import demandeCreationClubAdmin from "./routes/demandeCreationClubAdmin";
import demandeMateriel from "./routes/demandeMateriels";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  ...demandeCreationClub,
  ...blogClub,
  ...demandeCreationClubAdmin,
  ...demandeMateriel,
];

const router = new VueRouter({
  routes,
});

export default router;
