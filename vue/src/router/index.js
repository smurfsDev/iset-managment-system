import Vue from "vue";
import store from '../store/index';
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import blogClub from "./routes/blogClub";
import demandeCreationClub from "./routes/demandeCreationClub";
import demandeCreationClubAdmin from "./routes/demandeCreationClubAdmin";
import demandeMateriel from "./routes/demandeMateriels";
import members from "./routes/members";
import login from "./routes/login";
import register from "./routes/register";

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
  ...members,
  ...login,
  ...register
];

const router = new VueRouter({
  mode: "history",
  routes,
});

router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)) {
    if (store.getters.isAuthenticated) {
      next()
      return
    }
    next({name:'login', params: { msg: "You must be logged in" }})
  } else {
    next()
  };
  // if(to.matched.some(record => record.meta.notLoggedIn)) {
  //   if (!store.getters.isAuthenticated) {
  //     next()
  //     return
  //   }
  //   next('/')
  // } else {
  //   next()
  // };
  if (to.matched.some((record) => record.meta.guest)) {
    if (store.getters.isAuthenticated) {
      next("/posts");
      return;
    }
    next();
  } else {
    next();
  };
  if(to.matched.some(record => record.meta.requiresAdmin)) {
    if (store.getters.isAdmin) {
      next()
      return
    }
    next({name:'login', params: { msg: "You must be admin" }})
  } else {
    next()
  };
  if(to.matched.some(record => record.meta.requiresEtudiant)) {
    if (store.getters.isStudent) {
      next()
      return
    }
    next({name:'login', params: { msg: "You must be student" }})
  } else {
    next()
  };
  

});

export default router;
