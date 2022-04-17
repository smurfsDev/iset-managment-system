import axios from "axios";
// import store from "..";

const state = {
  user: null,
  token: null,
  secrets: null,
  isAuthenticated: false,
  isAdmin: false,
  isUser: false,
  isSuperAdmin: false,
  message: null
};

const getters = {
  isAuthenticated: (state) => state.user !== null,
  StateUser: (state) => state.user
};
const actions = {
 
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
