import home from '@/views/home';
import iniciarSesion from '@/views/iniciarSesion';
import configurador from '@/views/configurador';
import mantenimiento from '@/views/mantenimiento';
import historial from '@/views/historial';

import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
    {
      path: "/",
      name: "home",
      component: home,
    },
    {
      path: "/inicioSesion",
      name: "iniciarSesion",
      component: iniciarSesion,
    },
    {
      path:"/configurador",
      name:"configurador",
      component: configurador
    },
    {
      path:"/mantenimiento",
      name:"mantenimiento",
      component: mantenimiento
    },
    {
      path:"/mantenimiento/historial",
      name:"historial",
      component: historial
    }
  ];

  const router = createRouter({
    history: createWebHashHistory(),
    routes,
  });
  export default router;

        /*
        {
         path: '/about',
         name: 'about',
         component: () => import('@/views/About.vue')
        }
        */
    

