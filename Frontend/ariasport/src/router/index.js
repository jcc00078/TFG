import home from '@/views/home';
import iniciarSesion from '@/views/iniciarSesion';
import configurador from '@/views/configurador';
import comparador from '@/views/comparador';
import mantenimiento from '@/views/mantenimiento';
import historial from '@/views/historial';
import configuradorKawasaki from '@/views/configurador';
import configuradorSuzuki from '@/views/configurador';
import registroUsuario from '@/views/registroUsuario'

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
      path:"/comparador",
      name:"comparador",
      component: comparador
    },
    {
      path:"/configurador-Kawasaki",
      name:"configuradorKawasaki",
      component: configuradorKawasaki
    },
    {
      path:"/configurador-Suzuki",
      name:"configuradorSuzuki",
      component: configuradorSuzuki
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
    },
    {
      path:"/registrarme",
      name:"registrarme",
      component: registroUsuario
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
    

