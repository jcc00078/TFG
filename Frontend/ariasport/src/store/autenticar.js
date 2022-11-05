import {defineStore} from 'pinia'

export const useStore = defineStore('autenticar',{
    state: () => {
      return {
        esAnonimo: true
      }
    },
    getters: {
      noEsanonimo(state){
          return state.esAnonimo;
      },
    },
    actions: {
      login() {
        this.esAnonimo=false;
      },
    },
  })
export default useStore;
