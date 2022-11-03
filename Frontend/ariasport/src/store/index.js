import { createStore, createLogger } from 'vuex'
// Create a new store instance.
const store = createStore({
    state () {
      return {
        count: 0
      }
    },
    mutations: {
      increment (state) {
        state.count++
      }
    },
    plugins: [createLogger()] //Logs en el navegador
  })

  export default store;
