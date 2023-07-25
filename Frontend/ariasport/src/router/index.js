import home from "@/views/home";
import iniciarSesion from "@/views/iniciarSesion";
import configurador from "@/views/configurador";
import comparador from "@/views/comparador";
import mantenimiento from "@/views/mantenimiento";
import historial from "@/views/historial";
import registroUsuario from "@/views/registroUsuario";
import recomendador from "@/views/recomendador";
import sliderRecomendador from "@/components/sliderRecomendador";
import motoConfig from "@/components/motoConfig";
import calendarioCita from "@/components/calendarioCita";
import { useAuthStore } from "@/store/autenticar";
import { createRouter, createWebHashHistory } from "vue-router";

//Cargar bajo demanda la página de administrador
const adminFunc = () => import("@/views/adminFunc");

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
    path: "/configurador",
    name: "configurador",
    component: configurador,
  },
  {
    path: "/comparador",
    name: "comparador",
    component: comparador,
  },
  {
    path: "/mantenimiento",
    name: "mantenimiento",
    component: mantenimiento,
  },
  {
    path: "/mantenimiento/historial/:numBastidor",
    name: "historial",
    component: historial,
  },
  {
    path: "/registrarme",
    name: "registrarme",
    component: registroUsuario,
  },
  {
    path: "/recomendador",
    name: "recomendador",
    component: recomendador,
  },
  {
    path: "/slider",
    name: "slider",
    component: sliderRecomendador,
  },
  {
    path: "/configurador/:modelo",
    name: "motoConfig",
    component: motoConfig,
  },
  {
    path: "/citas",
    name: "cita",
    component: calendarioCita,
  },
  {
    path: "/admin",
    name: "admin",
    component: adminFunc,
    beforeEnter: () => {
      const authStore = useAuthStore();
      // reject the navigation
      return authStore.esAdmin;
    },
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});
export default router;
