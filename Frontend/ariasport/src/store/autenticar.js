import {defineStore} from 'pinia'

export const useStore = defineStore('autenticar',{
    state: () => {
      return {
        username: "",
        jwt: ""
      }
    },
    getters: {
      // noEsanonimo(state){
      //     return state.esAnonimo;
      // },

    },
    actions: {
      login(username, jwt) {
        this.username=username;
        this.jwt=jwt;
      },
      logout(){
        this.username="";
        this.jwt="";
      }
      ,
    },
  })
export default useStore;
