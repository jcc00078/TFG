import { defineStore } from "pinia";

export const useAuthStore = defineStore("autenticar", {
  state: () => {
    return {
      username: "",
      jwt: "",
      esAdmin: false,
    };
  },
  getters: {},
  actions: {
    login(username, jwt, esAdmin) {
      this.username = username;
      this.jwt = jwt;
      this.esAdmin = esAdmin;
    },
    logout() {
      this.username = "";
      this.jwt = "";
      this.esAdmin = false;
    },
  },
});
export default useAuthStore;
