import axios from "axios";
// import store from "..";
import router from "../../router/index";
const state = {
  user: null,
  token: null,
  csrfToken: null,
  secrets: null,
  isAuthenticated: false,
  isAdmin: false,
  isStudent: false,
  isResponsableClub: false,
  isSuperAdmin: false,
  isChefDepartement: false,
  isEnseignant: false,
  message: null,
  authStatus: null,
  authMessage: null,
  regStatus: null,
  regMessage: null,
  isTechnicien: false,
  
};


const getters = {
  isAuthenticated: (state) => state.user !== null,
  StateUser: (state) => state.user,
  userName: (state) =>  state.user !== null ? state.user.name : "User",
  isAdmin : (state) => state.isAdmin,
  isStudent : (state) => state.isStudent,
  isResponsableClub : (state) => state.isResponsableClub,
  isEnseignant : (state) => state.isEnseignant,
  token: (state) => state.token,
  authStatus : (state) => state.authStatus,
  authMessage : (state) => state.authMessage,
  regStatus : (state) => state.regStatus,
  regMessage : (state) => state.regMessage,
  isChefDepartement : (state) => state.isChefDepartement,
  isTechnicien : (state) => state.isTechnicien,

  
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
          // Vue.prototype.$http.defaults.headers.common['Authorization'] = 'Bearer ' + this.tkn;
          localStorage.setItem("token", this.tkn);
          commit('setAdmin',response.data.data.isAdmin);
          commit('setStudent',response.data.data.isStudent);
          commit('setResponsableClub',response.data.data.isResponsableClub);
          commit('setTechnicien',response.data.data.isTechnicien);         
          commit('setChefDepartement',response.data.data.isChefDepartement);
          commit('setEnseignant',response.data.data.isEnseignant);
          commit("setUser", response.data.data.user);
          commit("setAuthStatus", 1);

          commit("setToken", response.data.data.token);
        } else {
          console.log("jawna behi nai");
        }

        router.push("/");
      }).catch(function (error) {
        commit('setAuthStatus',2);
        commit('setAuthMessage',error.response.data.data.error); // this is the main part. Use the response property from the error object
       });
  },
  async LogOut({ commit }) {
    localStorage.removeItem("token");
    commit("resetAll");
    router.push("/login");

  },
  async Register(
    { commit },
     User) {
    await axios
      .post("http://localhost:8000/api/register", User, {
        headers: {
          "Content-Type": "application/json"
        },
        withCredentials: true
      })
       .then((response) => {
        if (response.status == 200) {
          // this.tkn = response.data.data.token;
          // localStorage.setItem("token", this.tkn);
          // commit("setUser", response.data.data.user);
          // commit("setToken", response.data.data.token);
          router.push("/login");
          commit("setRegStatus", 1);

        } else {
          console.log("jawna behi nai");
        }
      }) 
      .catch((error)=>{
        commit('setRegStatus',2);
        commit('setRegMessage',error.response.data.data.error); // this is the main part. Use the response property from the error object
      });
  }
};
const mutations = {
  resetAll(state) {
    state.user= null;
    state.token= null;
    state.csrfToken= null;
    state.secrets= null;
    state.isAuthenticated= false;
    state.isAdmin= false;
    state.isStudent= false;
    state.isResponsableClub= false;
    state.isSuperAdmin= false;
    state.isEnseignant= false;
    state.message= null;
    state.authStatus= null;
    state.authMessage= null;
    state.isChefDepartement= false;

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
  },
  setResponsableClub(state, isResponsableClub) {
    state.isResponsableClub = isResponsableClub;
  },
  setEnseignant(state, isEnseignant) {
    state.isEnseignant = isEnseignant;
  },
  setAuthStatus(state, authStatus) {
    state.authStatus = authStatus;
  },
  setAuthMessage(state, authMessage) {
    state.authMessage = authMessage;
  },
  setChefDepartement(state, isChefDepartement) {
    state.isChefDepartement = isChefDepartement;
  },
  setRegStatus(state, regStatus) {
    state.regStatus = regStatus;
  },
  setRegMessage(state, regMessage) {
    state.regMessage = regMessage;
  },
  setTechnicien(state, isTechnicien) {
    state.isTechnicien = isTechnicien;
  }
  

};
export default {
  state,
  getters,
  actions,
  mutations
};
