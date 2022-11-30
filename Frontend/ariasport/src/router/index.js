import home from '@/views/home';
import iniciarSesion from '@/views/iniciarSesion';
import configurador from '@/views/configurador';
import comparador from '@/views/comparador';
import mantenimiento from '@/views/mantenimiento';
import historial from '@/views/historial';
import registroUsuario from '@/views/registroUsuario'
// import cita from '@/views/cita'

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
      path:"/mantenimiento",
      name:"mantenimiento",
      component: mantenimiento
    },
    {
      path:"/mantenimiento/historial/:numBastidor",
      name:"historial",
      component: historial
    },
    {
      path:"/registrarme",
      name:"registrarme",
      component: registroUsuario
    },
    // {
    //   path:"/citas",
    //   name:"cita",
    //   component: cita
    // }
  ];

  const router = createRouter({
    history: createWebHashHistory(),
    routes,
  });
  export default router;

    

