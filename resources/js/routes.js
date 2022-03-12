import Vue from 'vue';
import VueRouter from "vue-router";
import example from "./routes/ExampleComponent";
import demandeCreationClub from "./routes/demandeCreationClub";

Vue.use(VueRouter);
export default new VueRouter(
    {
        mode:'history',
        scrollBehavior: (to, from, savedPosition) => ({ y: 0 }),
        routes: [
            ...example,...demandeCreationClub,
            ],
    }
)
