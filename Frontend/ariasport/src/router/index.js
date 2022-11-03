import principal from '@/views/principal';
import iniciarSesion from '@/views/iniciarSesion';
import configurador from '@/views/configurador'
import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
    {
      path: "/principal",
      name: "principal",
      component: principal,
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
    

