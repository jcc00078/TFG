import "mdb-vue-ui-kit/css/mdb.min.css";
import '@/assets/bootstrap.min.css'
import { createPinia } from "pinia";

import { createApp } from 'vue';
import App from './App.vue';
import router from "./router";
import axios from 'axios'
import VueAxios from 'vue-axios'
  const pinia = createPinia()
  const app = createApp(App)
  app.use(pinia)
  app.use(router)
  app.use(VueAxios,axios)
  app.mount('#app');
