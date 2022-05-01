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
  userName: (state) =>  state.user !== null ? state.user.name : "User",
  isAdmin : (state) => state.isAdmin,
  isStudent : (state) => state.isStudent,
  token: (state) => state.token,
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
          commit('setAdmin',response.data.data.isAdmin);
          commit('setStudent',response.data.data.isStudent);
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
    commit("resetAll");
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
  resetAll(state) {
    state.user = null;
    state.token = null;
    state.isAuthenticated = false;
    state.isAdmin = false;
    state.isStudent = false;
    state.isSuperAdmin = false;
  },
  setUser(state, user) {
    state.user = user;
  },
  setToken(state, token) {
    state.token = token;
  },
  setAdmin(state, isAdmin) {
    state.isAdmin = isAdmin;
  },
  setStudent(state, isStudent) {
    state.isStudent = isStudent;
  }
};
export default {
  state,
  getters,
  actions,
  mutations
};
