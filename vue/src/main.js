import Vue from "vue";
import App from "./App.vue";
import router from "./router";
let Bootstrap = require("bootstrap");
Vue.use(Bootstrap);
import "bootstrap";
import 'bootstrap/dist/js/bootstrap.min.js'
import Vuelidate from "vuelidate";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import VueSidebarMenu from "vue-sidebar-menu";
import "vue-sidebar-menu/dist/vue-sidebar-menu.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/css/bootstrap.css";
import "@fortawesome/fontawesome-free/css/all.css";
import "vue-material/dist/vue-material.min.css";
import 'mdbvue/lib/css/mdb.min.css'
// import "mdb-vue-ui-kit/css/mdb.min.css";
import Vuetify from "vuetify";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "@andresouzaabreu/vue-data-table/dist/DataTable.css";
import VueScreen from "vue-screen";
import VueMaterial from "vue-material";
Vue.use(VueMaterial);
const vuetify = new Vuetify();
Vue.use(vuetify);
Vue.use(VueSidebarMenu);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(Vuelidate);
Vue.use(VueScreen);
window.Vue = require("vue");
window.router = router;
window.Fire = new Vue();
import "boxicons";
Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
