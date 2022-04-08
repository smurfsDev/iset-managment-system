import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import blogClub from "./routes/blogClub";
import demandeCreationClub from "./routes/demandeCreationClub";
import demandeCreationClubAdmin from "./routes/demandeCreationClubAdmin";
import demandeMateriel from "./routes/demandeMateriels";
import dashboard from "./routes/dashboardclub";
import headerDash from "./routes/headerDash";
import aboutDash from "./routes/aboutDash";
import activitiesDash from "./routes/activitiesDash";
import boardDash from "./routes/boardDash";
import projectsDash from "./routes/projectsDash";



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
  ...dashboard,
  ...headerDash,
  ...aboutDash,
  ...activitiesDash,
  ...boardDash,
  ...projectsDash
  


];

const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;
