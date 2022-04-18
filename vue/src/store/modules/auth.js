import axios from "axios";
// import store from "..";
import router from "../../router/index";
const state = {
  user: null,
  token: null,
  secrets: null,
  isAuthenticated: false,
  isAdmin: false,
  isStudent: false,
  isSuperAdmin: false,
  message: null
};

const getters = {
  isAuthenticated: (state) => state.user !== null,
  StateUser: (state) => state.user,
  

};
const actions = {
  async LogIn({ commit }, User) {
    axios
      .get("http://localhost:8000/sanctum/csrf-cookie", {
        withCredentials: true
      })
      .then(() => {})
      .catch(() => {});
    await axios
      .post("http://localhost:8000/api/login", User, {
        headers: {
          "Content-Type": "application/json"
        },
        withCredentials: true
      })
      .then((response) => {
        if (response.status == 200) {
          this.tkn = response.data.data.token;
          localStorage.setItem("token", this.tkn);
          commit("setUser", response.data.data.user);
          commit("setToken", response.data.data.token);
        } else {
          console.log("jawna behi nai");
        }
        router.push("/");
      });
  },
  async LogOut({ commit }) {
    localStorage.removeItem("token");
    commit("setUser", null);
    commit("setToken", null);
    router.push("/login");

  },
  async Register({ commit }, User) {
    await axios
      .post("http://localhost:8000/api/register", User, {
        headers: {
          "Content-Type": "application/json"
        },
        withCredentials: true
      })
      .then((response) => {
        if (response.status == 200) {
          this.tkn = response.data.data.token;
          localStorage.setItem("token", this.tkn);
          commit("setUser", response.data.data.user);
          commit("setToken", response.data.data.token);
        } else {
          console.log("jawna behi nai");
        }
      });
  }
};
const mutations = {
  setUser(state, user) {
    state.user = user;
  },
  setToken(state, token) {
    state.token = token;
  },
};
export default {
  state,
  getters,
  actions,
  mutations
};
