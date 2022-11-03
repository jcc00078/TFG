import "mdb-vue-ui-kit/css/mdb.min.css";

import { createApp } from 'vue';
import App from './App.vue';
import router from "./router";
import store from "./store"

createApp(App).use(router).mount('#app');


  const app = createApp({App})

  // Install the store instance as a plugin
  app.use(store)
