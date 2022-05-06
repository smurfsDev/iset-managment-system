import Vue from "vue";
import store from "../store/index";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import blogClub from "./routes/club/pageClub/blogClub";
import demandeCreationClub from "./routes/demandeCreationClub";
import demandeCreationClubAdmin from "./routes/demandeCreationClubAdmin";
import demandeMateriel from "./routes/demandeMateriels";
import demandeSalle from "./routes/demandeSalle";

import dashboard from "./routes/club/pageClub/dashboardclub";
import headerDash from "./routes/club/pageClub/headerDash";
import aboutDash from "./routes/club/pageClub/aboutDash";
import activitiesDash from "./routes/club/pageClub/activitiesDash";
import boardDash from "./routes/club/pageClub/boardDash";
import projectsDash from "./routes/club/pageClub/projectsDash";
import members from "./routes/members";
import login from "./routes/login";
import register from "./routes/register";
import listeClubs from "./routes/listeClubs";
import demandeAdhesionForm from "./routes/demandeAdhesionForm";
import deemandeAdhesioClub from "./routes/demandeAdhesionClub";
import demandeAdhesionResponsable from "./routes/demandeAdhesionResponsable";
import manageChefDepartments from "./routes/manageChefDepartments";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
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
  ...projectsDash,
  ...members,
  ...login,
  ...register,
  ...listeClubs,
  ...demandeAdhesionForm,
  ...deemandeAdhesioClub,
  ...demandeAdhesionResponsable,
  ...demandeSalle,


  ...manageChefDepartments
];

const router = new VueRouter({
  mode: "history",
  routes
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (store.getters.isAuthenticated) {
      next();
      return;
    }
    next({ name: "login", params: { msg: "You must be logged in" } });
  } else {
    next();
  }
  if (to.matched.some((record) => record.meta.guest)) {
    if (store.getters.isAuthenticated) {
      next("/posts");
      return;
    }
    next();
  }else if (to.matched.some((record) => record.meta.requiresResponsable)) {
    if (store.getters.isResponsableClub) {
      next();
      return;
    }
    next({ name: "login", params: { msg: "You must be  a responsableClub" } });
  }else if (to.matched.some((record) => record.meta.requiresSorR)) {
    if (store.getters.isStudent || store.getters.isResponsableClub) {
      next();
      return;
    }
    next({ name: "login", params: { msg: "You must be student or a responsableClub" } });
  } else if (to.matched.some((record) => record.meta.requiresAdmin)) {
    if (store.getters.isAdmin) {
      next();
      return;
    }
    next({ name: "login", params: { msg: "You must be admin" } });
  } else if (to.matched.some((record) => record.meta.requiresEtudiant)) {
    if (store.getters.isStudent) {
      next();
      return;
    }
    next({ name: "login", params: { msg: "You must be student" } });
  }  else {
    next();
  }
});

export default router;
