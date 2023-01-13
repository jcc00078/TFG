import "mdb-vue-ui-kit/css/mdb.min.css";
import '@/assets/bootstrap.min.css'
import { persist } from 'pinia-persists'
import { createPinia } from "pinia";

import { createApp } from 'vue';
import App from './App.vue';
import router from "./router";
import axios from 'axios'
import VueAxios from 'vue-axios'
  const pinia = createPinia()
  pinia.use(persist())
  const app = createApp(App)
  axios.defaults.baseURL='http://localhost:8084/'
  app.use(pinia)
  app.use(router)
  app.use(VueAxios,axios)
  app.mount('#app');
